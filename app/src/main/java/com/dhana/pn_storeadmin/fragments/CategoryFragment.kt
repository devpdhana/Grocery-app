package com.dhana.pn_storeadmin.fragments

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.dhana.pn_storeadmin.Adapter.CategoryAdapter
import com.dhana.pn_storeadmin.Model.CategoryModel
import com.dhana.pn_storeadmin.R
import com.dhana.pn_storeadmin.databinding.FragmentCategoryBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.ArrayList


class CategoryFragment : Fragment() {
    private lateinit var binding :FragmentCategoryBinding

    private  var imageUrl : Uri? = null
    private lateinit var dialog : Dialog

    private var launchGalleryActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode == Activity.RESULT_OK){
            imageUrl = it.data!!.data
            binding.ImageView.setImageURI(imageUrl)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(layoutInflater)

        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.progress_layout)
        dialog.setCancelable(false)

        binding.apply {
            ImageView.setOnClickListener{
                val intent = Intent("android.intent.action.GET_CONTENT")
                intent.type = "image/*"
                launchGalleryActivity.launch(intent)
            }
            btnCategoryUpload.setOnClickListener {
                validateData(binding.categoryName.text.toString())
            }
        }
        return binding.root
    }

    private fun getData() {
        val list = ArrayList<CategoryModel>()
        Firebase.firestore.collection("categories")
            .get().addOnSuccessListener {
                list.clear()
                for(doc in it.documents){
                    val data = doc .toObject(CategoryModel::class.java)
                    list.add(data!!)

                }
                binding.categoryRecycler.adapter = CategoryAdapter(requireContext(),list)

            }
    }

    private fun validateData(categroyName: String) {
        if (categroyName.isEmpty()){
            Toast.makeText(requireContext(),"Please enter category name",Toast.LENGTH_SHORT).show()
        }else if (imageUrl == null){
            Toast.makeText(requireContext(),"Please select image",Toast.LENGTH_SHORT).show()
        }
        else{
            uploadImage(categroyName)
        }
    }

    private fun uploadImage(categroyName: String) {
        dialog.show()
        val fileName = UUID.randomUUID().toString()+".jpg"
        val refStorage = FirebaseStorage.getInstance().reference.child("category/$fileName")
        refStorage.putFile(imageUrl!!).addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener { image ->
                storeData(categroyName,image.toString())
            }
        }.addOnFailureListener{
            dialog.dismiss()
            Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }

    private fun storeData(url: String, categroyName: String) {
        val db = Firebase.firestore

        val data = hashMapOf<String, Any>(
            "cate" to categroyName,
            "img" to url
        )
        db.collection("categories").add(data)
            .addOnSuccessListener {
                dialog.dismiss()
                binding.ImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_launcher_foreground))
                binding.categoryName.text = null
                getData()
                Toast.makeText(requireContext(),"Category Added",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                dialog.dismiss()
                Toast.makeText(requireContext(),"Something want wrong",Toast.LENGTH_SHORT).show()
            }
    }
}
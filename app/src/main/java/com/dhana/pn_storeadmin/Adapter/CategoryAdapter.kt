package com.dhana.pn_storeadmin.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dhana.pn_storeadmin.Model.CategoryModel
import com.dhana.pn_storeadmin.R
import com.dhana.pn_storeadmin.databinding.ItemCategoryLayoutBinding

class CategoryAdapter(var context : Context, var list : ArrayList<CategoryModel>)  : RecyclerView.Adapter<CategoryAdapter.categoryViewHolder>(){

    inner class categoryViewHolder(view : View): RecyclerView.ViewHolder(view){
        var binding = ItemCategoryLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryViewHolder {
        return categoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category_layout,parent,false))
    }

    override fun onBindViewHolder(holder: categoryViewHolder, position: Int) {
        holder.binding.textView2.text = list[position].cat
        Glide.with(context).load(list[position].img).into(holder.binding.ImageView2)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
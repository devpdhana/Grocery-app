package com.dhana.pn_storeadmin.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dhana.pn_storeadmin.databinding.ImageItemBinding
import java.util.Collections.list

class AddProducrImageAdapter(var list: ArrayList<Uri>): RecyclerView.Adapter<AddProducrImageAdapter.AddProductImageViewHolder>(){

    inner class AddProductImageViewHolder(val binding : ImageItemBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddProductImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddProductImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddProductImageViewHolder, position: Int) {
        holder.binding.itemImage.setImageURI(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
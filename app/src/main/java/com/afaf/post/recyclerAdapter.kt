package com.afaf.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afaf.post.databinding.ItemRowBinding

class recyclerAdapter(private var Data: ArrayList<dataItem>):
    RecyclerView.Adapter<recyclerAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),

                parent,
                false
            )

        )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = Data[position]

        holder.binding.apply {
            tvName.text = item.name
            tvLocation.text = item.location
        }
    }

    override fun getItemCount() = Data.size

}
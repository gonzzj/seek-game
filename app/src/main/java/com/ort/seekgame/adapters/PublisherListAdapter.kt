package com.ort.seekgame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ort.seekgame.R
import com.ort.seekgame.entities.Publisher
import com.ort.seekgame.holders.PublisherHolder

class PublisherListAdapter(private val listPublishers: MutableList<Publisher>, val context: Context, val onItemClick : (Int, String) -> Unit): RecyclerView.Adapter<PublisherHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublisherHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_platform,parent,false)
        return PublisherHolder(view)
    }
    override fun getItemCount(): Int = listPublishers.size
    override fun onBindViewHolder(holder: PublisherHolder, position: Int) {
        holder.setName(listPublishers[position].name)

        Glide
            .with(context)
            .load(listPublishers[position].image_background)
            .centerCrop()
            .into(holder.getImageView())

        holder.getCardLayout().setOnClickListener  () {
            onItemClick(listPublishers[position].id, "publishers")
        }
    }
}
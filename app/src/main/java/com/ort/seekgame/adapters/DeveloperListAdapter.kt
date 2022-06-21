package com.ort.seekgame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ort.seekgame.R
import com.ort.seekgame.entities.Developer
import com.ort.seekgame.holders.DeveloperHolder

class  DeveloperListAdapter(private val listDevelopers: MutableList<Developer>, val context: Context, val onItemClick : (Int, String) -> Unit): RecyclerView.Adapter<DeveloperHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_platform,parent,false)
        return DeveloperHolder(view)
    }
    override fun getItemCount(): Int = listDevelopers.size
    override fun onBindViewHolder(holder: DeveloperHolder, position: Int) {
        holder.setName(listDevelopers[position].name)

        Glide
            .with(context)
            .load(listDevelopers[position].image_background)
            .centerCrop()
            .into(holder.getImageView())

        holder.getCardLayout().setOnClickListener  () {
            onItemClick(listDevelopers[position].id, "developers")
        }
    }
}
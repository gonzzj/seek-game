package com.example.seekgame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seekgame.holders.GameHolder
import com.example.seekgame.R
import com.example.seekgame.entities.Game
import com.example.seekgame.entities.Platform
import com.example.seekgame.entities.Publisher
import com.example.seekgame.fragments.GameFragment
import com.example.seekgame.fragments.LoginFragmentDirections
import com.example.seekgame.fragments.SearchFragmentDirections
import com.example.seekgame.holders.PlatformHolder
import com.example.seekgame.holders.PublisherHolder

class PublisherListAdapter(private val listPublishers: MutableList<Publisher>, val context: Context, val onItemClick : (Int) -> Unit): RecyclerView.Adapter<PublisherHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublisherHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_platform,parent,false)
        return PublisherHolder(view)
    }
    override fun getItemCount(): Int = listPublishers.size
    override fun onBindViewHolder(holder: PublisherHolder, position: Int) {
        Glide
            .with(context)
            .load(listPublishers[position].image_background)
            .centerCrop()
            .into(holder.getImageView())

        holder.getCardLayout().setOnClickListener  () {
            onItemClick(position)
        }
    }
}
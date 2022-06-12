package com.example.seekgame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seekgame.holders.GameHolder
import com.example.seekgame.R
import com.example.seekgame.entities.Developer
import com.example.seekgame.entities.Game
import com.example.seekgame.entities.Platform
import com.example.seekgame.fragments.GameFragment
import com.example.seekgame.fragments.LoginFragmentDirections
import com.example.seekgame.fragments.SearchFragmentDirections
import com.example.seekgame.holders.DeveloperHolder
import com.example.seekgame.holders.PlatformHolder

class DeveloperListAdapter(private val listDevelopers: MutableList<Developer>, val context: Context, val onItemClick : (Int) -> Unit): RecyclerView.Adapter<DeveloperHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_platform,parent,false)
        return DeveloperHolder(view)
    }
    override fun getItemCount(): Int = listDevelopers.size
    override fun onBindViewHolder(holder: DeveloperHolder, position: Int) {
        Glide
            .with(context)
            .load(listDevelopers[position].image_background)
            .centerCrop()
            .into(holder.getImageView())

        holder.getCardLayout().setOnClickListener  () {
            onItemClick(position)
        }
    }
}
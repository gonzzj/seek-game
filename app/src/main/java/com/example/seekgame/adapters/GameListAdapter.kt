package com.example.seekgame.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seekgame.holders.GameHolder
import com.example.seekgame.R
import com.example.seekgame.entities.Game

class GameListAdapter(private val listGames: MutableList<Game>, val context: Context): RecyclerView.Adapter<GameHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_game,parent,false)
        return GameHolder(view)
    }
    override fun getItemCount(): Int = listGames.size
    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        Glide
            .with(context)
            .load(listGames[position].background_image)
            .override(600, 200)
            .centerCrop()
            .into(holder.getImageView())
    }
}
package com.example.seekgame.holders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.seekgame.R

class GameHolder (v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v

    fun getImageView () : ImageView {
        return view.findViewById(R.id.ivGameImage)
    }
}
package com.example.seekgame.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.seekgame.R

class PlatformHolder (v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v

    fun getImageView () : ImageView {
        return view.findViewById(R.id.ivPlatformImage)
    }

    fun getCardLayout (): CardView {
        return view.findViewById(R.id.cvPlatformItem)
    }
}
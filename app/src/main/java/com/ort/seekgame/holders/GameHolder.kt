package com.ort.seekgame.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ort.seekgame.R

class GameHolder (v: View) : RecyclerView.ViewHolder(v) {
    private var view: View = v

    fun setName(name: String) {
        val txt: TextView = view.findViewById(R.id.txtGameName)
        txt.text = name
    }

    fun getImageView () : ImageView {
        return view.findViewById(R.id.ivGameImage)
    }

    fun getCardLayout (): CardView {
        return view.findViewById(R.id.cvGameItem)
    }
}
package com.ort.seekgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.seekgame.R
import com.ort.seekgame.adapters.GameListAdapter
import com.ort.seekgame.entities.Game
import com.google.android.material.snackbar.Snackbar
import com.ort.seekgame.activities.HomeActivityArgs

class HomeFragment : Fragment() {

    lateinit var v: View
    lateinit var rvHomeList : RecyclerView
    private lateinit var gameListAdapter: GameListAdapter
    private var userGamesList : MutableList<Game> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v =  inflater.inflate(R.layout.home_fragment, container, false)
        rvHomeList = v.findViewById(R.id.rvHomeList)
        return v
    }

    override fun onStart() {
        super.onStart()

        rvHomeList.layoutManager = LinearLayoutManager(context)

        gameListAdapter = GameListAdapter(userGamesList, R.layout.item_home, requireContext()){ id ->
            onItemClick(id)
        }

        rvHomeList.adapter = gameListAdapter
    }

    fun onItemClick ( position : Int )  {
        Snackbar.make(v,position.toString(), Snackbar.LENGTH_SHORT).show()
    }
}
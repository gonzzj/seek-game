package com.ort.seekgame.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.seekgame.R
import com.ort.seekgame.adapters.GameListAdapter
import com.ort.seekgame.entities.Game
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    lateinit var v: View
    lateinit var rvHomeList : RecyclerView
    lateinit var pbHomeList : ProgressBar
    lateinit var txtHomeEmpty : TextView
    private lateinit var gameListAdapter: GameListAdapter
    private var userGamesList : MutableList<Game> = ArrayList()
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v =  inflater.inflate(R.layout.home_fragment, container, false)
        rvHomeList = v.findViewById(R.id.rvHomeList)
        pbHomeList = v.findViewById(R.id.pbHomeList)
        txtHomeEmpty = v.findViewById(R.id.txtHomeEmpty)
        return v
    }

    override fun onStart() {
        super.onStart()

        userGamesList.clear()
        val currentUser = Firebase.auth.currentUser

        db.collection("users").document(currentUser?.email!!)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.d("Test", "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    val games = snapshot.get("games") as ArrayList<HashMap<String, Object>>

                    if (games.isEmpty()) {
                        txtHomeEmpty.visibility = View.VISIBLE
                    } else {
                        txtHomeEmpty.visibility = View.GONE
                        for (game in games) {
                            userGamesList.add(
                                Game(
                                    (game.get("id") as Long).toInt(),
                                    game.get("name") as String,
                                    game.get("background_image") as String,
                                    game.get("description") as String,
                                    (game.get("metacritic") as Long).toInt()
                                )
                            )
                        }

                        gameListAdapter =
                            GameListAdapter(userGamesList, R.layout.item_home, requireContext()) { id ->
                                onItemClick(id)
                            }

                        rvHomeList.adapter = gameListAdapter
                    }

                    pbHomeList.visibility = View.GONE
                }
            }

        rvHomeList.layoutManager = LinearLayoutManager(context)
    }

    fun onItemClick ( id : Int )  {
        val action = HomeFragmentDirections.actionHomeFragmentToGameDetailFragment(id)
        v.findNavController().navigate(action)
    }
}
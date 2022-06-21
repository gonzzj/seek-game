package com.ort.seekgame.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.seekgame.R
import com.ort.seekgame.adapters.GameListAdapter
import com.ort.seekgame.entities.Game

class ProfileFragment : Fragment() {
    lateinit var v: View
    lateinit var btnLogout: Button
    lateinit var txtEmailProfle: TextView
    lateinit var rvProfileList: RecyclerView
    lateinit var pbProfileList: ProgressBar
    private var userGamesList : MutableList<Game> = ArrayList()
    private lateinit var gameListAdapter: GameListAdapter
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.profile_fragment, container, false)
        btnLogout = v.findViewById(R.id.btnLogout)
        txtEmailProfle = v.findViewById(R.id.txtEmailProfile)
        rvProfileList = v.findViewById(R.id.rvProfileList)
        pbProfileList = v.findViewById(R.id.pbProfileList);
        return v
    }

    override fun onStart() {
        super.onStart()

        val currentUser = Firebase.auth.currentUser

        txtEmailProfle.text = currentUser?.email

        db.collection("users").document(currentUser?.email!!)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.d("Test", "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    for (game in snapshot.get("games") as ArrayList<HashMap<String, Object>>) {
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

                    rvProfileList.adapter = gameListAdapter

                    pbProfileList.visibility = View.GONE
                }
            }

        rvProfileList.layoutManager = LinearLayoutManager(context)

        btnLogout.setOnClickListener {
            auth.signOut()
            val action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment2()
            v.findNavController().navigate(action)
        }
    }

    fun onItemClick ( id : Int )  {
        val action = ProfileFragmentDirections.actionProfileFragmentToGameDetailFragment(id)
        v.findNavController().navigate(action)
    }
}
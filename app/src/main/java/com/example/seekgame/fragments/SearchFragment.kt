package com.example.seekgame.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seekgame.adapters.GameListAdapter
import com.example.seekgame.services.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.seekgame.R
import com.example.seekgame.entities.Game

class SearchFragment : Fragment() {

    lateinit var v: View
    lateinit var rvGameList : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gameListAdapter: GameListAdapter
    private var listGames : MutableList<Game> = ArrayList<Game>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.search_fragment, container, false)
        rvGameList = v.findViewById(R.id.rvGameList)
        return v
    }

    override fun onStart() {
        super.onStart()

        listGames.add(Game(0, "Hola","https://cdn.pixabay.com/photo/2020/05/10/11/03/pacman-5153518_960_720.jpg"))
        listGames.add(Game(1, "Hola2","https://cdn.pixabay.com/photo/2020/05/10/11/03/pacman-5153518_960_720.jpg"))
        listGames.add(Game(2, "Hola3","https://cdn.pixabay.com/photo/2020/05/10/11/03/pacman-5153518_960_720.jpg"))
        listGames.add(Game(3, "Hola4","https://cdn.pixabay.com/photo/2020/05/10/11/03/pacman-5153518_960_720.jpg"))
        listGames.add(Game(4, "Hola5","https://cdn.pixabay.com/photo/2020/05/10/11/03/pacman-5153518_960_720.jpg"))
        listGames.add(Game(5, "Hola6","https://cdn.pixabay.com/photo/2020/05/10/11/03/pacman-5153518_960_720.jpg"))

        rvGameList.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        rvGameList.layoutManager = linearLayoutManager

        gameListAdapter = GameListAdapter(listGames, requireContext())

        rvGameList.adapter = gameListAdapter
    }

    /*
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.igdb.com/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getGamesByName("games?search=$query&key=90444bab91d84c889bf5ddc30270aa48");
            val response = call.body()

            if (call.isSuccessful) {
                val games = response?.results ?: emptyList()
                listGames.clear()
                listGames.addAll(games)
                gameListAdapter.notifyDataSetChanged()
            } else {
                showError()
            }
        }
    }

    private fun showError() {
        Toast.makeText(context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
    */
}
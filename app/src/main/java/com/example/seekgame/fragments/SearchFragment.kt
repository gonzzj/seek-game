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
import com.example.seekgame.databinding.SearchFragmentBinding
import com.example.seekgame.entities.Game
import com.example.seekgame.services.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment : Fragment() {

    private lateinit var gameListAdapter: GameListAdapter
    private var listGames : MutableList<Game> = ArrayList()
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        gameListAdapter = GameListAdapter(listGames, requireContext())
        binding.rvGameList.layoutManager = LinearLayoutManager(context)
        binding.rvGameList.adapter = gameListAdapter

        searchByName("")
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getGamesByName("games?search=$query&key=90444bab91d84c889bf5ddc30270aa48");
            val response = call.body()
            activity?.runOnUiThread {
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
    }

    private fun showError() {
        Toast.makeText(context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}
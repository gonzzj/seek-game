package com.ort.seekgame.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ort.seekgame.R
import com.ort.seekgame.adapters.GameListAdapter
import com.ort.seekgame.databinding.SearchFragmentBinding
import com.ort.seekgame.entities.Game
import com.ort.seekgame.services.APIService
import com.ort.seekgame.services.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

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

        val id = SearchFragmentArgs.fromBundle(requireArguments()).id
        val searchBy = SearchFragmentArgs.fromBundle(requireArguments()).searchBy

        gameListAdapter = GameListAdapter(listGames, R.layout.item_game, requireContext()){ pos ->
            onItemClick(pos)
        }

        binding.rvGameList.layoutManager = LinearLayoutManager(context)
        binding.rvGameList.adapter = gameListAdapter

        binding.svSearch.setOnQueryTextListener(this)
        searchByName("", id, searchBy)
    }

    private fun onItemClick(id: Int)  {
        val action = SearchFragmentDirections.actionSearchFragmentToGameDetail(id)
        binding.frameLayout.findNavController().navigate(action)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchByName(query: String, id: Int, searchBy: String) {
        val apiKey = getString(R.string.rawg_api_key)
        var searchByParam = ""
        if (searchBy.isNotEmpty()) { searchByParam = "&$searchBy=$id" }
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitBuilder.get().create(APIService::class.java).getGamesByName("games?search=$query&key=$apiKey$searchByParam");
            val response = call.body()
            activity?.runOnUiThread {
                binding.pbSearch.visibility = View.GONE
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByName(query.lowercase(Locale.getDefault()), 0, "")
        }

        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true
    }
}
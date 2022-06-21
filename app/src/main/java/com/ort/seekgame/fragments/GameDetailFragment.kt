package com.ort.seekgame.fragments

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ort.seekgame.databinding.GameDetailFragmentBinding
import com.ort.seekgame.services.APIService
import com.ort.seekgame.services.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameDetail : Fragment() {

    private var apiKey: String = "90444bab91d84c889bf5ddc30270aa48"
    private var _binding: GameDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GameDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val id = GameDetailArgs.fromBundle(requireArguments()).id

        getGame(id)
    }

    private fun getGame(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitBuilder.get().create(APIService::class.java).getGameDetail("games/$id?key=$apiKey");
            val response = call.body()
            activity?.runOnUiThread {
                binding.pbGameDetail.visibility = View.GONE
                if (call.isSuccessful) {
                    binding.tvGameDetailTitle.text = response?.name
                    binding.tvGameDetailBody.text = Html.escapeHtml(response?.description).toString()
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
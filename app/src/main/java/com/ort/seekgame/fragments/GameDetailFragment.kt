package com.ort.seekgame.fragments

import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.seekgame.R
import com.ort.seekgame.databinding.GameDetailFragmentBinding
import com.ort.seekgame.entities.Game
import com.ort.seekgame.services.APIService
import com.ort.seekgame.services.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GameDetail : Fragment() {

    private var _binding: GameDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var game: Game
    private var gameFound: Boolean = false
    private val db = Firebase.firestore
    private val currentUser = Firebase.auth.currentUser

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
        checkGame(id)

        binding.abAddGame.setOnClickListener {
            binding.pbGameDetail.visibility = View.VISIBLE
            val user = db.collection("users").document(currentUser?.email!!)

            if (!gameFound) {
                updateGame(user)
            } else {
                removeGame(user)
            }
        }
    }

    private fun getGame(id: Int) {
        val apiKey = getString(R.string.rawg_api_key)
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitBuilder.get().create(APIService::class.java).getGameDetail("games/$id?key=$apiKey");
            val response = call.body()
            activity?.runOnUiThread {
                binding.pbGameDetail.visibility = View.GONE
                binding.abAddGame.visibility = View.VISIBLE
                if (call.isSuccessful && response != null) {
                    game = Game(id, response.name, response.background_image, response.description, response.metacritic)
                    binding.tvGameDetailTitle.text = response.name
                    binding.tvGameDetailBody.text = Html.escapeHtml(response.description).toString()
                    binding.tvGameDetailBody.movementMethod = ScrollingMovementMethod()
                    binding.txtGameDetailMetacritic.text = "Metacritic: " + response.metacritic

                    Glide
                        .with(binding.root)
                        .load(response.background_image)
                        .centerCrop()
                        .into(binding.ivGameDetail)


                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    private fun updateGame(user: DocumentReference) {
        user.update("games", FieldValue.arrayUnion(game))
            .addOnSuccessListener {
                binding.abAddGame.setImageResource(R.drawable.outline_star_24)
                binding.pbGameDetail.visibility = View.GONE
                Toast.makeText(context, "Se agrego el juego a favoritos", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                    e -> Log.w("ERROR", "Error writing document", e)
            }
    }

    private fun removeGame(user: DocumentReference) {
        user.update("games", FieldValue.arrayRemove(game))
            .addOnSuccessListener {
                binding.abAddGame.setImageResource(R.drawable.outline_star_border_24)
                binding.pbGameDetail.visibility = View.GONE
                Toast.makeText(context, "Se elimino el juego de favoritos", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                    e -> Log.w("ERROR", "Error removing game from document", e)
            }
    }

    private fun checkGame(id: Int) {
        db.collection("users").document(currentUser?.email!!).get()
            .addOnSuccessListener{ document ->
                for (game in document.get("games") as ArrayList<HashMap<String, Object>>) {
                    val gameId = (game.get("id") as Long).toInt()
                    if (id == gameId) {
                        gameFound = true
                        binding.abAddGame.setImageResource(R.drawable.outline_star_24)
                    }
                }
            }
            .addOnFailureListener {
                e -> Log.w("ERROR", "Error getting user", e)
            }
    }
}
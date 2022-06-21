package com.ort.seekgame.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.seekgame.R
import com.ort.seekgame.adapters.DeveloperListAdapter
import com.ort.seekgame.adapters.PlatformListAdapter
import com.ort.seekgame.adapters.PublisherListAdapter
import com.ort.seekgame.databinding.PlatformFragmentBinding
import com.ort.seekgame.entities.Developer
import com.ort.seekgame.entities.Platform
import com.ort.seekgame.entities.Publisher
import com.ort.seekgame.services.APIService
import com.ort.seekgame.services.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlatformFragment : Fragment() {

    private lateinit var platformListAdapter: PlatformListAdapter
    private lateinit var publisherListAdapter: PublisherListAdapter
    private lateinit var developerListAdapter: DeveloperListAdapter
    private var listPlatforms : MutableList<Platform> = ArrayList()
    private var listPublishers : MutableList<Publisher> = ArrayList()
    private var listDevelopers : MutableList<Developer> = ArrayList()
    private var _binding: PlatformFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PlatformFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvPlatformList.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.rvPublisherList.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.rvDeveloperList.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        platformListAdapter = PlatformListAdapter(listPlatforms, requireContext()){ id, searchBy ->
            onItemClick(id, searchBy)
        }

        publisherListAdapter = PublisherListAdapter(listPublishers, requireContext()){ id, searchBy ->
            onItemClick(id, searchBy)
        }

        developerListAdapter = DeveloperListAdapter(listDevelopers, requireContext()){ id, searchBy ->
            onItemClick(id, searchBy)
        }

        binding.rvPlatformList.adapter = platformListAdapter
        binding.rvPublisherList.adapter = publisherListAdapter
        binding.rvDeveloperList.adapter = developerListAdapter

        setPlatforms()
        setPublishers()
        setDevelopers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setPlatforms() {
        val apiKey = getString(R.string.rawg_api_key)
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitBuilder.get().create(APIService::class.java).getPlatforms("platforms?key=$apiKey");
            val response = call.body()
            activity?.runOnUiThread {
                binding.pbListPlatforms.visibility = View.GONE
                if (call.isSuccessful) {
                    val platforms = response?.results ?: emptyList()
                    listPlatforms.clear()
                    listPlatforms.addAll(platforms)
                    platformListAdapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setPublishers() {
        val apiKey = getString(R.string.rawg_api_key)
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitBuilder.get().create(APIService::class.java).getPublishers("publishers?key=$apiKey");
            val response = call.body()
            activity?.runOnUiThread {
                binding.pbListPublishers.visibility = View.GONE
                if (call.isSuccessful) {
                    val publishers = response?.results ?: emptyList()
                    listPublishers.clear()
                    listPublishers.addAll(publishers)
                    publisherListAdapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setDevelopers() {
        val apiKey = getString(R.string.rawg_api_key)
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitBuilder.get().create(APIService::class.java).getDevelopers("developers?key=$apiKey");
            val response = call.body()
            activity?.runOnUiThread {
                binding.pbListDevelopers.visibility = View.GONE
                if (call.isSuccessful) {
                    val developers = response?.results ?: emptyList()
                    listDevelopers.clear()
                    listDevelopers.addAll(developers)
                    developerListAdapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    private fun onItemClick(id: Int, searchBy: String)  {
        val action = PlatformFragmentDirections.actionPlatformFragmentToSearchFragment(id, searchBy)
        binding.frameLayout.findNavController().navigate(action)
    }

    private fun showError() {
        Toast.makeText(context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}
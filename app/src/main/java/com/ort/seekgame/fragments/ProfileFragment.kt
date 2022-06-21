package com.ort.seekgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ort.seekgame.R

class ProfileFragment : Fragment() {
    lateinit var v: View
    lateinit var btnLogout: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.profile_fragment, container, false)
        auth = Firebase.auth
        btnLogout = v.findViewById(R.id.btnLogout)
        return v
    }

    override fun onStart() {
        super.onStart()

        btnLogout.setOnClickListener {
            auth.signOut()
            val action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment2()
            v.findNavController().navigate(action)
        }
    }
}
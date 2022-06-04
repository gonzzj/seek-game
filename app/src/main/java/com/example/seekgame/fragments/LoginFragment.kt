package com.example.seekgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.seekgame.R

class LoginFragment : Fragment() {

    lateinit var v: View
    lateinit var btnLogin: Button
    lateinit var txtUser: EditText
    lateinit var txtPassword: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.login_fragment, container, false)

        btnLogin = v.findViewById(R.id.btnLogin)
        txtUser = v.findViewById(R.id.txtUser)
        txtPassword = v.findViewById(R.id.txtPassword)
        // Inflate the layout for this fragment
        return v
    }

    override fun onStart() {
        super.onStart()

        btnLogin.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeActivity()
            v.findNavController().navigate(action)
        }
    }
}
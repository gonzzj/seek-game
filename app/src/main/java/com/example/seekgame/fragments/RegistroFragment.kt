package com.example.seekgame.fragments

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.example.seekgame.R
import com.example.seekgame.viewModels.RegistroViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.security.Provider

class RegistroFragment : Fragment() {
    lateinit var v: View
    lateinit var btnRegister2: Button
    lateinit var nombre: EditText
    lateinit var txtMailReg: EditText
    lateinit var txtPassword2: EditText



    companion object {
        fun newInstance() = RegistroFragment()

    }

    private lateinit var viewModel: RegistroViewModel

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       v= inflater.inflate(R.layout.fragment_registro, container, false)
        btnRegister2 = v.findViewById(R.id.btnRegister2)
        nombre= v.findViewById(R.id.txtNombreReg)
        txtMailReg= v.findViewById(R.id.txtMailReg)
        txtPassword2=v.findViewById(R.id.txtPass2)
        return v
    }


    override fun onStart() {
        super.onStart()
        btnRegister2.setOnClickListener {
            if (nombre.text.isNotEmpty() && txtMailReg.text.isNotEmpty()  && txtPassword2.text.isNotEmpty()) {
                //Logica para registrar usuario en firebase
                }

            }
        }




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistroViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
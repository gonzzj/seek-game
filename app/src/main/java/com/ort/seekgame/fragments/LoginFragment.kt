package com.ort.seekgame.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ort.seekgame.R

class LoginFragment : Fragment() {

    lateinit var v: View
    lateinit var btnLogin: Button
    lateinit var btnRegister: Button
    lateinit var txtMail: EditText
    lateinit var txtPassword: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.login_fragment, container, false)
        auth = Firebase.auth
        btnLogin = v.findViewById(R.id.btnLogin)
        txtMail = v.findViewById(R.id.txtMail)
        txtPassword = v.findViewById(R.id.txtPassword)
        btnRegister = v.findViewById(R.id.btnRegister)

        // Inflate the layout for this fragment
        return v
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            goToHome()
        }

        btnLogin.setOnClickListener {
            if (txtMail.text.isNotEmpty() && txtPassword.text.isNotEmpty()) {
                signAccount()
            } else {
                Snackbar.make(v, "El mail o la conraseña estan vacios", Snackbar.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegistroFragment()
            v.findNavController().navigate(action)
        }
    }

    private fun signAccount() {
        activity?.let {
            auth.signInWithEmailAndPassword(txtMail.text.toString(), txtPassword.text.toString())
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        goToHome()
                    } else {
                        Snackbar.make(v, "El mail o la contraseña son incorrectos", Snackbar.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun goToHome() {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeActivity()
        v.findNavController().navigate(action)
    }
}

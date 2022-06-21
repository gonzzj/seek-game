package com.ort.seekgame.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.seekgame.R
import com.ort.seekgame.entities.User

class RegistroFragment : Fragment() {
    lateinit var v: View
    lateinit var btnRegisterNewUser: Button
    lateinit var txtMailRegister: EditText
    lateinit var txtPassRegister: EditText
    lateinit var txtPassRegister2: EditText
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.registro_fragment, container, false)
        auth = Firebase.auth
        btnRegisterNewUser = v.findViewById(R.id.btnRegisterNewUser)
        txtMailRegister = v.findViewById(R.id.txtMailRegister)
        txtPassRegister = v.findViewById(R.id.txtPassRegister)
        txtPassRegister2 = v.findViewById(R.id.txtPassRegister2)
        return v
    }

    override fun onStart() {
        super.onStart()

        btnRegisterNewUser.setOnClickListener {
            if (txtMailRegister.text.isEmpty() || txtPassRegister.text.isEmpty()) {
                Snackbar.make(v, "El mail o la conraseña estan vacios", Snackbar.LENGTH_SHORT).show()
            } else if (txtPassRegister.text.toString() != txtPassRegister2.text.toString()) {
                Snackbar.make(v, "La contraseñas no son iguales", Snackbar.LENGTH_SHORT).show()
            } else {
                createAccount()
            }
        }
    }

    private fun createAccount() {
        activity?.let { it1 ->
            auth.createUserWithEmailAndPassword(txtMailRegister.text.toString(), txtPassRegister.text.toString())
                .addOnCompleteListener(it1) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        user.let {
                            val email = user?.email
                            db.collection("users").add(User(email, "", ""))
                        }

                        val action = RegistroFragmentDirections.actionRegistroFragmentToHomeActivity(true)
                        v.findNavController().navigate(action)
                    } else {
                        Log.w("AUTH", "signInWithEmail:failure", task.exception)
                        Snackbar.make(v, "Fallo el registro del nuevo usuario.", Snackbar.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
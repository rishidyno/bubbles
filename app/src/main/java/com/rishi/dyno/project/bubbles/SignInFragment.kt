package com.rishi.dyno.project.bubbles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.rishi.dyno.project.bubbles.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var signInBinding: FragmentSignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        signInBinding = FragmentSignInBinding.inflate(inflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()
        signInBinding.button.setOnClickListener { view ->
            val email = signInBinding.emailEt.text.toString()
            val password = signInBinding.passET.text.toString()
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        // ...
                    }
                }
        }

        return signInBinding.root
    }
}
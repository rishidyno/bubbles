package com.rishi.dyno.project.bubbles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.rishi.dyno.project.bubbles.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    lateinit var signUpBinding: FragmentSignUpBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        signUpBinding.button.setOnClickListener { view ->
            val email = signUpBinding.emailEt.text.toString()
            val password = signUpBinding.passET.text.toString()
            val confirmPassword = signUpBinding.confirmPassET.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val user = firebaseAuth.currentUser
                            } else {
                                Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                }else {
                    signUpBinding.confirmPassET.error = "Password does not match"
                }
            }else {
                if (email.isEmpty()) {
                    signUpBinding.emailEt.error = "Email cannot be empty"
                }
                if (password.isEmpty()) {
                    signUpBinding.passET.error = "Password cannot be empty"
                }
                if (confirmPassword.isEmpty()) {
                    signUpBinding.confirmPassET.error = "Confirm Password cannot be empty"
                }
            }
        }
        return signUpBinding.root
    }

}
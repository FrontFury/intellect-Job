package com.example.intellectjob

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intellectjob.databinding.ActivitySignInBinding
import com.example.intellectjob.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvBackToSignIn.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val name = binding.etSignUpName.text.toString().trim()
            val phone = binding.etSignUpPhone.text.toString().trim()
            val password = binding.etSignUpPassword.text.toString().trim()
            val confirmPassword = binding.etConfirSignUpPassword.text.toString().trim()
            val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{6,}$".toRegex()

            if (name.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please complete your details", Toast.LENGTH_SHORT).show()
            } else {
                if (!password.matches(passwordRegex)) {
                    Toast.makeText(this, "Password requires: 6+ chars, 1 uppercase, 1 lowercase, 1 number, and 1 special char", Toast.LENGTH_LONG).show()
                }
                else{
                    if (password != confirmPassword) {
                        Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this, "Account Registered!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignUp, HomePage::class.java)
                        intent.putExtra("name",name)
                        startActivity(intent)
                    }
                }
            }


        }

    }
}
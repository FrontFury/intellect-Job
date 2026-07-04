package com.example.intellectjob

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intellectjob.databinding.ActivitySignInBinding
import com.example.intellectjob.databinding.ActivitySplashViewBinding

class SignIn : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvBackToSignUp.setOnClickListener {
            val intent = Intent(this@SignIn, SignUp::class.java)
            startActivity(intent)
        }

        sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE)
        val savedName = sharedPreferences.getString("username", null)
        if (savedName != null) {
            Toast.makeText(this, "Data exist", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@SignIn, HomePage::class.java))
            finish()
        }

        // Handle Log In Button Click and Extract Data
        binding.btnLogin.setOnClickListener {

            val phone = binding.etPhone.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Phone: $phone", Toast.LENGTH_SHORT).show()

            }
        }

    }
}
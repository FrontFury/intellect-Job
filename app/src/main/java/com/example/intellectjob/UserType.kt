package com.example.intellectjob

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intellectjob.databinding.ActivitySignUpBinding
import com.example.intellectjob.databinding.ActivityUserTypeBinding

class UserType : AppCompatActivity() {
    private lateinit var binding: ActivityUserTypeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Job Seeker Selection
        binding.cardJobSeeker.setOnClickListener {
            val intent = Intent(this@UserType, SignUp::class.java)
            startActivity(intent)
        }

        // Recruiter Selection
        binding.cardRecruiter.setOnClickListener {
            val intent = Intent(this@UserType, RecruiterSignUp::class.java)
            startActivity(intent)
        }

        binding.textLogin.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

    }
}
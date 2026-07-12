package com.example.intellectjob

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.intellectjob.databinding.ActivityUserTypeBinding

class UserType : AppCompatActivity() {
    private lateinit var binding: ActivityUserTypeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var recruiterSharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if user is already logged in to prevent duplicate accounts
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        recruiterSharedPreferences = getSharedPreferences("MyCompanyPrefs", MODE_PRIVATE)

        val savedName = sharedPreferences.getString("username", null)
        val savedCompanyName = recruiterSharedPreferences.getString("company_name", null)

        if (savedName != null) {
            startActivity(Intent(this, HomePage::class.java))
            finish()
            return
        }

        if (savedCompanyName != null) {
            startActivity(Intent(this, RecruiterHomePage::class.java))
            finish()
            return
        }

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

        // Login Selection
        binding.textLogin.setOnClickListener {
            val intent = Intent(this@UserType, SignIn::class.java)
            startActivity(intent)
        }
    }
}
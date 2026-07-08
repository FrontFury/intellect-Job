package com.example.intellectjob

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intellectjob.databinding.ActivityRecruiterSignUpBinding
import com.example.intellectjob.databinding.ActivityUserTypeBinding

class RecruiterSignUp : AppCompatActivity() {
    private lateinit var binding: ActivityRecruiterSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecruiterSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateRecruiter.setOnClickListener {
            val intent = Intent(this, RecruiterHomePage::class.java)
            startActivity(intent)
        }

        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

    }
}
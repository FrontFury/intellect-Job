package com.example.intellectjob

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.intellectjob.databinding.ActivityAboutPageBinding

class AboutPage : AppCompatActivity() {
    private lateinit var binding: ActivityAboutPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAboutPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bottom Navigation Logic
        binding.homePageView.setOnClickListener {
            startActivity(Intent(this, HomePage::class.java))
            finish()
        }

        binding.savedPageView.setOnClickListener {
            startActivity(Intent(this, SavedJobPage::class.java))
            finish()
        }

        // About is currently active, no need to navigate to itself
    }
}
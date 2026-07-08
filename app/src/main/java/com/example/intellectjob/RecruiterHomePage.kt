package com.example.intellectjob

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intellectjob.databinding.ActivityRecruiterHomePageBinding

class RecruiterHomePage : AppCompatActivity() {
    private  lateinit var binding: ActivityRecruiterHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecruiterHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCreateJob.setOnClickListener {
            val intent = Intent(this@RecruiterHomePage, CreateJob::class.java)
            startActivity(intent)
        }
    }
}
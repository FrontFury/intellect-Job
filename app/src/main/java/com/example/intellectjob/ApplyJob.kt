package com.example.intellectjob

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.intellectjob.databinding.ActivityApplyJobBinding

class ApplyJob : AppCompatActivity() {
    private lateinit var binding: ActivityApplyJobBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityApplyJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this@ApplyJob, HomePage::class.java)
            startActivity(intent)
        }

        val jobTitle = intent.getStringExtra("JOB_TITLE") ?: "Job"
        val companyName = intent.getStringExtra("COMPANY_NAME") ?: "Company"

        binding.tvHeaderTitle.text = "Apply For $jobTitle"
        binding.tvHeaderCompany.text = companyName

        binding.btnSubmitApplication.setOnClickListener {
            val fullName = binding.etFullName.text.toString()
            val email = binding.etEmail.text.toString()

            if (fullName.isNotEmpty() && email.isNotEmpty()) {
                Toast.makeText(this, "Application submitted for $jobTitle at $companyName", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.example.intellectjob

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.intellectjob.databinding.ActivityRecruiterSignUpBinding

class RecruiterSignUp : AppCompatActivity() {
    private lateinit var binding: ActivityRecruiterSignUpBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var jobSeekerPreferences: SharedPreferences

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecruiterSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyCompanyPrefs", MODE_PRIVATE)
        jobSeekerPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        // Prevent Job Seeker from signing up as Recruiter
        if (jobSeekerPreferences.getString("username", null) != null) {
            Toast.makeText(this, "You already have a Job Seeker account", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomePage::class.java))
            finish()
            return
        }

        val savedCompanyName = sharedPreferences.getString("company_name", null)
        if (savedCompanyName != null) {
            startActivity(Intent(this, RecruiterHomePage::class.java))
            finish()
            return
        }

        binding.tvSignIn.setOnClickListener {
            startActivity(Intent(this, SignIn::class.java))
            finish()
        }

        binding.btnCreateRecruiter.setOnClickListener {
            val companyName = binding.etCompanyName.text.toString().trim()
            val recruiterName = binding.etRecruiterName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            val website = binding.etWebsite.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()
            val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$".toRegex()

            if (companyName.isEmpty() || recruiterName.isEmpty() || email.isEmpty() || website.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please complete your details", Toast.LENGTH_SHORT).show()
            } else if (!password.matches(passwordRegex)) {
                Toast.makeText(this, "Password requires: 6+ chars, 1 uppercase, 1 lowercase, 1 number, and 1 special char", Toast.LENGTH_LONG).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
            } else {
                sharedPreferences.edit { putString("company_name", companyName) }
                Toast.makeText(this, "Account Registered!", Toast.LENGTH_SHORT).show()
                
                val intent = Intent(this, RecruiterHomePage::class.java)
                intent.putExtra("companyName", companyName)
                startActivity(intent)
                finishAffinity() // Clear backstack to prevent going back to role selection
            }
        }
    }
}
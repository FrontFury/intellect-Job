package com.example.intellectjob

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.intellectjob.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var recruiterSharedPreferences: SharedPreferences

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        recruiterSharedPreferences = getSharedPreferences("MyCompanyPrefs", MODE_PRIVATE)

        // Prevent Recruiter from signing up as Job Seeker
        if (recruiterSharedPreferences.getString("company_name", null) != null) {
            Toast.makeText(this, "You already have a Recruiter account", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, RecruiterHomePage::class.java))
            finish()
            return
        }

        val savedName = sharedPreferences.getString("username", null)
        if (savedName != null) {
            startActivity(Intent(this, HomePage::class.java))
            finish()
            return
        }

        binding.tvBackToSignIn.setOnClickListener {
            startActivity(Intent(this, SignIn::class.java))
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val name = binding.etSignUpName.text.toString().trim()
            val phone = binding.etSignUpPhone.text.toString().trim()
            val password = binding.etSignUpPassword.text.toString().trim()
            val confirmPassword = binding.etConfirSignUpPassword.text.toString().trim()
            val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$".toRegex()

            if (name.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please complete your details", Toast.LENGTH_SHORT).show()
            } else if (!password.matches(passwordRegex)) {
                Toast.makeText(this, "Password requires: 6+ chars, 1 uppercase, 1 lowercase, 1 number, and 1 special char", Toast.LENGTH_LONG).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
            } else {
                sharedPreferences.edit { putString("username", name) }
                Toast.makeText(this, "Account Registered!", Toast.LENGTH_SHORT).show()
                
                val intent = Intent(this, HomePage::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
                finishAffinity() // Clears the backstack so back button won't return to UserType
            }
        }
    }
}
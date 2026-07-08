package com.example.intellectjob

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
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

        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

        binding.btnCreateRecruiter.setOnClickListener {
            val companyName = binding.etCompanyName.text.toString().trim()
            val recruiterName = binding.etRecruiterName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            val website = binding.etWebsite.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()
            val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{6,}$".toRegex()


            if (companyName.isEmpty() || recruiterName.isEmpty() || email.isEmpty() || website.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
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
                        val intent = Intent(this, RecruiterHomePage::class.java)
                        startActivity(intent)
                    }
                }
            }

        }

    }
}
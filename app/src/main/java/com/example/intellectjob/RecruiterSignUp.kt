package com.example.intellectjob

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
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
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecruiterSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

        sharedPreferences = getSharedPreferences("MyCompanyPrefs",MODE_PRIVATE)
        val savedCompanyName = sharedPreferences.getString("company_name", null)

        if (savedCompanyName != null) {
            Toast.makeText(this, "Data exist", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@RecruiterSignUp, RecruiterHomePage::class.java))
            finish()
        }

        binding.etCompanyName.setText(savedCompanyName)


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
                        sharedPreferences.edit {
                            putString("company_name", companyName)
                        }
                        val intent = Intent(this, RecruiterHomePage::class.java)
                        intent.putExtra("companyName",companyName)
                        startActivity(intent)
                    }
                }
            }

        }

    }
}

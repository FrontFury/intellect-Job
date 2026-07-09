package com.example.intellectjob

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.intellectjob.databinding.ActivityRecruiterHomePageBinding


class RecruiterHomePage : AppCompatActivity() {
    private  lateinit var binding: ActivityRecruiterHomePageBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecruiterHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCreateJob.setOnClickListener {
            val intent = Intent(this@RecruiterHomePage, CreateJob::class.java)
            startActivity(intent)
        }

        val greeting = getGreeting()
        binding.tvRecruiterGreeting?.text = greeting

        sharedPreferences = getSharedPreferences("MyCompanyPrefs",MODE_PRIVATE)
        val savedCompanyName = sharedPreferences.getString("company_name", null)

        val intentCompanyName = intent.getStringExtra("companyName")

        binding.tvRecruiterCompanyName?.text = intentCompanyName
        binding.tvRecruiterCompanyName?.text = savedCompanyName

        binding.btnRecruiterLogout?.setOnClickListener {
            sharedPreferences.edit().clear().apply()

            startActivity(Intent(this@RecruiterHomePage, SignIn::class.java))
            finish()
        }
    }
}
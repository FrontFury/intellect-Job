package com.example.intellectjob

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intellectjob.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE)
        val savedName = sharedPreferences.getString("username","No Name")

        val intentName = intent.getStringExtra("name")
        binding.tvHomeWelcome.text = "Welcome Mr. $intentName"


        binding.tvHomeWelcome.text = "Hi Mr. $savedName"
        binding.btnLogout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this@HomePage, SignIn::class.java))
        }

    }
}
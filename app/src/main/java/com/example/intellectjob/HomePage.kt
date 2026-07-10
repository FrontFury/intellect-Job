package com.example.intellectjob

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intellectjob.databinding.ActivityHomePageBinding
import androidx.core.content.edit

class HomePage : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private lateinit var adapter: JobAdapter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jobList = listOf<Job>(
            Job("Android Developer", "Pathao Ltd.", "৳45,000 - ৳60,000"),
            Job("Frontend Developer", "Brain Station 23", "৳50,000 - ৳70,000"),
            Job("Backend Developer", "TigerIT Bangladesh", "৳55,000 - ৳80,000"),
            Job("Full Stack Developer", "Datasoft Systems", "৳60,000 - ৳90,000"),
            Job("UI/UX Designer", "BJIT", "৳40,000 - ৳65,000"),
            Job("Software Engineer", "Samsung R&D Institute Bangladesh", "৳70,000 - ৳120,000"),
            Job("QA Engineer", "Enosis Solutions", "৳35,000 - ৳55,000"),
            Job("DevOps Engineer", "REVE Systems", "৳80,000 - ৳130,000"),
            Job("Data Analyst", "Robi Axiata Ltd.", "৳50,000 - ৳75,000"),
            Job("Mobile App Developer", "Kona Software Lab", "৳45,000 - ৳70,000"),
            Job("Cyber Security Analyst", "Banglalink", "৳60,000 - ৳95,000"),
            Job("Machine Learning Engineer", "bKash Ltd.", "৳80,000 - ৳140,000"),
            Job("Cloud Engineer", "Aamra Networks", "৳65,000 - ৳100,000"),
            Job("Flutter Developer", "SSL Wireless", "৳45,000 - ৳75,000"),
            Job("Java Developer", "Nascenia", "৳50,000 - ৳85,000")
        )
        adapter = JobAdapter(jobList)

        binding.featuredJob.layoutManager = LinearLayoutManager(this@HomePage)
        binding.featuredJob.adapter = adapter

        val sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE)
        val savedName = sharedPreferences.getString("username","No Name")

        val intentName = intent.getStringExtra("name")
        binding.tvHomeWelcome.text = "Welcome Mr. $intentName"


        binding.tvHomeWelcome.text = "Hi Mr. $savedName"
        binding.btnLogout.setOnClickListener {
            sharedPreferences.edit { clear() }

            startActivity(Intent(this, SignIn::class.java))
            finish()
        }

        binding.savedPageView.setOnClickListener {
            startActivity(Intent(this@HomePage, SavedJobPage::class.java))
        }

    }
}
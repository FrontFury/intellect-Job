package com.example.intellectjob

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intellectjob.databinding.ActivitySavedJobPageBinding

class SavedJobPage : AppCompatActivity() {
    private lateinit var binding: ActivitySavedJobPageBinding
    private lateinit var savedAdapter: SavedJobAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySavedJobPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val savedJobList = listOf(
            SavedJob("Senior Android Developer", "Pathao Ltd.", "Dhaka, Bangladesh", "৳45,000 - ৳60,000"),
            SavedJob("Junior Android Developer", "Brain Station 23", "Dhaka, Bangladesh", "৳30,000 - ৳45,000"),
            SavedJob("Frontend Developer", "Tiger IT Bangladesh", "Dhaka, Bangladesh", "৳40,000 - ৳60,000"),
            SavedJob("Backend Developer", "DataSoft Systems", "Dhaka, Bangladesh", "৳45,000 - ৳70,000"),
            SavedJob("Full Stack Developer", "REVE Systems", "Dhaka, Bangladesh", "৳60,000 - ৳90,000"),
            SavedJob("Flutter Developer", "BJIT Ltd.", "Dhaka, Bangladesh", "৳40,000 - ৳65,000"),
            SavedJob("React Native Developer", "Enosis Solutions", "Dhaka, Bangladesh", "৳50,000 - ৳80,000"),
            SavedJob("Software Engineer", "Samsung R&D Institute Bangladesh", "Dhaka, Bangladesh", "৳70,000 - ৳120,000"),
            SavedJob("QA Engineer", "Selise Digital Platforms", "Dhaka, Bangladesh", "৳35,000 - ৳55,000"),
            SavedJob("UI/UX Designer", "LeadSoft Bangladesh", "Dhaka, Bangladesh", "৳35,000 - ৳60,000"),
            SavedJob("Java Developer", "Southtech Limited", "Dhaka, Bangladesh", "৳50,000 - ৳75,000"),
            SavedJob("Kotlin Developer", "Pathao Ltd.", "Dhaka, Bangladesh", "৳55,000 - ৳85,000"),
            SavedJob("iOS Developer", "Shohoz", "Dhaka, Bangladesh", "৳50,000 - ৳80,000"),
            SavedJob("DevOps Engineer", "SSL Wireless", "Dhaka, Bangladesh", "৳70,000 - ৳100,000"),
            SavedJob("Database Administrator", "Nascenia", "Dhaka, Bangladesh", "৳55,000 - ৳90,000"),
            SavedJob("Machine Learning Engineer", "Bohubrihi", "Dhaka, Bangladesh", "৳70,000 - ৳110,000"),
            SavedJob("Cyber Security Analyst", "Genex Infosys", "Dhaka, Bangladesh", "৳60,000 - ৳95,000"),
            SavedJob("System Administrator", "Aamra Technologies", "Dhaka, Bangladesh", "৳45,000 - ৳70,000"),
            SavedJob("Software Support Engineer", "Walton Digi-Tech", "Gazipur, Bangladesh", "৳30,000 - ৳50,000"),
            SavedJob("Cloud Engineer", "ShopUp", "Dhaka, Bangladesh", "৳80,000 - ৳130,000")

        )

        savedAdapter = SavedJobAdapter(savedJobList)

        binding.savedJobsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.savedJobsRecyclerView.adapter = savedAdapter

    }
}

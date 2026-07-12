package com.example.intellectjob

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intellectjob.Model.Jobs
import com.example.intellectjob.Network.RetrofitInstance
import com.example.intellectjob.databinding.ActivityManageJobsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageJobs : AppCompatActivity() {
    private lateinit var binding: ActivityManageJobsBinding
    private lateinit var adapter: JobsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityManageJobsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchJobs()

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        adapter = JobsAdapter(emptyList())
        binding.rvJobs.layoutManager = LinearLayoutManager(this)
        binding.rvJobs.adapter = adapter
    }

    private fun fetchJobs() {
        RetrofitInstance.api.getJobs().enqueue(object : Callback<List<Jobs>> {
            override fun onResponse(call: Call<List<Jobs>>, response: Response<List<Jobs>>) {
                if (response.isSuccessful) {
                    val jobs = response.body() ?: emptyList()
                    adapter.updateData(jobs)
                } else {
                    Toast.makeText(this@ManageJobs, "Failed to load jobs", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Jobs>>, t: Throwable) {
                Log.e("ManageJobs", "Error: ${t.message}")
                Toast.makeText(this@ManageJobs, "Error connecting to server", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
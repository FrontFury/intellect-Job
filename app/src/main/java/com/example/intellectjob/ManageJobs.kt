package com.example.intellectjob

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intellectjob.Adapter.JobsAdapter
import com.example.intellectjob.Network.RetrofitInstance
import com.example.intellectjob.databinding.ActivityManageJobsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

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
        adapter = JobsAdapter(emptyList()) { jobId ->
            showDeleteConfirmationDialog(jobId)
        }
        binding.rvJobs.layoutManager = LinearLayoutManager(this)
        binding.rvJobs.adapter = adapter
    }

    private fun showDeleteConfirmationDialog(jobId: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Confirm")
            .setMessage("Are you ready?")
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("OK") { dialog, _ ->
                deleteJob(jobId)
                dialog.dismiss()
            }
            .show()
    }

    private fun fetchJobs() {
        // Start Shimmer Animation
        binding.shimmerView.startShimmer()
        binding.shimmerView.visibility = View.VISIBLE
        binding.rvJobs.visibility = View.GONE

        lifecycleScope.launch {
            try {
                val jobs = RetrofitInstance.api.getJobs()

                binding.shimmerView.stopShimmer()
                binding.shimmerView.visibility = View.GONE
                binding.rvJobs.visibility = View.VISIBLE

                if (jobs.isEmpty()) {
                    Toast.makeText(this@ManageJobs, "No jobs found on server", Toast.LENGTH_LONG).show()
                } else {
                    adapter.updateData(jobs)
                }
            } catch (e: Exception) {
                binding.shimmerView.stopShimmer()
                binding.shimmerView.visibility = View.GONE
                binding.rvJobs.visibility = View.VISIBLE

                Log.e("ManageJobs", "Network Failure: ${e.message}")
                Toast.makeText(this@ManageJobs, "Connection Error", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun deleteJob(id: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.deleteJob(id)
                if (response.isSuccessful) {
                    Toast.makeText(this@ManageJobs, "Job deleted", Toast.LENGTH_SHORT).show()
                    fetchJobs()
                } else {
                    Toast.makeText(this@ManageJobs, "Delete failed: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("ManageJobs", "Delete Error: ${e.message}")
                Toast.makeText(this@ManageJobs, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerView.startShimmer()
    }

    override fun onPause() {
        binding.shimmerView.stopShimmer()
        super.onPause()
    }
}
package com.example.intellectjob

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intellectjob.Adapter.JobsAdapter
import com.example.intellectjob.Model.Jobs
import com.example.intellectjob.Network.RetrofitInstance
import com.example.intellectjob.databinding.ActivityManageJobsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        adapter = JobsAdapter(emptyList()) { jobId ->
            showDeleteConfirmationDialog(jobId)
        }
        binding.rvJobs.layoutManager = LinearLayoutManager(this)
        binding.rvJobs.adapter = adapter
    }

    private fun showDeleteConfirmationDialog(jobId: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Confirm Delete")
            .setMessage("Are you sure you want to delete this job?")
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Delete") { dialog, _ ->
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

        RetrofitInstance.api.getJobs().enqueue(object : Callback<List<Jobs>> {
            override fun onResponse(call: Call<List<Jobs>>, response: Response<List<Jobs>>) {
                // Stop and Hide Shimmer
                binding.shimmerView.stopShimmer()
                binding.shimmerView.visibility = View.GONE
                binding.rvJobs.visibility = View.VISIBLE

                if (response.isSuccessful) {
                    val jobs = response.body()
                    if (jobs.isNullOrEmpty()) {
                        Toast.makeText(this@ManageJobs, "No jobs found on server", Toast.LENGTH_LONG).show()
                    } else {
                        adapter.updateData(jobs)
                    }
                } else {
                    val errorMsg = "Error: ${response.code()} ${response.message()}"
                    Log.e("ManageJobs", errorMsg)
                    Toast.makeText(this@ManageJobs, errorMsg, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Jobs>>, t: Throwable) {
                // Stop and Hide Shimmer on Failure too
                binding.shimmerView.stopShimmer()
                binding.shimmerView.visibility = View.GONE
                binding.rvJobs.visibility = View.VISIBLE

                Log.e("ManageJobs", "Network Failure: ${t.message}")
                Toast.makeText(this@ManageJobs, "Connection Error: Check internet or API URL", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun deleteJob(id: String) {
        RetrofitInstance.api.deleteJob(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ManageJobs, "Job deleted", Toast.LENGTH_SHORT).show()
                    fetchJobs()
                } else {
                    Toast.makeText(this@ManageJobs, "Delete failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ManageJobs, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
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
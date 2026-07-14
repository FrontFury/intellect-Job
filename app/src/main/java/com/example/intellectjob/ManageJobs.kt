package com.example.intellectjob

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intellectjob.Adapter.JobsAdapter
import com.example.intellectjob.ViewModel.ManageJobsViewModel
import com.example.intellectjob.databinding.ActivityManageJobsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ManageJobs : AppCompatActivity() {
    private lateinit var binding: ActivityManageJobsBinding
    private lateinit var adapter: JobsAdapter
    private val viewModel: ManageJobsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityManageJobsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()

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

    private fun observeViewModel() {
        // Observe jobs list
        viewModel.jobs.observe(this) { jobs ->
            if (jobs != null) {
                adapter.updateData(jobs)
            }
        }

        // Observe loading state for shimmer
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.shimmerView.startShimmer()
                binding.shimmerView.visibility = View.VISIBLE
                binding.rvJobs.visibility = View.GONE
            } else {
                binding.shimmerView.stopShimmer()
                binding.shimmerView.visibility = View.GONE
                binding.rvJobs.visibility = View.VISIBLE
            }
        }

        // Observe errors
        viewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        // Observe delete status
        viewModel.deleteStatus.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Job deleted successfully", Toast.LENGTH_SHORT).show()
                viewModel.resetDeleteStatus()
            }
        }
    }

    private fun showDeleteConfirmationDialog(jobId: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Confirm")
            .setMessage("Are you ready?")
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("OK") { dialog, _ ->
                viewModel.deleteJob(jobId)
                dialog.dismiss()
            }
            .show()
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isLoading.value == true) {
            binding.shimmerView.startShimmer()
        }
    }

    override fun onPause() {
        binding.shimmerView.stopShimmer()
        super.onPause()
    }
}
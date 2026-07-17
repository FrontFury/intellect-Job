package com.example.intellectjob

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.intellectjob.Model.Jobs
import com.example.intellectjob.Network.RetrofitInstance
import com.example.intellectjob.databinding.ActivityUpdateJobBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.util.Calendar

class UpdateJob : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateJobBinding
    private var jobId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDropdowns()
        setupDatePicker()

        // Get Job data from intent
        val jobJson = intent.getStringExtra("JOB_DATA")
        if (jobJson != null) {
            val job = Gson().fromJson(jobJson, Jobs::class.java)
            jobId = job._id
            populateFields(job)
        }

        binding.btnUpdateJob.setOnClickListener {
            validateAndUpdate()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupDropdowns() {
        val categories = arrayOf("Software Development", "Design", "Marketing", "Data Science", "Management", "Customer Service")
        binding.actCategory.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories))

        val jobTypes = arrayOf("Full-Time", "Part-Time", "Contract", "Internship", "Remote")
        binding.actJobType.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, jobTypes))
    }

    private fun setupDatePicker() {
        binding.etDeadline.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, day ->
                binding.etDeadline.setText("$day/${month + 1}/$year")
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun populateFields(job: Jobs) {
        binding.etJobTitle.setText(job.title)
        binding.actCompany.setText(job.company)
        binding.actCategory.setText(job.category, false)
        binding.actJobType.setText(job.jobType, false)
        binding.etExperience.setText(job.experience)
        binding.etVacancy.setText(job.vacancies.toString())
        binding.etLocation.setText(job.location)
        binding.etSalaryMin.setText(job.salary.min.toString())
        binding.etSalaryMax.setText(job.salary.max.toString())
        binding.etDeadline.setText(job.deadline)
        binding.etDescription.setText(job.description)
    }

    private fun validateAndUpdate() {
        val title = binding.etJobTitle.text.toString().trim()
        val vacanciesStr = binding.etVacancy.text.toString().trim()
        val vacancies = vacanciesStr.toIntOrNull() ?: 0

        if (title.isEmpty() || vacanciesStr.isEmpty() || jobId == null) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Create a Map to send only the fields that need updating (avoiding _id in body)
        val updates = mapOf(
            "title" to title,
            "company" to binding.actCompany.text.toString().trim(),
            "category" to binding.actCategory.text.toString().trim(),
            "jobType" to binding.actJobType.text.toString().trim(),
            "experience" to binding.etExperience.text.toString().trim(),
            "vacancies" to vacancies,
            "location" to binding.etLocation.text.toString().trim(),
            "salary" to mapOf(
                "min" to (binding.etSalaryMin.text.toString().toIntOrNull() ?: 0),
                "max" to (binding.etSalaryMax.text.toString().toIntOrNull() ?: 0)
            ),
            "deadline" to binding.etDeadline.text.toString().trim(),
            "description" to binding.etDescription.text.toString().trim()
        )

        updateJobOnServer(updates)
    }

    private fun updateJobOnServer(updates: Map<String, Any>) {
        jobId?.let { id ->
            binding.btnUpdateJob.isEnabled = false
            binding.btnUpdateJob.text = "Updating..."
            
            lifecycleScope.launch {
                try {
                    val response = RetrofitInstance.api.updateJob(id, updates)
                    if (response.isSuccessful) {
                        Toast.makeText(this@UpdateJob, "Updated Successfully!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@UpdateJob, "Update Failed: ${response.code()}", Toast.LENGTH_SHORT).show()
                        binding.btnUpdateJob.isEnabled = true
                        binding.btnUpdateJob.text = "Update Vacancy"
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@UpdateJob, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    binding.btnUpdateJob.isEnabled = true
                    binding.btnUpdateJob.text = "Update Vacancy"
                }
            }
        }
    }
}
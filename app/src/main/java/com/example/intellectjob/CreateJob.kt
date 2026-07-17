package com.example.intellectjob

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.intellectjob.Model.Jobs
import com.example.intellectjob.Model.Salary
import com.example.intellectjob.Network.RetrofitInstance
import com.example.intellectjob.databinding.ActivityCreateJobBinding
import kotlinx.coroutines.launch
import java.util.Calendar

class CreateJob : AppCompatActivity() {
    private lateinit var binding: ActivityCreateJobBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateJobBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDropdowns()
        setupDatePicker()

        binding.btnPublish.setOnClickListener {
            validateAndPublish()
        }
        
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupDropdowns() {
        val categories = arrayOf("Software Development", "Design", "Marketing", "Data Science", "Management", "Customer Service")
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categories)
        binding.actCategory.setAdapter(categoryAdapter)

        val jobTypes = arrayOf("Full-Time", "Part-Time", "Contract", "Internship", "Remote")
        val jobTypeAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, jobTypes)
        binding.actJobType.setAdapter(jobTypeAdapter)
    }

    private fun setupDatePicker() {
        binding.etDeadline.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.etDeadline.setText(date)
            }, year, month, day)
            
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }
    }

    private fun validateAndPublish() {
        val title = binding.etJobTitle.text.toString().trim()
        val company = binding.actCompany.text.toString().trim()
        val category = binding.actCategory.text.toString().trim()
        val jobType = binding.actJobType.text.toString().trim()
        val experience = binding.etExperience.text.toString().trim()
        val vacancies = binding.etVacancy.text.toString().toIntOrNull() ?: 0
        val location = binding.etLocation.text.toString().trim()
        val minSalary = binding.etSalaryMin.text.toString().toIntOrNull() ?: 0
        val maxSalary = binding.etSalaryMax.text.toString().toIntOrNull() ?: 0
        val deadline = binding.etDeadline.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()

        if (title.isEmpty() || company.isEmpty() || category.isEmpty() || jobType.isEmpty() || 
            location.isEmpty() || deadline.isEmpty() || description.isEmpty() || vacancies == 0) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val salary = Salary(max = maxSalary, min = minSalary)
        val job = Jobs(
            _id = "", 
            category = category,
            company = company,
            deadline = deadline,
            description = description,
            experience = experience,
            jobType = jobType,
            location = location,
            salary = salary,
            title = title,
            vacancies = vacancies
        )

        publishJob(job)
    }

    private fun publishJob(job: Jobs) {
        binding.btnPublish.isEnabled = false
        binding.btnPublish.text = "Publishing..."

        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.postJob(job)
                if (response.isSuccessful) {
                    Toast.makeText(this@CreateJob, "Job Published Successfully!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this@CreateJob, "Failed to publish: ${response.code()}", Toast.LENGTH_SHORT).show()
                    binding.btnPublish.isEnabled = true
                    binding.btnPublish.text = "Publish Vacancy"
                }
            } catch (e: Exception) {
                Toast.makeText(this@CreateJob, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                binding.btnPublish.isEnabled = true
                binding.btnPublish.text = "Publish Vacancy"
            }
        }
    }
}
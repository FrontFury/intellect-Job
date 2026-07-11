package com.example.intellectjob.Model

data class Jobs(
    val _id: String,
    val category: String,
    val company: String,
    val deadline: String,
    val description: String,
    val experience: String,
    val jobType: String,
    val location: String,
    val salary: Salary,
    val title: String,
    val vacancies: Int
)
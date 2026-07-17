package com.example.intellectjob.Model

import com.google.gson.annotations.SerializedName

data class Jobs(
    @SerializedName("_id") val _id: String,
    @SerializedName("category") val category: String,
    @SerializedName("company") val company: String,
    @SerializedName("deadline") val deadline: String,
    @SerializedName("description") val description: String,
    @SerializedName("experience") val experience: String,
    @SerializedName("jobType") val jobType: String,
    @SerializedName("location") val location: String,
    @SerializedName("salary") val salary: Salary,
    @SerializedName("title") val title: String,
    @SerializedName("vacancies") val vacancies: Int
)
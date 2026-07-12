package com.example.intellectjob.Network

import com.example.intellectjob.Model.Jobs
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("jobs")
    fun getJobs() : Call<List<Jobs>>

}
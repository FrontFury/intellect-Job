package com.example.intellectjob.Network

import com.example.intellectjob.Model.Jobs
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("jobs")
    fun getJobs() : Call<List<Jobs>>

    @DELETE("jobs/{id}")
    fun deleteJob(@Path("id") id: String): Call<Void>

}
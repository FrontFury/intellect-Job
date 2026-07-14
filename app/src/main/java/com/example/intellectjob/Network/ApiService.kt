package com.example.intellectjob.Network

import com.example.intellectjob.Model.Jobs
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("jobs")
    suspend fun getJobs() : List<Jobs>

    @DELETE("jobs/{id}")
    suspend fun deleteJob(@Path("id") id: String): Response<Void>

}
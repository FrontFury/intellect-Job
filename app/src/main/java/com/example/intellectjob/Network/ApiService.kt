package com.example.intellectjob.Network

import com.example.intellectjob.Model.Jobs
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {

    @GET("jobs")
    suspend fun getJobs() : List<Jobs>

    @POST("jobs")
    suspend fun postJob(@Body job: Jobs): Response<Jobs>

    @PATCH("jobs/{id}")
    suspend fun updateJob(@Path("id") id: String, @Body updates: Map<String, @JvmSuppressWildcards Any>): Response<Jobs>

    @DELETE("jobs/{id}")
    suspend fun deleteJob(@Path("id") id: String): Response<Void>

}
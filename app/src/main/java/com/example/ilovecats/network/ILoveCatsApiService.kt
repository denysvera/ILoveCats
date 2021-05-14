package com.example.ilovecats.network

import com.example.ilovecats.models.Cat
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ILoveCatsApiService {

    @GET("/v1/images/search?")
    fun getCatImages(@Query("limit") limit: Int,
                     @Query("page") page: Int,
                     @Query("order") order: String): Deferred<List<Cat>>
}
package com.namanh.composebase.data.remote

import com.namanh.composebase.data.model.PostResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getPosts(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String,
    ): PostResponse

}
package com.namanh.composebase.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PostResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Post>
)

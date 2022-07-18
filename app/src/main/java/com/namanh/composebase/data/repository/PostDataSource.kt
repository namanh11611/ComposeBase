package com.namanh.composebase.data.repository

import com.namanh.composebase.data.model.Post
import kotlinx.coroutines.flow.Flow

interface PostDataSource {

    fun getPostsStream(): Flow<Result<List<Post>>>

    suspend fun getPosts(): Result<List<Post>>

    fun getPostStream(postId: String): Flow<Result<Post>>

    suspend fun getPost(postId: String): Result<Post>

    suspend fun savePost(post: Post)

    suspend fun deleteAllPosts()

    suspend fun deletePost(postId: String)
}
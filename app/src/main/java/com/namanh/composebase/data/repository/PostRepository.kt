package com.namanh.composebase.data.repository

import com.namanh.composebase.data.model.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface PostRepository {

    fun getPostsStream(): Flow<Result<List<Post>>>

    suspend fun getPosts(forceUpdate: Boolean = false): Result<List<Post>>

    suspend fun refreshPosts()

    fun getPostStream(postId: String): Flow<Result<Post>>

    suspend fun getPost(postId: String, forceUpdate: Boolean = false): Result<Post>

    suspend fun refreshPost(postId: String)

    suspend fun savePost(post: Post)

    suspend fun deleteAllPosts()

    suspend fun deletePost(postId: String)
}
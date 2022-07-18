package com.namanh.composebase.data.remote

import com.namanh.composebase.data.model.Post
import com.namanh.composebase.data.repository.PostDataSource
import com.namanh.composebase.data.repository.Result
import com.namanh.composebase.di.DispatcherIO
import com.namanh.composebase.utils.AppUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : PostDataSource {
    override fun getPostsStream(): Flow<Result<List<Post>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPosts(): Result<List<Post>> = withContext(dispatcherIO) {
        try {
            val response = apiService.getPosts(
                AppUtils.API_COUNTRY,
                AppUtils.API_CATEGORY,
                AppUtils.API_KEY
            )
            Result.Success(response.articles)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    override fun getPostStream(postId: String): Flow<Result<Post>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPost(postId: String): Result<Post> {
        TODO("Not yet implemented")
    }

    override suspend fun savePost(post: Post) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllPosts() {
        TODO("Not yet implemented")
    }

    override suspend fun deletePost(postId: String) {
        TODO("Not yet implemented")
    }
}
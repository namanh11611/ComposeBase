package com.namanh.composebase.data.local

import com.namanh.composebase.data.model.Post
import com.namanh.composebase.data.repository.PostDataSource
import com.namanh.composebase.data.repository.Result
import com.namanh.composebase.di.DispatcherIO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostLocalDataSource @Inject constructor(
    private val postDao: PostDao,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : PostDataSource {
    override fun getPostsStream(): Flow<Result<List<Post>>> =
        postDao.observeAllPosts().map {
            Result.Success(it)
        }

    override suspend fun getPosts(): Result<List<Post>> = withContext(dispatcherIO) {
        try {
            Result.Success(postDao.getAllPosts())
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    override fun getPostStream(postId: String): Flow<Result<Post>> {
        return postDao.observePostById(postId).map {
            Result.Success(it)
        }
    }

    override suspend fun getPost(postId: String): Result<Post> = withContext(dispatcherIO) {
        try {
            Result.Success(postDao.getPostById(postId))
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    override suspend fun savePost(post: Post) {
        postDao.insert(post)
    }

    override suspend fun deleteAllPosts() {
        postDao.deleteAll()
    }

    override suspend fun deletePost(postId: String) {
        TODO("Not yet implemented")
    }

    suspend fun saveAllPosts(posts: List<Post>) {
        postDao.insertAll(posts)
    }
}
package com.namanh.composebase.data.repository

import com.namanh.composebase.data.local.PostLocalDataSource
import com.namanh.composebase.data.model.Post
import com.namanh.composebase.data.remote.PostRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource,
    private val postLocalDataSource: PostLocalDataSource
) : PostRepository {

    override fun getPostsStream(): Flow<Result<List<Post>>> {
        return postLocalDataSource.getPostsStream()
    }

    override suspend fun getPosts(forceUpdate: Boolean): Result<List<Post>> {
        if (forceUpdate) {
            try {
                updatePostsFromRemoteDataSource()
            } catch (exception: Exception) {
                return Result.Error(exception)
            }
        }
        return postLocalDataSource.getPosts()
    }

    override suspend fun refreshPosts() {
        updatePostsFromRemoteDataSource()
    }

    override fun getPostStream(postId: String): Flow<Result<Post>> {
        return postLocalDataSource.getPostStream(postId)
    }

    override suspend fun getPost(postId: String, forceUpdate: Boolean): Result<Post> {
        if (forceUpdate) {
            try {
                updatePostFromRemoteDataSource(postId)
            } catch (exception: Exception) {
                return Result.Error(exception)
            }
        }
        return postLocalDataSource.getPost(postId)
    }

    override suspend fun refreshPost(postId: String) {
        updatePostFromRemoteDataSource(postId)
    }

    override suspend fun savePost(post: Post) {
        postLocalDataSource.savePost(post)
    }

    override suspend fun deleteAllPosts() {
        postLocalDataSource.deleteAllPosts()
    }

    override suspend fun deletePost(postId: String) {
        postLocalDataSource.deletePost(postId)
    }

    private suspend fun updatePostsFromRemoteDataSource() {
        val remotePosts = postRemoteDataSource.getPosts()
        if (remotePosts is Result.Success) {
            postLocalDataSource.deleteAllPosts()
            postLocalDataSource.saveAllPosts(remotePosts.data)
        } else if (remotePosts is Result.Error) {
            throw remotePosts.exception
        }
    }

    private suspend fun updatePostFromRemoteDataSource(postId: String) {
        val remotePost = postRemoteDataSource.getPost(postId)
        if (remotePost is Result.Success) {
            postLocalDataSource.savePost(remotePost.data)
        }
    }
}
package com.namanh.composebase.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.namanh.composebase.data.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun observeAllPosts(): Flow<List<Post>>

    @Query("SELECT * FROM post")
    suspend fun getAllPosts(): List<Post>

    @Query("SELECT * FROM post WHERE id LIKE :postId")
    fun observePostById(postId: String): Flow<Post>

    @Query("SELECT * FROM post WHERE id LIKE :postId")
    suspend fun getPostById(postId: String): Post

    @Query("SELECT * FROM post WHERE title LIKE :title LIMIT 1")
    suspend fun findByTitle(title: String): Post

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(posts: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Query("DELETE FROM post")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(post: Post)
}
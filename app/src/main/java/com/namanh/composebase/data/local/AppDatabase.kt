package com.namanh.composebase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.namanh.composebase.data.model.Post
import com.namanh.composebase.utils.AppUtils

@Database(entities = [Post::class], version = AppUtils.DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
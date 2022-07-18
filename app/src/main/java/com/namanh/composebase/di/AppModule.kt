package com.namanh.composebase.di

import android.content.Context
import androidx.room.Room
import com.namanh.composebase.data.local.AppDatabase
import com.namanh.composebase.data.local.PostDao
import com.namanh.composebase.data.repository.PostRepository
import com.namanh.composebase.data.repository.PostRepositoryImpl
import com.namanh.composebase.data.remote.ApiService
import com.namanh.composebase.utils.AppUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherIO

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesApiService(): ApiService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AppUtils.BASE_URL)
            .build().create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppUtils.DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun providesPostDao(appDatabase: AppDatabase): PostDao = appDatabase.postDao()

    @DispatcherIO
    @Singleton
    @Provides
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindPostRepository(impl: PostRepositoryImpl): PostRepository
}
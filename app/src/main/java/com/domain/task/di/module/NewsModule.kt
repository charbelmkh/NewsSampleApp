package com.domain.task.di.module

import com.domain.task.news.data.local.NewsDao
import android.util.Log
import com.domain.task.news.data.remote.NewsService
import com.domain.task.core.connection.ConnectionManager
import com.domain.task.news.data.remote.NewsRemoteDataSource
import com.domain.task.news.data.NewsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NewsModule {
    companion object {
        const val TAG = "D-NewsModule"
    }


    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService {
        Log.d(TAG, "provideNewsService called")
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    fun provideNewsRemoteDataSource(
        connectionManager: ConnectionManager,
        service: NewsService
    ): NewsRemoteDataSource {
        Log.d(TAG, "provideNewsRemoteDataSource called")
        return NewsRemoteDataSource(connectionManager, service)
    }

    @Singleton
    @Provides
    fun provideNewsRepository(newsDao: NewsDao, dataSource: NewsRemoteDataSource
    ): NewsRepository {
        Log.d(TAG, "provideRestaurantsRepository called")
        return NewsRepository(newsDao, dataSource)
    }
}


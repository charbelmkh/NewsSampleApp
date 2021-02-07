package com.domain.task.di.module

import com.domain.task.news.data.local.NewsDao
import android.app.Application
import android.util.Log
import com.domain.task.Constant
import com.domain.task.core.data.local.AppDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module(includes = [CoreModule::class])
class AppModule {

    companion object {
        const val TAG = "D-AppModule"
    }



    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        Log.d(TAG, "provideRetrofitClient called")
        return Retrofit.Builder()
            .baseUrl(Constant.domain)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideNewsDaoDao(db: AppDatabase): NewsDao = db.getNewsDao()



}

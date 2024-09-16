package com.route.newsappc40gsunwed.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton
object ApiManager {
    private lateinit var retofit: Retrofit

    init {
        initRetrofit()
    }

    // Http Logging Interceptor
    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
            Log.e("API", message)
        }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()
        return okHttpClient
    }

    private fun initRetrofit() {
        retofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getNewsService(): NewsService = retofit.create(NewsService::class.java)


}
package com.route.newsappc40gsunwed.api

import android.util.Log
import com.route.newsappc40gsunwed.di.NewsAuthInterceptor
import com.route.newsappc40gsunwed.di.NewsBaseURL
import com.route.newsappc40gsunwed.di.NewsHttpLoggingInterceptor
import com.route.newsappc40gsunwed.di.NewsMapsAPIKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Singleton
// Declare Dependencies

// Inject Dependencies
@Module
@InstallIn(SingletonComponent::class)
object ApiManager {
    // Reusability
    // Non-Boiler Code => Room
    // Http Logging Interceptor  - Low Level Module
    @Provides
    @Singleton
    @NewsBaseURL
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    @NewsMapsAPIKey
    fun provideGoogleMapsApiKey(): String = "kajsdksajdsajasldk"

    @Provides
    @Singleton
    @NewsHttpLoggingInterceptor
    fun provideHttpLoggingInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
            Log.e("API", message)
        }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    @NewsAuthInterceptor
    fun provideAuthApiKeyInterceptor(): Interceptor {
        return AuthApiKeyInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @NewsAuthInterceptor authApiKeyInterceptor: Interceptor,
        @NewsHttpLoggingInterceptor httpLoggingInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authApiKeyInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        @NewsBaseURL baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun getNewsService(
        retrofit: Retrofit
    ): NewsService {
        return retrofit.create(NewsService::class.java)
    }

}
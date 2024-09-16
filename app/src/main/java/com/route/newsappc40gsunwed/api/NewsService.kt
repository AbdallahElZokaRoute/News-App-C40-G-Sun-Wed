package com.route.newsappc40gsunwed.api

import com.route.newsappc40gsunwed.api.model.Category
import com.route.newsappc40gsunwed.api.model.NewsResponse
import com.route.newsappc40gsunwed.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines/sources")
    fun getSources(
        @Query("category") categoryId: String,
        @Query("apiKey") serverApiKey: String = "8e30e66ecc364d75967401f639e6f535"
    ): Call<SourcesResponse>

    @GET("everything")
    fun getNewsBySource(
        @Query("sources") selectedSourceId: String,
        @Query("apiKey") serverApiKey: String = "8e30e66ecc364d75967401f639e6f535"
    ): Call<NewsResponse>
}

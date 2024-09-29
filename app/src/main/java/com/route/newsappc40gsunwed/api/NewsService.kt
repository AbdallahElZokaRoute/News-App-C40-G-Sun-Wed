package com.route.newsappc40gsunwed.api

import com.route.newsappc40gsunwed.api.model.NewsResponse
import com.route.newsappc40gsunwed.api.model.SourcesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") categoryId: String,
    ): Response<SourcesResponse>

    @GET("everything")
    suspend fun getNewsBySource(
        @Query("sources") selectedSourceId: String,
    ): Response<NewsResponse>
}

package com.route.newsappc40gsunwed.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthApiKeyInterceptor : Interceptor {
    val authorization = "Authorization"
    val apiKey = "8e30e66ecc364d75967401f639e6f535"
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(authorization, apiKey)
        return chain.proceed(requestBuilder.build())
    }
}

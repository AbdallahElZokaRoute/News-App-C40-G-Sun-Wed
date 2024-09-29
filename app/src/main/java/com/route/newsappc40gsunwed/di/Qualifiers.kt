package com.route.newsappc40gsunwed.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsAuthInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsHttpLoggingInterceptor


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsBaseURL

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsMapsAPIKey

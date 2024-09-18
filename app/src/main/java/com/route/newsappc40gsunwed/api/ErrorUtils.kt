package com.route.newsappc40gsunwed.api

import android.util.Log
import com.google.gson.Gson
import com.route.newsappc40gsunwed.R
import com.route.newsappc40gsunwed.api.model.SourcesResponse
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException


fun handleError(throwable: Throwable): Int {
    return when (throwable) {
        // https://wwww.newsapi.org
        is UnknownHostException, is IOException -> {
            Log.e("TAG", "handleError: ${throwable.message}")
            R.string.check_your_internet_connection
        }

        else -> {
            Log.e("TAG", "handleError: ${throwable.message}")
            Log.e("TAG", "handleError: Throwable object : $throwable")
            R.string.something_went_wrong
        }
    }
}

fun <T> handleError(response: Response<T>): Int {
    val sourceResponse = response.errorBody()?.string()?.fromJson(SourcesResponse::class.java)
    Log.e("TAG", "handleError:  message => ${sourceResponse?.message}")
    return R.string.something_went_wrong
}

fun <T> String.fromJson(clazz: Class<T>): T {
    val gson = Gson()
    return gson.fromJson(this, clazz)
}


package com.route.newsappc40gsunwed.composables.news

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.route.newsappc40gsunwed.R
import com.route.newsappc40gsunwed.api.ApiManager
import com.route.newsappc40gsunwed.api.handleError
import com.route.newsappc40gsunwed.api.model.ArticlesItem
import com.route.newsappc40gsunwed.api.model.NewsResponse
import com.route.newsappc40gsunwed.api.model.SourcesItem
import com.route.newsappc40gsunwed.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    //  Lifecycle-Aware
    val selectedSourceId = mutableStateOf("")
    val articlesList = mutableStateListOf<ArticlesItem>()
    val sourcesList = mutableStateListOf<SourcesItem>()
    val errorMessage =
        mutableIntStateOf(R.string.empty)
    val isLoading = mutableStateOf(true)
    fun getSources(
        categoryId: String,
    ) {
        ApiManager.getNewsService().getSources(categoryId)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody?.sources?.isNotEmpty() == true) {
                            sourcesList.addAll(responseBody.sources)
                        }
                    } else {
                        // Handle error
                        errorMessage.intValue = handleError(response)
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    isLoading.value = false
                    errorMessage.intValue = handleError(t)
                }
            })
    }

    fun getNewsBySource() {
        ApiManager.getNewsService().getNewsBySource(selectedSourceId.value)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful) {
                        if (responseBody?.articles?.isNotEmpty() == true) {
                            articlesList.clear()
                            articlesList.addAll(responseBody.articles)
                        }
                    } else {
                        errorMessage.intValue = handleError(response)
                    }

                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    errorMessage.intValue = handleError(t)
                }
            })
    }
}

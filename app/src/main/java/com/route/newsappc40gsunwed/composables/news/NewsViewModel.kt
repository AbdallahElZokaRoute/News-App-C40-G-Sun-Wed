package com.route.newsappc40gsunwed.composables.news

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsappc40gsunwed.R
import com.route.newsappc40gsunwed.api.ApiManager
import com.route.newsappc40gsunwed.api.NewsService
import com.route.newsappc40gsunwed.api.handleError
import com.route.newsappc40gsunwed.api.model.ArticlesItem
import com.route.newsappc40gsunwed.api.model.NewsResponse
import com.route.newsappc40gsunwed.api.model.SourcesItem
import com.route.newsappc40gsunwed.api.model.SourcesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsService: NewsService,
) : ViewModel() {
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
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                val response = newsService.getSources(categoryId)
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
            } catch (e: Exception) {
                isLoading.value = false
                errorMessage.intValue = handleError(e)
            }
        }
    }

    fun getNewsBySource() {
        viewModelScope.launch(context = Dispatchers.IO) {
            try {
                val response = newsService.getNewsBySource(selectedSourceId.value)
                val responseBody = response.body()
                if (response.isSuccessful) {
                    if (responseBody?.articles?.isNotEmpty() == true) {
                        articlesList.clear()
                        articlesList.addAll(responseBody.articles)
                    }
                } else {
                    errorMessage.intValue = handleError(response)
                }
            } catch (e: Exception) {
                isLoading.value = false
                errorMessage.intValue = handleError(e)
            }
        }
    }
}

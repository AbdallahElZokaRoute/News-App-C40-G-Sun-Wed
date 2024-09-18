package com.route.newsappc40gsunwed.composables.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.route.newsappc40gsunwed.Colors
import com.route.newsappc40gsunwed.utils.ErrorDialog
import com.route.newsappc40gsunwed.utils.NewsCard
import com.route.newsappc40gsunwed.utils.NewsScrollableTabRow
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewsScreenContent(
    categoryId: String,
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        // Background Thread -> Callbacks
//            .execute() // Main Thread (UI Thread)
        viewModel.getSources(categoryId)
    }
    Column {
        if (viewModel.sourcesList.isNotEmpty())
            NewsScrollableTabRow()
        if (viewModel.selectedSourceId.value.isNotEmpty())
            NewsLazyColumn()
    }
    ErrorDialog(errorState = viewModel.errorMessage) {
        viewModel.isLoading.value = true
        viewModel.getSources(categoryId)
    }
    if (viewModel.isLoading.value)
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Colors.green)
        }

    // MVVM UI Architecture -> Recommended By Google
}


@Composable
fun NewsLazyColumn(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = viewModel()
) {

    LaunchedEffect(viewModel.selectedSourceId.value) {
        viewModel.getNewsBySource()
    }
    LazyColumn(modifier) {
        items(viewModel.articlesList) { articleItem ->
            NewsCard(articlesItem = articleItem)
        }
    }
}


@Preview
@Composable
private fun NewsScreenContentPreview() {

}
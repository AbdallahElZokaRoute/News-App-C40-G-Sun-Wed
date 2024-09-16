package com.route.newsappc40gsunwed.utils

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.route.newsappc40gsunwed.api.model.Category

@Composable
fun CategoriesLazyVerticalGrid(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    // 360 dp
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        itemsIndexed(Category.getCategoriesList()) { index, category ->
            CategoryCard(navHostController = navHostController, category = category, index = index)
        }
    }
}

@Preview
@Composable
private fun CategoriesLazyVerticalGridPreview() {

}
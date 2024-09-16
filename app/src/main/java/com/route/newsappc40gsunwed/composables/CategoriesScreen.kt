package com.route.newsappc40gsunwed.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.route.newsappc40gsunwed.R
import com.route.newsappc40gsunwed.utils.CategoriesLazyVerticalGrid

@Composable
fun CategoriesScreenContent(navHostController: NavHostController, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.pattern),
                contentScale = ContentScale.Crop,
            )
    ) {
        Text(
            text = stringResource(R.string.pick_your_category_of_interest),
            modifier = Modifier.padding(8.dp)
        )
        CategoriesLazyVerticalGrid(navHostController)
    }
}

@Preview(showBackground = true)
@Composable
private fun CategoriesScreenContentPreview() {

}


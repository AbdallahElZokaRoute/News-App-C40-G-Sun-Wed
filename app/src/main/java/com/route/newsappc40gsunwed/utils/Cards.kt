package com.route.newsappc40gsunwed.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.route.newsappc40gsunwed.R
import com.route.newsappc40gsunwed.Routes
import com.route.newsappc40gsunwed.api.model.ArticlesItem
import com.route.newsappc40gsunwed.api.model.Category

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(articlesItem: ArticlesItem, modifier: Modifier = Modifier) {
    // Details Screen
    // Settings
    // Search
    // Drawer Sheet (Offline)
    Column(
        modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(24.dp))
    ) {
        GlideImage(
            model = articlesItem.urlToImage ?: "",
            contentDescription = stringResource(R.string.each_news_list_image),
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop,
            loading = placeholder(R.drawable.logo),

            )
        Text(text = articlesItem.source?.name ?: "", fontSize = 12.sp)
        Text(text = articlesItem.title ?: "")
        Text(text = articlesItem.publishedAt ?: "", modifier = Modifier.align(Alignment.End))
    }
}

@Composable
fun CategoryCard(
    category: Category,
    navHostController: NavHostController,
    index: Int,
    modifier: Modifier = Modifier
) {


    Card(
        onClick = {
            navHostController.navigate("${Routes.NEWS_SCREEN}/${category.apiID}")
        },
        modifier = modifier
            .padding(6.dp)
            .background(
                color = colorResource(id = category.backgroundColor),
                RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp,
                    bottomStart = if (index % 2 == 0) 24.dp else 0.dp,
                    bottomEnd = if (index % 2 == 0) 0.dp else 24.dp
                )
            ),

        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = category.drawableResId),
            contentDescription = stringResource(
                id = R.string.category_image
            ),
            modifier = Modifier
                .height(84.dp)
                .align(Alignment.CenterHorizontally), contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = category.titleResID),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.White
        )

    }
}


package com.route.newsappc40gsunwed.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.newsappc40gsunwed.Colors
import com.route.newsappc40gsunwed.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsToolbar(title: String, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title, color = Color.White)
        },
        modifier = Modifier.background(
            Colors.green,
            shape = RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp)
        ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = stringResource(
                    R.string.navigation_icon
                ),
                modifier = Modifier.padding(8.dp)
            )
        })

}

@Preview
@Composable
private fun NewsToolbarPreview() {
    NewsToolbar(title = "News App")
}
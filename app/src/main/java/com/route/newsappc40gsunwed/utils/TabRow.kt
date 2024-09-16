package com.route.newsappc40gsunwed.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.newsappc40gsunwed.Colors
import com.route.newsappc40gsunwed.api.model.SourcesItem

@Composable
fun NewsScrollableTabRow(
    sourcesList: List<SourcesItem>,
    onSourceChangedListener: (sourceId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val selectedModifier = Modifier
        .padding(4.dp)
        .background(Colors.green, CircleShape)
        .padding(horizontal = 8.dp, vertical = 6.dp)
    val unSelectedModifier =
        Modifier
            .padding(4.dp)
            .border(2.dp, Colors.green, CircleShape)
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        indicator = {},
        divider = {},
        edgePadding = 0.dp
    ) {
        sourcesList.forEachIndexed { index, sourcesItem ->
            Tab(
                selected = selectedTabIndex == index, onClick = {
                    onSourceChangedListener(sourcesItem.id ?: "")
                    selectedTabIndex = index
                }, modifier = if (selectedTabIndex == index) selectedModifier
                else unSelectedModifier
            ) {
                Text(
                    text = sourcesItem.name ?: "",
                    color = if (selectedTabIndex == index) Color.White
                    else Colors.green
                )
            }

        }
    }
}

@Preview
@Composable
private fun NewsScrollableTabRowPreview() {
    NewsScrollableTabRow(
        listOf(
            SourcesItem(name = "ABC News"),
            SourcesItem(name = "Al-Jazeera News")
        ), onSourceChangedListener = {}
    )
}
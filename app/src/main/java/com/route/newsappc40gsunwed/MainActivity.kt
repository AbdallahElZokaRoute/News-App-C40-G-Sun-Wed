package com.route.newsappc40gsunwed

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.route.newsappc40gsunwed.api.ApiManager
import com.route.newsappc40gsunwed.api.handleError
import com.route.newsappc40gsunwed.api.model.ArticlesItem
import com.route.newsappc40gsunwed.api.model.Category
import com.route.newsappc40gsunwed.api.model.NewsResponse
import com.route.newsappc40gsunwed.api.model.SourcesItem
import com.route.newsappc40gsunwed.api.model.SourcesResponse
import com.route.newsappc40gsunwed.ui.theme.NewsAppC40GSunWedTheme
import com.route.newsappc40gsunwed.utils.ErrorDialog
import com.route.newsappc40gsunwed.utils.NewsCard
import com.route.newsappc40gsunwed.utils.NewsNavigationComponent
import com.route.newsappc40gsunwed.utils.NewsScrollableTabRow
import com.route.newsappc40gsunwed.utils.NewsToolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    // Create a new Project -> News App (APIs + Networking )
    // 1- Handling Error API
    // 2- Interceptor -> API Key  ( Http Logging Interceptor )
    // 3 - MVVM UI Architecture Pattern
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppC40GSunWedTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    val navController = rememberNavController()
                    val title = remember {
                        mutableStateOf("News App")
                    }
                    NewsToolbar(title.value)
                }) { innerPadding ->
                    NewsNavigationComponent(Modifier.padding(innerPadding))
                }
            }
        }
    }
}


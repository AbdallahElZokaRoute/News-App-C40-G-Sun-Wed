package com.route.newsappc40gsunwed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.route.newsappc40gsunwed.ui.theme.NewsAppC40GSunWedTheme
import com.route.newsappc40gsunwed.utils.NewsNavigationComponent
import com.route.newsappc40gsunwed.utils.NewsToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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


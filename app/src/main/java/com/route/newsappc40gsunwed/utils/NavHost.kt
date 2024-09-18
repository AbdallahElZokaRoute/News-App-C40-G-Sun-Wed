package com.route.newsappc40gsunwed.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.route.newsappc40gsunwed.Routes
import com.route.newsappc40gsunwed.composables.CategoriesScreenContent
import com.route.newsappc40gsunwed.composables.news.NewsScreenContent

@Composable
fun NewsNavigationComponent(modifier: Modifier = Modifier) {
    // NavHost <=> FrameLayout
    // NavController => Component that Navigates
    val navHostController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Routes.CATEGORIES_SCREEN
    ) {
        composable(Routes.CATEGORIES_SCREEN) {
            CategoriesScreenContent(navHostController)
        }
        composable(
            "${Routes.NEWS_SCREEN}/{categoryId}", arguments = listOf(navArgument(
                "categoryId"
            ) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")

            NewsScreenContent(categoryId!!)
        }
    }
}
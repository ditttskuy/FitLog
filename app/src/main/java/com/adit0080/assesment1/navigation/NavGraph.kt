package com.adit0080.assesment1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adit0080.assesment1.ui.screen.DetailScreen
import com.adit0080.assesment1.ui.screen.MainScreen


@Composable
fun SetupnavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }

        composable(route = Screen.Detail.route) {
            DetailScreen(navController=navController)
        }

        composable(route = Screen.DetailUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_KALORI){
                    type = NavType.LongType
                }
            )){
                navbackStackEntry ->
            val id = navbackStackEntry.arguments?.getLong(KEY_ID_KALORI)?:0
            DetailScreen(navController=navController , id = id )

        }

    }
}
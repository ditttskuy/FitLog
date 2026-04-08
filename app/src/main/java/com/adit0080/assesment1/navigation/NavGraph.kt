package com.adit0080.assesment1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adit0080.assesment1.ui.screen.MainScreen
import com.adit0080.assesment1.ui.screen.ResultScreen

@Composable
fun SetupnavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }

        composable(route = "${Screen.Result.route}/{kalori}",arguments = listOf(
            navArgument("kalori") { type = NavType.IntType }
        )

        ) { backStackEntry ->
            val angkaKalori = backStackEntry.arguments?.getInt("kalori")?:0
            ResultScreen(navController, kalori = angkaKalori)
        }


    }
}
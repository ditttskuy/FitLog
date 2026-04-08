package com.adit0080.assesment1.navigation

sealed class Screen (val route: String){
    data object Home: Screen("mainScreen")
    data object Result: Screen("resultScreen")
}
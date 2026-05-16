package com.adit0080.assesment1.navigation

const val KEY_ID_KALORI = "idKalori"

sealed class Screen (val route: String){
    data object Home: Screen("mainScreen")

    data object Detail: Screen("detailScreen")

    data object DetailUbah: Screen("detailScreen/{$KEY_ID_KALORI}")

    fun withId(id : Long) = "detailScreen/$id"
}

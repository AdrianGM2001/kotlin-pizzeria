package com.adrgon.pizzeria.data.navigation

sealed class Screen(val route: String) {
    object Registro : Screen("registro")
    object Login : Screen("login")
    object Home : Screen("home")
}

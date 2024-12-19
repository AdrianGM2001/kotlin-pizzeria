package com.adrgon.pizzeria.data.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.adrgon.pizzeria.data.repositories.ClienteRepository
import com.adrgon.pizzeria.data.repositories.ProductoRepository
import com.adrgon.pizzeria.ui.home.HomeScreen
import com.adrgon.pizzeria.ui.home.HomeViewModel
import com.adrgon.pizzeria.ui.login.LoginScreen
import com.adrgon.pizzeria.ui.login.LoginViewModel
import com.adrgon.pizzeria.ui.registro.RegistroScreen
import com.adrgon.pizzeria.ui.registro.RegistroViewModel

@Composable
fun AppNavigation(navController: NavHostController, clienteRepository: ClienteRepository, productoRepository: ProductoRepository) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route // Pantalla inicial
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = LoginViewModel(clienteRepository),
                navController = navController
            )
        }
        composable(Screen.Registro.route) {
            RegistroScreen(
                viewModel = RegistroViewModel(clienteRepository),
                navController = navController
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = HomeViewModel(productoRepository),
                navController = navController
            )
        }
    }
}

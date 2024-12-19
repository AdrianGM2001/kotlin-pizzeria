package com.adrgon.pizzeria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.adrgon.pizzeria.data.navigation.AppNavigation
import com.adrgon.pizzeria.data.network.RetrofitInstance
import com.adrgon.pizzeria.data.repositories.ClienteRepository
import com.adrgon.pizzeria.data.repositories.ProductoRepository
import com.adrgon.pizzeria.ui.home.Home
import com.adrgon.pizzeria.ui.home.HomeScreen
import com.adrgon.pizzeria.ui.home.HomeViewModel
import com.adrgon.pizzeria.ui.login.Login
import com.adrgon.pizzeria.ui.login.LoginViewModel
import com.adrgon.pizzeria.ui.registro.Registro
import com.adrgon.pizzeria.ui.registro.RegistroViewModel
import com.adrgon.pizzeria.ui.theme.PizzeriaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PizzeriaTheme {
                AppNavigation(rememberNavController(), ClienteRepository(RetrofitInstance.clienteApi), ProductoRepository(RetrofitInstance.productoApi))
            }
        }
    }
}

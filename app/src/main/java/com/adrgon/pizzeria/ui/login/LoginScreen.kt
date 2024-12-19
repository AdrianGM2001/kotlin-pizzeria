package com.adrgon.pizzeria.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.adrgon.pizzeria.R
import com.adrgon.pizzeria.data.model.LoginDTO
import com.adrgon.pizzeria.data.navigation.Screen
import com.adrgon.pizzeria.ui.componentes.OutlinedTextFieldPizzeria
import kotlinx.coroutines.launch

private const val emailLabel = "Email"
private const val emailPlaceholder = "Introduce tu email"
private const val passwordLabel = "Contraseña"
private const val passwordPlaceholder = "Introduce tu contraseña"

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavController
) {
    val cliente: LoginDTO by viewModel.login.observeAsState(LoginDTO())
    val loginFinalizado: Boolean by viewModel.loginFinalizado.observeAsState(false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)

    Login(cliente, loginFinalizado, isLoading, viewModel::onClienteChange, viewModel::onClickLogin, navController)
}

@Composable
fun Login(
    cliente: LoginDTO,
    loginFinalizado: Boolean,
    isLoading: Boolean,
    onClienteChange: (LoginDTO) -> Unit,
    onClickLogin: suspend () -> Boolean,
    navController: NavController
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo Pizzeria"
            )

            OutlinedTextFieldPizzeria(
                value = cliente.email,
                onValueChange = { onClienteChange(cliente.copy(email = it)) },
                label = emailLabel,
                placeholder = emailPlaceholder,
                keyboardType = KeyboardType.Email
            )

            OutlinedTextFieldPizzeria(
                value = cliente.password,
                onValueChange = { onClienteChange(cliente.copy(password = it)) },
                label = passwordLabel,
                placeholder = passwordPlaceholder,
                keyboardType = KeyboardType.Password
            )

            Button(
                onClick = {
                    scope.launch {
                        if (onClickLogin())
                            navController.navigate(Screen.Home.route)
                        else
                            Toast.makeText(context, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                enabled = loginFinalizado
            ) {
                Text("Entrar")
            }

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment =
                Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            TextButton(
                onClick = { navController.navigate(Screen.Registro.route) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Registrarse")
            }
        }
    }
}

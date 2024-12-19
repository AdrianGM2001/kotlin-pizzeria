package com.adrgon.pizzeria.ui.registro

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
import com.adrgon.pizzeria.data.model.ClienteDTO
import com.adrgon.pizzeria.data.navigation.Screen
import com.adrgon.pizzeria.ui.componentes.OutlinedTextFieldPizzeria
import kotlinx.coroutines.launch

private const val nombreLabel = "Nombre"
private const val nombrePlaceholder = "Introduce tu nombre"
private const val dniLabel = "DNI"
private const val dniPlaceholder = "Introduce tu DNI"
private const val direccionLabel = "Dirección"
private const val direccionPlaceholder = "Introduce tu dirección"
private const val telefonoLabel = "Teléfono"
private const val telefonoPlaceholder = "Introduce tu teléfono"
private const val emailLabel = "Email"
private const val emailPlaceholder = "Introduce tu email"
private const val passwordLabel = "Contraseña"
private const val passwordPlaceholder = "Introduce tu contraseña"

@Composable
fun RegistroScreen(
    viewModel: RegistroViewModel,
    navController: NavController
) {
    val cliente: ClienteDTO by viewModel.cliente.observeAsState(ClienteDTO())
    val registroFinalizado: Boolean by viewModel.registroFinalizado.observeAsState(false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    val errorMensaje: ErrorMessage? by viewModel.error.observeAsState(null)
    Registro(cliente, registroFinalizado, isLoading, errorMensaje, viewModel::onClienteChange, viewModel::onClickRegistrar, navController)
}

@Composable
fun Registro(
    cliente: ClienteDTO,
    registroFinalizado: Boolean,
    isLoading: Boolean,
    errorMensaje: ErrorMessage?,
    onClienteChange: (ClienteDTO) -> Unit,
    onClickRegistrar: suspend () -> Boolean,
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
                value = cliente.nombre,
                onValueChange = { onClienteChange(cliente.copy(nombre = it)) },
                label = nombreLabel,
                placeholder = nombrePlaceholder,
                error = errorMensaje?.nombre
            )

            OutlinedTextFieldPizzeria(
                value = cliente.dni,
                onValueChange = { onClienteChange(cliente.copy(dni = it)) },
                label = dniLabel,
                placeholder = dniPlaceholder
            )

            OutlinedTextFieldPizzeria(
                value = cliente.direccion,
                onValueChange = { onClienteChange(cliente.copy(direccion = it)) },
                label = direccionLabel,
                placeholder = direccionPlaceholder
            )

            OutlinedTextFieldPizzeria(
                value = cliente.telefono,
                onValueChange = { onClienteChange(cliente.copy(telefono = it)) },
                label = telefonoLabel,
                placeholder = telefonoPlaceholder,
                keyboardType = KeyboardType.Phone
            )

            OutlinedTextFieldPizzeria(
                value = cliente.email,
                onValueChange = { onClienteChange(cliente.copy(email = it)) },
                label = emailLabel,
                placeholder = emailPlaceholder,
                keyboardType = KeyboardType.Email,
                error = errorMensaje?.email
            )


            OutlinedTextFieldPizzeria(
                value = cliente.password,
                onValueChange = { onClienteChange(cliente.copy(password = it)) },
                label = passwordLabel,
                placeholder = passwordPlaceholder,
                error = errorMensaje?.password,
                keyboardType = KeyboardType.Password
            )

            Button(
                onClick = {
                    scope.launch {
                        if (onClickRegistrar())
                            navController.navigate(Screen.Home.route)
                        else
                            Toast.makeText(context, "Error al registrar", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                enabled = registroFinalizado
            ) {
                Text("Registrarse")
            }

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment =
                Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            TextButton(
                onClick = { navController.navigate(Screen.Login.route) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Iniciar sesión")
            }
        }
    }
}

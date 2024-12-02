package com.adrgon.pizzeria.ui.registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.adrgon.pizzeria.R
import com.adrgon.pizzeria.data.ClienteDTO
import com.adrgon.pizzeria.ui.componentes.OutlinedTextFieldPizzeria

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
fun Registro(viewModel: RegistroViewModel)  {
    val cliente: ClienteDTO by viewModel.cliente.observeAsState(ClienteDTO())
    val registroFinalizado: Boolean by viewModel.registroFinalizado.observeAsState(false)
    val errorMensaje: ErrorMessage? by viewModel.error.observeAsState(null)

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
                onValueChange = { viewModel.onClienteChange(cliente.copy(nombre = it)) },
                label = nombreLabel,
                placeholder = nombrePlaceholder,
                error = errorMensaje?.nombre
            )

            OutlinedTextFieldPizzeria(
                value = cliente.dni,
                onValueChange = { viewModel.onClienteChange(cliente.copy(dni = it)) },
                label = dniLabel,
                placeholder = dniPlaceholder
            )

            OutlinedTextFieldPizzeria(
                value = cliente.direccion,
                onValueChange = { viewModel.onClienteChange(cliente.copy(direccion = it)) },
                label = direccionLabel,
                placeholder = direccionPlaceholder
            )

            OutlinedTextFieldPizzeria(
                value = cliente.telefono,
                onValueChange = { viewModel.onClienteChange(cliente.copy(telefono = it)) },
                label = telefonoLabel,
                placeholder = telefonoPlaceholder,
                keyboardType = KeyboardType.Phone
            )

            OutlinedTextFieldPizzeria(
                value = cliente.email,
                onValueChange = { viewModel.onClienteChange(cliente.copy(email = it)) },
                label = emailLabel,
                placeholder = emailPlaceholder,
                keyboardType = KeyboardType.Email,
                error = errorMensaje?.email
            )


            OutlinedTextFieldPizzeria(
                value = cliente.password,
                onValueChange = { viewModel.onClienteChange(cliente.copy(password = it)) },
                label = passwordLabel,
                placeholder = passwordPlaceholder,
                error = errorMensaje?.password,
                keyboardType = KeyboardType.Password
            )

            Button(
                onClick = { viewModel.registrar() },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                enabled = registroFinalizado
            ) {
                Text("Registrarse")
            }
        }
    }
}

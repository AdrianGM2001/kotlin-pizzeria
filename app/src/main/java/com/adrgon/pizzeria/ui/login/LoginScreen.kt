package com.adrgon.pizzeria.ui.login

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

private const val emailLabel = "Email"
private const val emailPlaceholder = "Introduce tu email"
private const val passwordLabel = "Contraseña"
private const val passwordPlaceholder = "Introduce tu contraseña"

@Composable
fun Login(viewModel: LoginViewModel)  {
    val cliente: ClienteDTO by viewModel.cliente.observeAsState(ClienteDTO())
    val loginFinalizado: Boolean by viewModel.loginFinalizado.observeAsState(false)

    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(R.drawable.logomalo),
                contentDescription = "Logo Pizzeria"
            )

            OutlinedTextFieldPizzeria(
                value = cliente.email,
                onValueChange = { viewModel.onClienteChange(cliente.copy(email = it)) },
                label = emailLabel,
                placeholder = emailPlaceholder,
                keyboardType = KeyboardType.Email
            )

            OutlinedTextFieldPizzeria(
                value = cliente.password,
                onValueChange = { viewModel.onClienteChange(cliente.copy(password = it)) },
                label = passwordLabel,
                placeholder = passwordPlaceholder,
                keyboardType = KeyboardType.Password
            )

            Button(
                onClick = { viewModel.entrar() },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                enabled = loginFinalizado
            ) {
                Text("Entrar")
            }
        }
    }
}

package com.adrgon.pizzeria.ui.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedTextFieldPizzeria(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    error: String? = null
) {
    var mostrarPassword by remember { mutableStateOf(false) }
    val esPassword = keyboardType == KeyboardType.Password
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder)},
        visualTransformation = if (esPassword && !mostrarPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        singleLine = true,
        trailingIcon = {
            if (esPassword) {
                IconButton(onClick = { mostrarPassword = !mostrarPassword }) {
                    Icon(
                        imageVector = if (mostrarPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Mostrar contraseña"
                    )
                }
            }
        }
    )

    if (error != null) {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = error,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun TextConBordeEstiloPixelado(texto: String) {
    TextoSombra(texto, 1)
    TextoSombra(texto, -1)
    TextoSombra(texto, y = 1)
    TextoSombra(texto, y = -1)
    Text(
        text = texto,
        style = MaterialTheme.typography.titleLarge,
        color = Color.White,
    )
}

@Composable
private fun TextoSombra(texto: String, x: Int = 0, y: Int = 0) {
    Text(
        text = texto,
        style = MaterialTheme.typography.titleLarge,
        color = Color.Black,
        modifier = Modifier
            .offset(x.dp, y.dp)
            .alpha(0.75f)
    )
}

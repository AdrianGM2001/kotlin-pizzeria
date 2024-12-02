package com.adrgon.pizzeria.ui.registro

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.adrgon.pizzeria.data.ClienteDTO
import com.adrgon.pizzeria.utilidades.Validar

class RegistroViewModel {
    val cliente = MutableLiveData(ClienteDTO())
    val error = MutableLiveData<ErrorMessage?>(null)
    val registroFinalizado = MutableLiveData(false)

    private val mensajeErrorNombre = "El nombre debe contener solo letras."
    private val mensajeErrorEmail = "Correo electrónico no es válido."
    private val mensajeErrorPassword = "La contraseña debe tener al menos 4 caracteres."

    fun onClienteChange(cliente: ClienteDTO) {
        this.cliente.value = cliente

        val errorNombre = if (Validar.validarNombre(cliente.nombre)) mensajeErrorNombre else null
        val errorEmail = if (Validar.validarEmail(cliente.email)) mensajeErrorEmail else null
        val errorPassword = if (Validar.validarPassword(cliente.password)) mensajeErrorPassword else null

        error.value = ErrorMessage(errorNombre, errorEmail, errorPassword)

        registroFinalizado.value = listOf(
            cliente.nombre,
            cliente.dni,
            cliente.direccion,
            cliente.telefono,
            cliente.email,
            cliente.password
        ).all { it.isNotBlank() } &&
                listOf(
                    errorNombre,
                    errorEmail,
                    errorPassword
                ).all { it == null }
    }

    fun registrar() = Log.d("RegistroViewModel", "Cliente: ${cliente.value}")
}
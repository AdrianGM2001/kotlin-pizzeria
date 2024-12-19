package com.adrgon.pizzeria.ui.registro

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrgon.pizzeria.data.model.ClienteDTO
import com.adrgon.pizzeria.data.repositories.ClienteRepository
import com.adrgon.pizzeria.utilidades.Validar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistroViewModel(private val clienteRepository: ClienteRepository) : ViewModel() {
    val cliente = MutableLiveData(ClienteDTO())
    val error = MutableLiveData<ErrorMessage?>(null)
    val registroFinalizado = MutableLiveData(false)
    val isLoading = MutableLiveData(false)

    private val mensajeErrorNombre = "El nombre debe contener solo letras."
    private val mensajeErrorEmail = "Correo electrónico no es válido."
    private val mensajeErrorPassword = "La contraseña debe tener al menos 4 caracteres."

    fun onClienteChange(cliente: ClienteDTO) {
        this.cliente.value = cliente

        val errorNombre = if (Validar.validarNombre(cliente.nombre) || cliente.nombre.isBlank()) null else mensajeErrorNombre
        val errorEmail = if (Validar.validarEmail(cliente.email) || cliente.email.isBlank()) null else mensajeErrorEmail
        val errorPassword = if (Validar.validarPassword(cliente.password) || cliente.password.isBlank()) null else mensajeErrorPassword

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

    suspend fun onClickRegistrar(): Boolean {
        isLoading.value = true
        var clienteActual = cliente.value
        if (clienteActual != null) {
            val result = clienteRepository.registrarCliente(clienteActual)
            isLoading.value = false
            clienteActual = result.getOrNull()
        } else {
            isLoading.value = false
            cliente.value = null
        }
        return clienteActual != null
    }
}
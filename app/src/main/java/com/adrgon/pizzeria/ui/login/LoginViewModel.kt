package com.adrgon.pizzeria.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adrgon.pizzeria.data.model.ClienteDTO
import com.adrgon.pizzeria.data.model.LoginDTO
import com.adrgon.pizzeria.data.repositories.ClienteRepository

class LoginViewModel(private val clienteRepository: ClienteRepository) : ViewModel() {
    val cliente = MutableLiveData(ClienteDTO())
    val login = MutableLiveData(LoginDTO())
    val loginFinalizado = MutableLiveData(false)
    val isLoading = MutableLiveData(false)


    fun onClienteChange(login: LoginDTO) {
        this.login.value = login
        loginFinalizado.value = login.email.isNotEmpty() && login.password.isNotEmpty()
    }

    suspend fun onClickLogin(): Boolean {
        isLoading.value = true
        val loginActual = login.value
        if (loginActual != null) {
            val result = clienteRepository.logearCliente(loginActual)
            isLoading.value = false
            cliente.value = result.getOrNull()
        } else {
            isLoading.value = false
            cliente.value = null
        }
        return cliente.value != null
    }
}
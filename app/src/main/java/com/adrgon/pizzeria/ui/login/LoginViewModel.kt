package com.adrgon.pizzeria.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.adrgon.pizzeria.data.ClienteDTO

class LoginViewModel {
    val cliente = MutableLiveData(ClienteDTO())
    val loginFinalizado = MutableLiveData(false)


    fun onClienteChange(cliente: ClienteDTO) {
        this.cliente.value = cliente
        loginFinalizado.value = cliente.email.isNotEmpty() && cliente.password.isNotEmpty()
    }

    fun entrar() = Log.d("LoginViewModel", "Cliente: ${cliente.value}")
}
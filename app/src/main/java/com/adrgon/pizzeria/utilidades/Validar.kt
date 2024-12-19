package com.adrgon.pizzeria.utilidades

import android.util.Patterns

class Validar {
    companion object {
        fun validarNombre(nombre: String) = nombre.matches("[a-zA-Z]+".toRegex())

        fun validarEmail(email: String) = email.matches(Patterns.EMAIL_ADDRESS.pattern().toRegex())

        fun validarPassword(password: String) = password.length >= 4
    }
}
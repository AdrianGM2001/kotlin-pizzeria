package com.adrgon.pizzeria.utilidades

import android.util.Patterns

public class Validar {
    companion object {
        public fun validarNombre(nombre: String) = nombre.matches("[a-zA-Z]+".toRegex())

        public fun validarEmail(email: String) = email.matches(Patterns.EMAIL_ADDRESS.pattern().toRegex())

        public fun validarPassword(password: String) = password.length >= 4
    }
}
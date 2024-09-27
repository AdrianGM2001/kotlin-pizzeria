package com.adrgon.practica1

import  com.adrgon.practica1.modelo.ClienteDTO
import com.adrgon.practica1.modelo.IngredienteDTO
import com.adrgon.practica1.modelo.PizzaDTO

class Controlador {
    fun filtrarPizzas(pizzas: List<PizzaDTO>, min: Float = 7f, max: Float = 10f): List<PizzaDTO> = pizzas.filter { it.precio in min..max }

    fun filtrarIngredientes(alergenos: List<String>, ingredientes: List<IngredienteDTO>) = ingredientes.filter { it.alergenos.none { jt -> alergenos.contains(jt) } }

    fun ordenarPizzas(pizzas: List<PizzaDTO>, orden: ORDEN) = when(orden) {
            ORDEN.ASCENDENTE -> pizzas.sortedBy { it.nombre }
            else -> pizzas.sortedByDescending { it.nombre }
    }

    fun contarPizzasConIngrediente(pizzas: List<PizzaDTO>, ingrediente: IngredienteDTO) = pizzas.count { it.ingredientes.contains(ingrediente) }
}

enum class ORDEN { ASCENDENTE, DESCENDENTE }

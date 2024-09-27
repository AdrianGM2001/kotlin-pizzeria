package com.adrgon.practica1.modelo

data class PizzaDTO (
    val id: Int,
    val nombre: String,
    val precio: Float,
    val size: SIZE,
    val ingredientes: List<IngredienteDTO>
) {
    override fun toString(): String {
        return nombre
    }
}

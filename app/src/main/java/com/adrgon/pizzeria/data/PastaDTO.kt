package com.adrgon.practica1.modelo

data class PastaDTO (
    val id: Int,
    val nombre: String,
    val precio: Float,
    val ingredientes: List<IngredienteDTO>
)

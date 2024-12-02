package com.adrgon.pizzeria.data

data class ProductoDTO(
    val id: Int = 0,
    val nombre: String,
    var precio: Double,
    val ingredientes: List<IngredienteDTO> = emptyList(),
    val tipo: TIPO
)

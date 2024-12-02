package com.adrgon.practica1.modelo

data class LineaPedidoDTO (
    val id: Int,
    val cantidad: Int,
    val pizza: PizzaDTO?,
    val pasta: PastaDTO?,
    val bebida: BebidaDTO?
)

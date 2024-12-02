package com.adrgon.pizzeria.data

data class ClienteDTO (
    val id: Int? = null,
    val dni: String = "",
    var nombre: String = "",
    val direccion: String = "",
    val telefono: String = "",
    val email: String = "",
    val password: String = "",
    val listaPedidos:List<PedidoDTO> = listOf()
)

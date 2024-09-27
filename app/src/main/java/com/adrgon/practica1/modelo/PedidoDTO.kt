package com.adrgon.practica1.modelo

import java.util.Date

data class PedidoDTO (
    val id: Int,
    val fecha: Date,
    val precioTotal: Float,
    val estadoPedido: ESTADO_PEDIDO,
    val lineaPedidos: List<LineaPedidoDTO>
)

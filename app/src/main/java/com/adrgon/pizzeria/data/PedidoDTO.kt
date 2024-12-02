package com.adrgon.pizzeria.data

import java.util.Date

data class PedidoDTO (
    val id: Int = 0,
    val fecha: Date = Date(),
    val precioTotal: Float = 0.0f,
    val estadoPedido: ESTADO_PEDIDO = ESTADO_PEDIDO.PENDIENTE,
    val lineaPedidos: MutableList<LineaPedidoDTO> = mutableListOf()
) {
    fun addLineaPedido(lp: LineaPedidoDTO) = lineaPedidos.add(lp)
}

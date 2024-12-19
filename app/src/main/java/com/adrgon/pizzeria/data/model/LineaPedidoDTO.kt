package com.adrgon.pizzeria.data.model

data class LineaPedidoDTO (
    val id: Int,
    val cantidad: Int,
    val producto: ProductoDTO,
    val size: Size?
) {
    override fun toString(): String {
        return "[" + "id: " + id + ", cantidad: " + cantidad + ", producto " + producto.nombre + ", tamaño " + size + "]"
    }
}

package com.adrgon.pizzeria.data

data class LineaPedidoDTO (
    val id: Int,
    val cantidad: Int,
    val producto: ProductoDTO,
    val size: SIZE?
) {
    override fun toString(): String {
        return "[" + "id: " + id + ", cantidad: " + cantidad + ", producto " + producto.nombre + ", tamaño " + size + "]"
    }
}

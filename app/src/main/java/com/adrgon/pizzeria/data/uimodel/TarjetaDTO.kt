package com.adrgon.pizzeria.data.uimodel

import com.adrgon.pizzeria.data.model.ProductoDTO
import com.adrgon.pizzeria.data.model.Size
import com.adrgon.pizzeria.data.model.Tipo

data class TarjetaDTO(
    var cantidad: Int = 1,
    val producto: ProductoDTO,
    var size: Size? = null,
    // Es un ID único que además de proporcionar la imagen, se utilizará para identificar la tarjeta
    // de manera unívoca. ¿Es una chapuza? Puede ser, pero si el compilador de Kotlin te da limones
    // haz limonada.
    val idImagen: Int,
    var expanded: Boolean = false,
    var habilitarQuitar: Boolean = false,
    var habilitarAnyadir: Boolean = true,
    var habilitarCarrito: Boolean = producto.tipo == Tipo.PASTA || size != null,
    var tieneIngredientes: Boolean = producto.ingredientes.isNotEmpty(),
    var tieneSize: Boolean = producto.tipo != Tipo.PASTA
)

package com.adrgon.pizzeria.data

data class TarjetaDTO(
    var cantidad: Int = 1,
    val producto: ProductoDTO,
    var size: SIZE? = null,
    // Es un ID único que además de proporcionar la imagen, se utilizará para identificar la tarjeta
    // de manera unívoca. ¿Es una chapuza? Puede ser, pero si el compilador de Kotlin te da limones
    // haz limonada.
    val idImagen: Int,
    var expanded: Boolean = false,
    var habilitarQuitar: Boolean = false,
    var habilitarAnyadir: Boolean = true,
    var habilitarCarrito: Boolean = producto.tipo == TIPO.PASTA || size != null,
    var tieneIngredientes: Boolean = producto.ingredientes.isNotEmpty(),
    var tieneSize: Boolean = producto.tipo != TIPO.PASTA
)

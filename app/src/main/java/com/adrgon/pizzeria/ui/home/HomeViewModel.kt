package com.adrgon.pizzeria.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adrgon.pizzeria.R
import com.adrgon.pizzeria.data.IngredienteDTO
import com.adrgon.pizzeria.data.LineaPedidoDTO
import com.adrgon.pizzeria.data.PedidoDTO
import com.adrgon.pizzeria.data.ProductoDTO
import com.adrgon.pizzeria.data.TIPO
import com.adrgon.pizzeria.data.TarjetaDTO

class HomeViewModel {
    private val _tarjetas: MutableLiveData<List<TarjetaDTO>> = MutableLiveData()
    val tarjetas: LiveData<List<TarjetaDTO>> = _tarjetas

    private val _totalProductos: MutableLiveData<Int> = MutableLiveData(0)
    val totalProductos: LiveData<Int> = _totalProductos

    private val pedido: PedidoDTO = PedidoDTO()

    private var _tarjetasPizza: MutableLiveData<List<TarjetaDTO>> = MutableLiveData()
    private val _tarjetasPasta: MutableLiveData<List<TarjetaDTO>> = MutableLiveData()
    private val _tarjetasBebida: MutableLiveData<List<TarjetaDTO>> = MutableLiveData()

    init {
        cargarProductos()
    }

    private fun cargarProductos() {
        // Ingredientes
        val ingredienteMozzarella = IngredienteDTO(nombre = "Mozzarella", alergenos = listOf("Lactosa", "Gluten"))
        val ingredienteParmesano = IngredienteDTO(nombre = "Parmesano", alergenos = listOf("Lactosa"))
        val ingredienteGorgonzola = IngredienteDTO(nombre = "Gorgonzola", alergenos = listOf("Lactosa"))
        val ingredienteRicotta = IngredienteDTO(nombre = "Ricotta", alergenos = listOf("Lactosa"))
        val ingredienteTomate = IngredienteDTO(nombre = "Tomate")
        val ingredienteAceitunas = IngredienteDTO(nombre = "Aceitunas negras", alergenos = listOf("Sulfitos"))
        val ingredientePinya = IngredienteDTO(nombre = "Piña", alergenos = listOf("Sulfitos"))
        val ingredientePimiento = IngredienteDTO(nombre = "Pimiento", alergenos = listOf("Apio"))
        val ingredienteCebolla = IngredienteDTO(nombre = "Cebolla")
        val ingredienteChampinyones = IngredienteDTO(nombre = "Champiñones")
        val ingredientePepperoni = IngredienteDTO(nombre = "Pepperoni", alergenos = listOf("Sulfitos"))
        val ingredientePollo = IngredienteDTO(nombre = "Pollo", alergenos = listOf("Mostaza"))
        val ingredienteBacon = IngredienteDTO(nombre = "Bacon", alergenos = listOf("Sulfitos"))
        val ingredienteSalchicha = IngredienteDTO(nombre = "Salchicha italiana", alergenos = listOf("Lactosa", "Sulfitos"))
        val ingredienteJamon = IngredienteDTO(nombre = "Jamón", alergenos = listOf("Lactosa", "Sulfitos"))
        val ingredienteMacarrones = IngredienteDTO(nombre = "Macarrones", alergenos = listOf("Gluten"))
        val ingredienteEspaguetis = IngredienteDTO(nombre = "Espaguetis", alergenos = listOf("Gluten"))
        val ingredienteTernera = IngredienteDTO(nombre = "Ternera")
        val ingredienteQueso = IngredienteDTO(nombre = "Queso", alergenos = listOf("Lactosa"))

        _tarjetasPizza.value = listOf(
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "Hot-N-Ready Pepperoni",
                    precio = 10.99,
                    tipo = TIPO.PIZZA,
                    ingredientes = listOf(ingredientePepperoni, ingredienteMozzarella, ingredienteTomate, ingredienteCebolla)
                ),
                idImagen = R.drawable.pipsa1
            ),
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "Hot-N-Ready Cheese",
                    precio = 10.99,
                    tipo = TIPO.PIZZA,
                    ingredientes = listOf(ingredienteMozzarella, ingredienteParmesano, ingredienteRicotta, ingredienteGorgonzola ,ingredienteTomate)
                ),
                idImagen = R.drawable.pipsa2
            ),
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "Sweet N Spicy Chicken",
                    precio = 16.2,
                    tipo = TIPO.PIZZA,
                    ingredientes = listOf(ingredientePollo, ingredienteMozzarella, ingredienteCebolla, ingredienteTomate)
                ),
                idImagen = R.drawable.pipsa3
            ),
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "BBQ Chicken", precio = 16.2, tipo = TIPO.PIZZA, ingredientes = listOf(ingredientePollo, ingredienteMozzarella, ingredienteBacon, ingredienteCebolla, ingredienteTomate)
                ),
                idImagen = R.drawable.pipsa4
            ),
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "3 Meat Treat",
                    precio = 18.5,
                    tipo = TIPO.PIZZA,
                    ingredientes = listOf(ingredientePepperoni, ingredienteSalchicha, ingredienteBacon, ingredienteMozzarella, ingredienteTomate)
                ),
                idImagen = R.drawable.pipsa5
            ),
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "Hula Hawaiian",
                    precio = 16.2,
                    tipo = TIPO.PIZZA,
                    ingredientes = listOf(ingredienteJamon, ingredientePinya, ingredienteMozzarella, ingredienteTomate)
                ),
                idImagen = R.drawable.pipsa6
            ),
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "Ultimate Supreme",
                    precio = 18.5,
                    tipo = TIPO.PIZZA,
                    ingredientes = listOf(ingredientePepperoni, ingredienteSalchicha, ingredienteBacon, ingredientePimiento, ingredienteChampinyones, ingredienteMozzarella, ingredienteTomate)
                ),
                idImagen = R.drawable.pipsa7
            ),
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "Veggie", precio = 16.2, tipo = TIPO.PIZZA, ingredientes = listOf(ingredientePimiento, ingredienteCebolla, ingredienteAceitunas, ingredienteChampinyones, ingredienteTomate, ingredienteMozzarella)
                ),
                idImagen = R.drawable.pipsa8
            )
        )

        _tarjetas.value = _tarjetasPizza.value

        _tarjetasPasta.value = listOf(
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "Macarrones del mediterráneo",
                    precio = 6.5,
                    tipo = TIPO.PASTA,
                    ingredientes = listOf(ingredienteMacarrones, ingredientePimiento, ingredienteAceitunas, ingredienteQueso)
                ),
                idImagen = R.drawable.pasta1
            ),
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "Espaguetis con tomate y cebolla",
                    precio = 7.5,
                    tipo = TIPO.PASTA,
                    ingredientes = listOf(ingredienteEspaguetis, ingredienteTomate, ingredienteCebolla)
                ),
                idImagen = R.drawable.pasta2
            ),
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "Espaguetis con tomate",
                    precio = 5.5,
                    tipo = TIPO.PASTA,
                    ingredientes = listOf(ingredienteEspaguetis, ingredienteTomate, ingredienteQueso)
                ),
                idImagen = R.drawable.pasta3
            ),
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "Espaguetis con tomate, salchicha italiana y queso",
                    precio = 5.5,
                    tipo = TIPO.PASTA,
                    ingredientes = listOf(ingredienteEspaguetis, ingredienteTomate, ingredienteQueso, ingredienteSalchicha)
                ),
                idImagen = R.drawable.pasta4
            ),
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "Espaguetis con tomate, ternera y queso",
                    precio = 5.5,
                    tipo = TIPO.PASTA,
                    ingredientes = listOf(ingredienteEspaguetis, ingredienteTomate, ingredienteQueso, ingredienteTernera)
                ),
                idImagen = R.drawable.pasta5
            ),
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "Espaguetis con albóndigas",
                    precio = 5.5,
                    tipo = TIPO.PASTA,
                    ingredientes = listOf(ingredienteEspaguetis, ingredienteTernera, ingredienteTomate, ingredienteQueso),
                ),
                idImagen = R.drawable.pasta6
            )
        )

        _tarjetasBebida.value = listOf(
            TarjetaDTO(
                producto = ProductoDTO(nombre = "Coca-Cola", precio = 1.5, tipo = TIPO.BEBIDA),
                idImagen = R.drawable.cocacola
            ),
            TarjetaDTO(
                producto = ProductoDTO(nombre = "Fanta de naranja", precio = 1.5, tipo = TIPO.BEBIDA),
                idImagen = R.drawable.fanta
            ),
            TarjetaDTO(
                producto = ProductoDTO(nombre = "Cerveza", precio = 1.5, tipo = TIPO.BEBIDA),
                idImagen = R.drawable.cerveza
            ),
            TarjetaDTO(
                producto = ProductoDTO(nombre = "Cerveza sin alcohol", precio = 1.5, tipo = TIPO.BEBIDA),
                idImagen = R.drawable.cervezasin
            ),
            TarjetaDTO(
                producto = ProductoDTO(nombre = "RedBull", precio = 1.5, tipo = TIPO.BEBIDA),
                idImagen = R.drawable.redbull
            ),
            TarjetaDTO(
                producto = ProductoDTO(nombre = "RedBull zero", precio = 1.5, tipo = TIPO.BEBIDA),
                idImagen = R.drawable.redbullzero
            ),
            TarjetaDTO(
                producto = ProductoDTO(
                    nombre = "RedBull sin azúcar",
                    precio = 1.5,
                    tipo = TIPO.BEBIDA
                ),
                idImagen = R.drawable.redbullsinazucar
            ),
        )
    }

    fun onTarjetaChange(tarjeta: TarjetaDTO) {
        tarjeta.habilitarQuitar = tarjeta.cantidad != 1
        tarjeta.habilitarAnyadir = tarjeta.cantidad != 99
        tarjeta.habilitarCarrito = tarjeta.producto.tipo == TIPO.PASTA || tarjeta.size != null

        _tarjetas.value = tarjetas.value?.map { if (it.idImagen == tarjeta.idImagen) tarjeta else it }
    }

    fun onCategoriaChange(tipo: TIPO) {
        _tarjetas.value = when (tipo) {
            TIPO.PIZZA -> _tarjetasPizza.value
            TIPO.PASTA -> _tarjetasPasta.value
            else -> _tarjetasBebida.value
        }
    }

    fun onAddCarrito(tarjeta: TarjetaDTO) {
        val lineaPedido = LineaPedidoDTO(
            pedido.lineaPedidos.size,
            tarjeta.cantidad,
            tarjeta.producto,
            tarjeta.size
        )

        pedido.addLineaPedido(lineaPedido)
        _totalProductos.value = pedido.lineaPedidos.sumOf { it.cantidad }

        Log.d("HomeViewModel", lineaPedido.toString())

        onTarjetaChange(
            tarjeta.copy(
                cantidad = 1,
                size = null
            )
        )
    }

    fun imagenAlergeno(alergeno: String) = when (alergeno) {
        "Lactosa" -> R.drawable.lacteos
        "Sulfitos" -> R.drawable.sulfitos
        "Gluten" -> R.drawable.cereales
        "Huevo" -> R.drawable.huevos
        "Pescado" -> R.drawable.pescado
        "Cacahuetes" -> R.drawable.cacahuetes
        "Soja" -> R.drawable.soja
        "Frutos secos" -> R.drawable.frutossecos
        "Apio" -> R.drawable.apio
        "Mostaza" -> R.drawable.mostaza
        "Granos de sésamo" -> R.drawable.sesamo
        "Altramuces" -> R.drawable.altramuz
        "Moluscos" -> R.drawable.moluscos
        else -> R.drawable.crustaceos
    }
}

package com.adrgon.pizzeria.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrgon.pizzeria.R
import com.adrgon.pizzeria.data.model.LineaPedidoDTO
import com.adrgon.pizzeria.data.model.PedidoDTO
import com.adrgon.pizzeria.data.model.ProductoDTO
import com.adrgon.pizzeria.data.repositories.ProductoRepository
import com.adrgon.pizzeria.data.model.Tipo
import com.adrgon.pizzeria.data.uimodel.TarjetaDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val productoRepository: ProductoRepository) : ViewModel() {
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
        viewModelScope.launch {
            val result = productoRepository.obtenerProductos()
            withContext(Dispatchers.Main) {
                if (result.isSuccess) {
                    val productos = result.getOrThrow()
                    _tarjetasPizza.value = productos.filter { it.tipo == Tipo.PIZZA }.map {
                        TarjetaDTO(
                            producto = it,
                            idImagen = imagenProducto(it)
                        )
                    }
                    _tarjetasPasta.value = productos.filter { it.tipo == Tipo.PASTA }.map {
                        TarjetaDTO(
                            producto = it,
                            idImagen = imagenProducto(it)
                        )
                    }
                    _tarjetasBebida.value = productos.filter { it.tipo == Tipo.BEBIDA }.map {
                        TarjetaDTO(
                            producto = it,
                            idImagen = imagenProducto(it)
                        )
                    }
                    _tarjetas.value =
                        _tarjetasPizza.value // Asignar las pizzas a la pantalla por defecto
                } else {
                        Log.d("HOME", "Error:$result")
                }
            }
        }
    }

    fun onTarjetaChange(tarjeta: TarjetaDTO) {
        tarjeta.habilitarQuitar = tarjeta.cantidad != 1
        tarjeta.habilitarAnyadir = tarjeta.cantidad != 99
        tarjeta.habilitarCarrito = tarjeta.producto.tipo == Tipo.PASTA || tarjeta.size != null

        _tarjetas.value = tarjetas.value?.map { if (it.idImagen == tarjeta.idImagen) tarjeta else it }
    }

    fun onCategoriaChange(tipo: Tipo) {
        _tarjetas.value = when (tipo) {
            Tipo.PIZZA -> _tarjetasPizza.value
            Tipo.PASTA -> _tarjetasPasta.value
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

    // TODO: obtener las imagenes desde el servidor
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

    fun imagenProducto(producto: ProductoDTO) = when (producto.nombre) {
        "Hot-N-Ready Pepperoni" -> R.drawable.pipsa1
        "Hot-N-Ready Cheese" -> R.drawable.pipsa2
        "Sweet N Spicy Chicken" -> R.drawable.pipsa3
        "BBQ Chicken" -> R.drawable.pipsa4
        "3 Meat Treat" -> R.drawable.pipsa5
        "Hula Hawaiian" -> R.drawable.pipsa6
        "Ultimate Supreme" -> R.drawable.pipsa7
        "Veggie" -> R.drawable.pipsa8
        "Macarrones del mediterráneo" -> R.drawable.pasta1
        "Espaguetis con tomate y cebolla" -> R.drawable.pasta2
        "Espaguetis con tomate" -> R.drawable.pasta3
        "Espaguetis con tomate, salchicha italiana y queso" -> R.drawable.pasta4
        "Espaguetis con tomate, ternera y queso" -> R.drawable.pasta5
        "Espaguetis con albóndigas" -> R.drawable.pasta6
        "Coca-Cola" -> R.drawable.cocacola
        "Fanta de naranja" -> R.drawable.fanta
        "Cerveza" -> R.drawable.cerveza
        "Cerveza sin alcohol" -> R.drawable.cervezasin
        "RedBull" -> R.drawable.redbull
        "RedBull zero" -> R.drawable.redbullzero
        "RedBull sin azúcar" -> R.drawable.redbullsinazucar
        else -> R.drawable.applogo
    }
}

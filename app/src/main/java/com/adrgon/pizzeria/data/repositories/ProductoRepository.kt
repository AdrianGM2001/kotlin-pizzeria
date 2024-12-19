package com.adrgon.pizzeria.data.repositories

import com.adrgon.pizzeria.data.model.ProductoDTO
import com.adrgon.pizzeria.data.network.ProductoApiService

class ProductoRepository(private val apiService: ProductoApiService) {
    suspend fun obtenerProductos(): Result<List<ProductoDTO>> {
        return try {
            val response = apiService.getProductos()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
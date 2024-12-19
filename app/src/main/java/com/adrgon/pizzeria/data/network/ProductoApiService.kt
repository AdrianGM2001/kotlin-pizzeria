package com.adrgon.pizzeria.data.network

import com.adrgon.pizzeria.data.model.ProductoDTO
import retrofit2.http.GET

interface ProductoApiService {
    @GET("/productos")
    suspend fun getProductos(): List<ProductoDTO>
}
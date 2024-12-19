package com.adrgon.pizzeria.data.repositories

import com.adrgon.pizzeria.data.model.ClienteDTO
import com.adrgon.pizzeria.data.model.LoginDTO
import com.adrgon.pizzeria.data.network.ClienteApiService

class ClienteRepository(private val apiService: ClienteApiService) {
    suspend fun registrarCliente(cliente: ClienteDTO): Result<ClienteDTO> {
        return try {
            val response = apiService.registrarCliente(cliente)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun logearCliente(cliente: LoginDTO): Result<ClienteDTO> {
        return try {
            val response = apiService.loginCliente(cliente)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
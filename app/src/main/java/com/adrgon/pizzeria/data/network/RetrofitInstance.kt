package com.adrgon.pizzeria.data.network

import com.adrgon.pizzeria.data.model.Tipo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

object RetrofitInstance {
    //private const val BASE_URL = "https://pizzeria-restapi.onrender.com/"
    private const val BASE_URL = "https://render-deploy-nodejs-main.onrender.com"

    // Objeto Gson con un TypeAdapter para el tipo de dato Tipo
    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(Tipo::class.java, TipoAdapter())
        .create()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    val clienteApi: ClienteApiService by lazy {
        retrofit.create(ClienteApiService::class.java)
    }

    val productoApi: ProductoApiService by lazy {
        retrofit.create(ProductoApiService::class.java)
    }
}

// Transforma el tipo de dato de la respuesta JSON a un objeto de tipo Tipo
class TipoAdapter : JsonDeserializer<Tipo> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Tipo {
        val tipoString = json.asString
        return Tipo.entries.first { it.tipo == tipoString }
    }
}

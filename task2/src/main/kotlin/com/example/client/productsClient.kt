package com.example.client

import com.example.model.Response
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.*

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*

suspend fun gettingData(): Response {

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    val response = client.get("https://dummyjson.com/products")

    return Gson().fromJson(response.bodyAsText(), Response::class.java)
}
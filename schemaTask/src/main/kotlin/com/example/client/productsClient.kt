package com.example.client

import com.example.model.Response
import com.example.model.SchemeModel
import com.example.utils.BaseResponse
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*

suspend fun getData(): Array<SchemeModel> {

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    val response = client.get("https://api.mfapi.in/mf")


    return Gson().fromJson(response.bodyAsText(), Array<SchemeModel>::class.java)
}
suspend fun getDataByID(id:String): BaseResponse<Response> {
    val client = HttpClient(){
        install(ContentNegotiation){
            gson()
        }
    }

    val response = client.get("https://api.mfapi.in/mf/${id}")
    val body = Gson().fromJson(response.bodyAsText(),Response::class.java)

    return body?.let {
        BaseResponse.SuccessResponse(data = it)
    }?:BaseResponse.ErrorResponse(message = "id didn't found")
}
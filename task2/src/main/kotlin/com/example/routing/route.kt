package com.example.routing

import com.example.client.gettingData
import com.example.model.QueryRequest
import com.example.model.updateProducts
import com.example.repository.ProductsRepository
import com.example.repository.ProductsRepositoryImpl
import com.example.utils.BaseResponse
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.routes(){

    val repository:ProductsRepository = ProductsRepositoryImpl()

    routing {

        get("/data") {
            val list = gettingData()
            val result = repository.storeData(list)
            call.respond(result)

        }
        post("/search") {
            val queryRequest = call.receive<QueryRequest>()
            val query = queryRequest.query
            if (query.isNotEmpty()) {

            val searchResults = repository.searchByTitleOrDescription(query)

            call.respond(searchResults)
            } else {
                call.respond(BaseResponse.ErrorResponse(data = null, message = "category parameter is missing."))
            }
        }
        post("/SearchCategory") {
            val request = call.receive<QueryRequest>()
            val query = request.query

            if (query.isNotEmpty()) {
                val product = repository.searchByCategory(query)
                call.respond(product)
            } else {
                call.respond(BaseResponse.ErrorResponse(data = null, message = "category parameter is missing."))
            }

        }

        delete("/deleteCategory") {

            val request = call.receive<QueryRequest>()
            val query = request.query


            if (query.isNotEmpty()) {
                val deletedCount = repository.deleteByCategory(query)
                call.respond(deletedCount)

            } else {
                call.respond(BaseResponse.ErrorResponse(data = null, message = "category parameter is missing."))
            }

        }
        post("/update") {

            val request = call.receive<updateProducts>()
            val result = repository.updateProduct(request)

            call.respond(result)

        }

    }
}
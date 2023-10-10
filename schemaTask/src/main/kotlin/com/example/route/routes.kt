package com.example.route

import com.example.model.FilterRequest
import com.example.model.SchemeRequest
import com.example.repository.SchemeRepository
import com.example.repository.SchemeRepositoryImpl
import com.example.service.FilterSchemeId
import com.example.service.StoreAllScheme
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*

fun Application.route() {

    val repo:SchemeRepository = SchemeRepositoryImpl()


    routing {

        route("/Scheme") {
            get("/data"){
                val result = StoreAllScheme().getScheme()
                call.respond(result)

            }
            post("/search"){

                val request = call.receive<SchemeRequest>()
                val result = repo.searchScheme(request)

                call.respond(result)

            }
            post("/filter"){
                val request = call.receive<FilterRequest>()
                call.respond(FilterSchemeId().filter(request))
            }

        }
    }
}
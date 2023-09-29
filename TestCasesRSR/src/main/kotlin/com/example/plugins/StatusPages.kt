package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configStatusPages(){

    install(StatusPages) {
        exception<Throwable> { call: ApplicationCall, cause -> call.respond(HttpStatusCode.InternalServerError, cause.localizedMessage)  }
    }
}
package com.example

import com.example.db.DBClass
import com.example.plugins.*
import com.example.route.route
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*

fun main() {

    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

}

fun Application.module() {

    configContentNegotiation()
    configStatusPages()
    DBClass.init()
    route()

}

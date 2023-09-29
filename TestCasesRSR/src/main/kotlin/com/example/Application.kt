package com.example

import com.example.DB.DBClass
import com.example.model.Request
import com.example.plugins.configContentNegotiation
import com.example.plugins.configStatusPages
import com.example.repository.UserRepositoryImpl
import com.example.route.route
import com.example.service.UserServiceImpl

import io.ktor.server.application.*
import io.ktor.server.tomcat.*
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

import io.ktor.server.engine.embeddedServer


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

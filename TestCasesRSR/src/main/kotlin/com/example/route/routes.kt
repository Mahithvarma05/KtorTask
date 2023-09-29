package com.example.route

import com.example.model.DUser
import com.example.model.Request
import com.example.repository.UserRepository
import com.example.repository.UserRepositoryImpl

import com.example.service.UserServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.route(){

    val repository: UserRepository = UserRepositoryImpl()

    routing {

        route("/users") {

            get("/{id}") {

                val userId = call.parameters["id"]?.toIntOrNull()
                if (userId == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
                    return@get
                }

                val result = repository.getUser(userId)

                if (result != null) {

                    call.respond(result)
                } else {
                    call.respond(HttpStatusCode.NotFound, "User not found")
                }
            }

            post("/insert") {

                val user = call.receive<Request>()
                repository.registerUser(user)

                call.respond(HttpStatusCode.Created)
            }

            post("/delete") {

                val uId = call.receive<DUser>()
                repository.deleteUser(uId.id)

                call.respond(HttpStatusCode.Created)
            }
            post("/see"){
                val user = Request("myNamess","myBio00")
                val id = UserServiceImpl().registerUser(user)

                val pre = id
                println(pre)
                val getUser = UserServiceImpl().getUser(id.toString().toInt())


                println(getUser)

            }
        }
    }
}
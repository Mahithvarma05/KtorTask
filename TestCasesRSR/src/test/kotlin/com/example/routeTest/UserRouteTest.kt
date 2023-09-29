package com.example.routeTest

import com.example.model.Request
import io.ktor.client.request.*
import io.ktor.server.testing.*
import org.testng.annotations.Test

import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.Json

import kotlin.test.assertEquals

class UserRouteTest {
    @Test
    fun testGetUser()= testApplication {
        val user= Request("mahith","code")
        val response=client.get("/users/1"){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        assertEquals(HttpStatusCode.OK,response.status)
        val responseStudent= Json.decodeFromString<Request>(response.bodyAsText())
        assertEquals(user,responseStudent)
    }
    @Test
    fun testPostInsert()= testApplication {
        val user = Request("mahi","test")
        val response=client.post("/users/insert"){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        assertEquals(HttpStatusCode.OK,response.status)
        val responseUser= Json.decodeFromString<Request>(response.bodyAsText())
        assertEquals(user,responseUser)
    }
}
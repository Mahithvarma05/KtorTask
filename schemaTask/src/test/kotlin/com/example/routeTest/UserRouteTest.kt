package com.example.routeTest

import com.example.model.Request
import com.example.model.SchemeModel
import io.ktor.client.request.*
import io.ktor.server.testing.*

import io.ktor.client.statement.*
import io.ktor.http.*

import kotlinx.serialization.json.Json
import kotlin.test.Test

import kotlin.test.assertEquals

class UserRouteTest {
    @Test
    fun testGet()= testApplication {

        val response=client.get("/Scheme/data"){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        val responseScheme= Json.decodeFromString<Array<SchemeModel>>(response.bodyAsText())
        assertEquals(HttpStatusCode.OK,response.status)


    }
    @Test
    fun testPostSearch()= testApplication {

        val response=client.post("/Scheme/search"){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        assertEquals(HttpStatusCode.OK,response.status)

    }
    @Test
    fun testPostFilter()= testApplication {

        val response=client.post("/Scheme/filter"){
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        assertEquals(HttpStatusCode.OK,response.status)

    }
}
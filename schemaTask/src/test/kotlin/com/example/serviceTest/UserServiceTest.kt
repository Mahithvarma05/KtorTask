package com.example.serviceTest


import com.example.db.DBClass
import com.example.db.SchemeTable
import com.example.model.FilterRequest
import com.example.model.Request
import com.example.service.FilterSchemeId
import com.example.service.StoreAllScheme
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import java.sql.Connection
import kotlin.test.Test
import kotlin.test.assertEquals

class UserServiceTest {
    @Before
    fun setup() {
        DBClass.init()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ

        transaction {
            SchemaUtils.create(SchemeTable)
        }
    }

    @After
    fun tearDown() {
        transaction {
            SchemaUtils.drop(SchemeTable)
        }
    }

    @Test
    fun testFilterSchemeID() {

        runBlocking {


            val request = Request("107", "1M")
            val fRequest = FilterRequest(request)
            val result = FilterSchemeId().filter(fRequest)
            assertEquals(HttpStatusCode.OK, result.status)

        }

    }


    @Test
    fun testStoreAllScheme() {

        runBlocking {

            val service = StoreAllScheme().getScheme()
            assertEquals(HttpStatusCode.OK, service.status)
        }

    }
}


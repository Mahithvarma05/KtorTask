package com.example.repositoryTest

import com.example.db.DBClass
import com.example.db.SchemeTable
import com.example.model.*
import com.example.repository.SchemeRepositoryImpl
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

class UserRepositoryTest {

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
    fun testInsertScheme(){

        runBlocking {

            val repository=SchemeRepositoryImpl()
            val model = SchemeModel("1007","schemeName")
            val result = repository.insertScheme(model)

            assertEquals(HttpStatusCode.OK,result.status)


        }
    }

    @Test
    fun testSearchScheme(){

        runBlocking {
            val repository=SchemeRepositoryImpl()
            val sName = SearchScheme("adithya")
            val request = SchemeRequest(sName)
            val result = repository.searchScheme(request)

            assertEquals(HttpStatusCode.OK,result.status)
        }

    }

}
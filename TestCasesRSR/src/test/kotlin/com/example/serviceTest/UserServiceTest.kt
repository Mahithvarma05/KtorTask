package com.example.serviceTest

import com.example.DB.DBClass
import com.example.entity.UserProfiles
import com.example.entity.Users
import com.example.model.Request
import com.example.service.UserServiceImpl
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.testng.annotations.Test
import java.sql.Connection
import kotlin.test.assertEquals

class UserServiceTest {
    @Before
    fun setup() {
        DBClass.init()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ

        transaction {
            SchemaUtils.create(Users,UserProfiles)
        }
    }

    @After
    fun tearDown() {
        transaction {
            SchemaUtils.drop(Users,UserProfiles)
        }
    }

    @Test
    fun testRegisterUser(){

        runBlocking {

            val service=UserServiceImpl()
            val user = Request("myName","myBio")
            val id = service.registerUser(user)

            val getUser = service.getUser(id.toString().toInt())
            if (getUser!=null) {
                val (user1, userProf) = getUser
                assertEquals(user1.name,user.name)
                assertEquals(userProf.bio,user.bio)
            }

        }
    }

    @Test
    fun testDeleteUser(){

        runBlocking {
            val service=UserServiceImpl()
            val user = Request("myName","myBio")
            val id = service.registerUser(user)

            val num = service.deleteUser(id.toString().toInt())
            assertEquals(1,num)
        }

    }
}


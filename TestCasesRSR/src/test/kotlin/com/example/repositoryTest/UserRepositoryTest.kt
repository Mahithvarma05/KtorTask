package com.example.repositoryTest

import com.example.DB.DBClass
import com.example.entity.UserProfiles
import com.example.entity.Users
import com.example.model.Request
import com.example.model.User
import com.example.repository.UserRepository
import com.example.repository.UserRepositoryImpl
import com.example.service.UserService
import com.example.service.UserServiceImpl
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.testng.annotations.Test
import java.sql.Connection
import kotlin.test.assertEquals

class UserRepositoryTest {

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

            val repository=UserServiceImpl()
            val user = Request("myName","myBio")
            val id = repository.registerUser(user)

            val getUser = repository.getUser(id.toString().toInt())
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
            val repository=UserServiceImpl()
            val user = Request("myName","myBio")
            val id = repository.registerUser(user)

            val num = repository.deleteUser(id.toString().toInt())
            assertEquals(1,num)
        }

    }

}
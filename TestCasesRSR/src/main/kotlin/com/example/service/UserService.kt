package com.example.service

import com.example.model.Request
import com.example.model.User
import com.example.model.UserProfile


interface UserService {
    suspend fun registerUser(user : Request): Any?

    suspend fun getUser(id:Int):Pair<User, UserProfile>?

    suspend fun deleteUser(uId:Int):Int?

}
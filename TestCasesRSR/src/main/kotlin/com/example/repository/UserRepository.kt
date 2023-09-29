package com.example.repository

import com.example.model.Request
import com.example.model.User
import com.example.model.UserProfile
import com.example.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(user : Request): BaseResponse<Any>?

    suspend fun getUser(id:Int):BaseResponse<Any>?

    suspend fun deleteUser(uId:Int):BaseResponse<Any>?
}
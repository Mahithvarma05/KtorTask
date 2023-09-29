package com.example.repository

import com.example.model.Request
import com.example.service.UserService
import com.example.service.UserServiceImpl
import com.example.utils.BaseResponse

class UserRepositoryImpl() :UserRepository {

    private val userService:UserService = UserServiceImpl()

    override suspend fun registerUser(user: Request): BaseResponse<Any>? {

        val result = userService.registerUser(user)

        return BaseResponse.SuccessResponse(data = result)
    }

    override suspend fun getUser(id: Int): BaseResponse<Any>? {

        val result = userService.getUser(id)

        return if (result!=null) BaseResponse.SuccessResponse(data = result)
        else BaseResponse.ErrorResponse(message = "id not found")
    }

    override suspend fun deleteUser(uId: Int): BaseResponse<Any>? {

        val result = userService.deleteUser(uId)

        return if (result!=null) BaseResponse.SuccessResponse(data = result)
        else BaseResponse.ErrorResponse(message = "id not found")
    }
}
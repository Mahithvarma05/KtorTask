package com.example.repository

import com.example.model.SchemeModel
import com.example.model.SchemeRequest
import com.example.utils.BaseResponse

interface SchemeRepository {

    suspend fun insertScheme(model : SchemeModel): BaseResponse<Any>
    suspend fun searchScheme(request: SchemeRequest):BaseResponse<Any>
}
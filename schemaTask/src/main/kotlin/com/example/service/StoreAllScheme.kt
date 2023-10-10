package com.example.service

import com.example.client.getData
import com.example.repository.SchemeRepository
import com.example.repository.SchemeRepositoryImpl
import com.example.utils.BaseResponse

class StoreAllScheme {
    suspend  fun getScheme(): BaseResponse<Any> {

        val repo: SchemeRepository = SchemeRepositoryImpl()

        val result = getData()

            result.forEach {
                repo.insertScheme(it)

            }

        return BaseResponse.SuccessResponse(data = result)
    }
}
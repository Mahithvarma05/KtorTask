package com.example.repository

import com.example.model.Response
import com.example.model.updateProducts
import com.example.utils.BaseResponse

interface ProductsRepository {

    suspend fun storeData(list: Response):BaseResponse<Any>

    suspend fun searchByTitleOrDescription(query:String):BaseResponse<Any>

    suspend fun searchByCategory(query:String):BaseResponse<Any>

    suspend fun deleteByCategory(query:String):BaseResponse<Any>

    suspend fun updateProduct(request: updateProducts):BaseResponse<Any>


}
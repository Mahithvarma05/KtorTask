package com.example.repository

import com.example.model.Response
import com.example.model.updateProducts
import com.example.service.*
import com.example.utils.BaseResponse

class ProductsRepositoryImpl:ProductsRepository {

    override suspend fun storeData(list: Response): BaseResponse<Any> {

        val msg: String = if (list.products.isNotEmpty()) {
            StoreDataService().storeData(list)
        } else {
            "Data is empty"
        }
        return  BaseResponse.SuccessResponse(message = msg)
    }

    override suspend fun searchByTitleOrDescription(query: String): BaseResponse<Any> {

        val product = SearchByTorDService().searchProduct(query)

        return if(product.isEmpty()) BaseResponse.ErrorResponse(message = "Data not found")

        else BaseResponse.SuccessResponse(data = product)
    }

    override suspend fun searchByCategory(query: String): BaseResponse<Any> {

        val product = SearchByCategoryService().searchProduct(query)

        return if(product.isEmpty()) BaseResponse.ErrorResponse(message = "Data not found")

        else BaseResponse.SuccessResponse(data = product)
    }

    override suspend fun deleteByCategory(query: String): BaseResponse<Any> {

        val count = DeleteByCategoryService().deleteCategory(query)

        return if (count>0) BaseResponse.SuccessResponse(data = count)

        else BaseResponse.ErrorResponse(message = "category not found")
    }

    override suspend fun updateProduct(request: updateProducts): BaseResponse<Any> {

        return  if (request.id>0)

            BaseResponse.SuccessResponse(message = UpdateService().updateProduct(request))

        else
            BaseResponse.ErrorResponse(message = "Invalid id")


    }
}
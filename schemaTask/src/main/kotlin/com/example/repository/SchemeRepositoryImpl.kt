package com.example.repository

import com.example.db.SchemeTable
import com.example.model.SchemeModel
import com.example.model.SchemeRequest
import com.example.utils.BaseResponse
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class SchemeRepositoryImpl:SchemeRepository{
    override suspend fun insertScheme(model: SchemeModel): BaseResponse<Any> {

        transaction {
            SchemeTable.insert {
                it[schemeName] = model.schemeName
                it[schemeCode] = model.schemeCode
            }
        }
     return BaseResponse.SuccessResponse(message = "Inserted")
    }

    override suspend fun searchScheme(request: SchemeRequest): BaseResponse<Any> {

        val list = transaction {
            SchemeTable.select{ Op.build {
                (SchemeTable.schemeName like "%${request.request.scheme_name}%")
            }
            }.map {

                val schemeCode = it[SchemeTable.schemeCode]
                val schemeName = it[SchemeTable.schemeName]

                SchemeModel(schemeCode,schemeName)
            }
        }
        return BaseResponse.SuccessResponse(data = list)
    }

}
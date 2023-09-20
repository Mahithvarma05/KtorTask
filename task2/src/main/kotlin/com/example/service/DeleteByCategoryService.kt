package com.example.service

import com.example.db.ProductTable
import com.example.db.ImagesTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class DeleteByCategoryService {

     fun deleteCategory(query:String):Int{

        val count = transaction {

            ImagesTable.deleteWhere {
                PId inList ProductTable.select { ProductTable.category eq query }
                    .map { it[ProductTable.id] }
            }


            ProductTable.deleteWhere { category eq query }
        }
        return count
    }
}
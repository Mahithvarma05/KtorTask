package com.example.service

import com.example.db.ProductTable
import com.example.model.updateProducts
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UpdateService {

     fun updateProduct(request:updateProducts):String{

      val msg =  transaction {
            val existingProduct = ProductTable.select { ProductTable.id eq request.id }.singleOrNull()

            if (existingProduct != null){
                if (!request.title.isNullOrBlank()) {
                    ProductTable.update({ ProductTable.id eq request.id }) {
                        it[title] = request.title
                    }
                }
                if (!request.description.isNullOrBlank()) {
                    ProductTable.update({ ProductTable.id eq request.id }) {
                        it[description] = request.description
                    }
                }
                if (!request.price.isNullOrBlank()) {
                    ProductTable.update({ ProductTable.id eq request.id }) {
                        it[price] = request.price
                    }
                }

                if (!request.discountPercentage.isNullOrBlank()) {
                    ProductTable.update({ ProductTable.id eq request.id }) {
                        it[discountPercentage] = request.discountPercentage
                    }
                }

                if (!request.rating.isNullOrBlank()) {
                    ProductTable.update({ ProductTable.id eq request.id }) {
                        it[rating] = request.rating
                    }
                }

                if (!request.stock.isNullOrBlank()) {
                    ProductTable.update({ ProductTable.id eq request.id }) {
                        it[stock] = request.stock
                    }
                }

                if (!request.brand.isNullOrBlank()) {
                    ProductTable.update({ ProductTable.id eq request.id }) {
                        it[brand] = request.brand
                    }
                }

                if (!request.category.isNullOrBlank()) {
                    ProductTable.update({ ProductTable.id eq request.id }) {
                        it[category] = request.category
                    }
                }

                if (!request.thumbnail.isNullOrBlank()) {
                    ProductTable.update({ ProductTable.id eq request.id }) {
                        it[thumbnail] = request.thumbnail
                    }
                }
                "Product updated successfully"
            }else{
                "Product not found"
            }



        }
        return msg

    }
}
package com.example.service

import com.example.db.ProductTable
import com.example.db.ImagesTable
import com.example.model.ProductResponce

import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class SearchByCategoryService {

     fun searchProduct(query: String):List<ProductResponce>{

            val product = transaction {
                (ProductTable innerJoin ImagesTable)
                    .slice(
                        ProductTable.id,
                        ProductTable.title,
                        ProductTable.description,
                        ProductTable.price,
                        ProductTable.discountPercentage,
                        ProductTable.rating,
                        ProductTable.stock,
                        ProductTable.brand,
                        ProductTable.category,
                        ProductTable.thumbnail,
                        ImagesTable.image
                    )
                    .select { ProductTable.category eq query }.map {

                        val id = it[ProductTable.id]
                        val title = it[ProductTable.title]
                        val description = it[ProductTable.description]
                        val price = it[ProductTable.price]
                        val discountPercentage = it[ProductTable.discountPercentage]
                        val rating = it[ProductTable.rating]
                        val stock = it[ProductTable.stock]
                        val brand = it[ProductTable.brand]
                        val category = it[ProductTable.category]
                        val thumbnail = it[ProductTable.thumbnail]
                        val images = it[ImagesTable.image]

                        ProductResponce(
                            id,
                            title,
                            description,
                            price,
                            discountPercentage,
                            rating,
                            stock,
                            brand,
                            category,
                            thumbnail,
                            images
                        )


                    }
            }
        return product

    }
}
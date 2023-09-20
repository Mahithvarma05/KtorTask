package com.example.service

import com.example.db.ProductTable
import com.example.db.ImagesTable
import com.example.model.Response
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class StoreDataService {

     fun storeData(list:Response):String{

        val msg = transaction {
            for (product in list.products) {
                ProductTable.insert {
                    it[id] = product.id
                    it[title] = product.title
                    it[description] = product.description
                    it[price] = product.price
                    it[discountPercentage] = product.discountPercentage
                    it[rating] = product.rating
                    it[stock] = product.stock
                    it[brand] = product.brand
                    it[category] = product.category
                    it[thumbnail] = product.thumbnail
                }
                for (images in product.images) {
                    ImagesTable.insert {
                        it[PId] = product.id
                        it[image] = images
                    }
                }


            }
            "Updated Successfully"
        }
        return msg

    }
}
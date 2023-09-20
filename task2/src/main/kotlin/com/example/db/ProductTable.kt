package com.example.db

import org.jetbrains.exposed.sql.Table

object ProductTable:Table("ProductTable") {
    val id = integer("id")
    val title = varchar("title",256)
    val description = varchar("description",256)
    val price = varchar("price",256)
    val discountPercentage = varchar("discountPercentage",256)
    val rating = varchar("rating",256)
    val stock = varchar("stock",256)
    val brand = varchar("brand",256)
    val category = varchar("category",256)
    val thumbnail = varchar("thumbnail",256)
    override val primaryKey = PrimaryKey(id)

}
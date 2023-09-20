package com.example.db

import org.jetbrains.exposed.sql.Table

object ImagesTable:Table("imagesTable") {
    val PId = reference("PId",ProductTable.id)
    val image = varchar("image",256)
}
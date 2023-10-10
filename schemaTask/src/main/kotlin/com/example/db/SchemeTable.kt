package com.example.db

import org.jetbrains.exposed.sql.Table

object SchemeTable: Table( "scheme_table"){

    val schemeCode = varchar("scheme_code", 256)
    val schemeName = varchar("scheme_name",256)

}
package com.example.model


data class SchemeModel(
    val schemeCode:String,
    val schemeName:String
)

data class SchemeRequest(
    val request:SearchScheme
)

data class SearchScheme(
    val scheme_name:String
)

data class FilterRequest(
    val request:Request
)

data class Request(
    val schemeID:String,
    val filter:String
)

data class Response(
    val meta:Meta,
    var data:List<Data>
)

data class Meta(
    val fund_house:String,
    val scheme_type:String,
    val scheme_category:String,
    val scheme_code:String,
    val scheme_name:String
)

data class Data(
    val date:String,
    val nav:String
)
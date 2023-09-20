package com.example.model

data class updateProducts (
    val id:Int,
    val title:String?,
    val description:String?,
    val price:String?,
    val discountPercentage:String?,
    val rating:String?,
    val stock:String?,
    val brand:String?,
    val category:String?,
    val thumbnail:String?,
    val images:List<String>?
)
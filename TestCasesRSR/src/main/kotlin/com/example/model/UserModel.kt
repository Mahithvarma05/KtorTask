package com.example.model

data class Request(
    val name:String,
    val bio:String
)

data class User(
    val name: String
)

data class DUser(
    val id:Int
)

data class UserProfile(
    val id: Int,
    val bio: String
)
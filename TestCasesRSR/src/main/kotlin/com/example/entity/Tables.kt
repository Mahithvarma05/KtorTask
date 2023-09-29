package com.example.entity

import com.example.model.User
import com.example.model.UserProfile
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object Users : IntIdTable("userForID") {
    val name = varchar("name", 50)
}

fun ResultRow.toUser() = User(this[Users.name])


object UserProfiles : IntIdTable("userProfff") {
    val userId = reference("user_id", Users)
    val bio = text("bio")
}

fun ResultRow.toUserProfile() = UserProfile(this[UserProfiles.id].value, this[UserProfiles.bio])
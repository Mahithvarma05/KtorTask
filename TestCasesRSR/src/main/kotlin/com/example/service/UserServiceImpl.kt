package com.example.service

import com.example.*
import com.example.entity.UserProfiles
import com.example.entity.Users
import com.example.entity.toUser
import com.example.entity.toUserProfile
import com.example.model.Request
import com.example.model.User
import com.example.model.UserProfile
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.SqlExpressionBuilder.inList
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserServiceImpl : UserService {
    override suspend fun registerUser(user: Request): Any {

        val result = transaction {
            val userId = Users.insertAndGetId {
                it[name] = user.name
            }
            UserProfiles.insertAndGetId {
                it[this.userId] = userId.value
                it[bio] = user.bio
            }
            userId
        }
        return result
    }

    override suspend fun getUser(id: Int): Pair<User, UserProfile>? {

        val result: Pair<User, UserProfile>? = transaction {
            Users.innerJoin(UserProfiles)
                .select { Users.id eq id }
                .map { row -> row.toUser() to row.toUserProfile() }
                .singleOrNull()
        }
        return result
    }

    override suspend fun deleteUser(uId:Int):Int?{

        val num = transaction {

            UserProfiles.deleteWhere {
                id inList Users.select { id eq uId}
                    .map { it[Users.id] }
            }

            Users.deleteWhere { id eq uId}
        }

        return if (num>0)  num
        else null
    }


}
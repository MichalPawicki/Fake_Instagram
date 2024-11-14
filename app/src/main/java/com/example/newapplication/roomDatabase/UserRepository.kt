package com.example.newapplication.roomDatabase

import android.content.Context
import com.example.newapplication.UserService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(context: Context, private val userService: UserService): UserContactDao  {

    private val dao = ContactDB.getInstance(context).userContactDao()

    override suspend fun insertAll(users: UserContact) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(user: UserContact) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<UserContact> {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllByIds(userIds: IntArray): List<UserContact> {
        TODO("Not yet implemented")
    }

    override suspend fun findByName(first: String, last: String): UserContact {
        TODO("Not yet implemented")
    }

}

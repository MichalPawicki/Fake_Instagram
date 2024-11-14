package com.example.newapplication.roomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserContactDao {

    @Insert
    suspend fun insertAll(users: UserContact)

    @Delete
    suspend fun delete(user: UserContact)

    @Query("SELECT * FROM UserContact")
    fun getAll(): List<UserContact>

    @Query("SELECT * FROM UserContact WHERE uid IN (:userIds)")
    suspend fun loadAllByIds(userIds: IntArray): List<UserContact>

    @Query(
        "SELECT * FROM UserContact WHERE name LIKE :first AND " +
                "surname LIKE :last LIMIT 1"
    )
    suspend fun findByName(first: String, last: String): UserContact

}
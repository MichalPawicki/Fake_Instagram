package com.example.newapplication.roomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserContact(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val name: String,
    val surname: String,
    val email: String,
    val phone: String,
    val gender: String,
    val photo: String,
    val birthday: String,
)

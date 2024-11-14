package com.example.newapplication.roomDatabase

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserContact::class], version = 1)
abstract class UserContactDatabase : RoomDatabase() {
    abstract fun userContactDao(): UserContactDao
}

//zabezpieczenie by nie mieć przypadkiem dwóch instancji database
object ContactDB {
     var db: UserContactDatabase? = null

    fun getInstance(context: android.content.Context): UserContactDatabase {
        if (db == null) {
            db = Room.databaseBuilder(context, UserContactDatabase::class.java, "userContact-database").build()
        }
        return db!!
    }
}

package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Joke::class], version = 1)
abstract class JokesDatabase : RoomDatabase() {
    abstract fun jokesDao(): JokesDao
}

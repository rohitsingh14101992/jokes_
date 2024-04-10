package com.example.myapplication.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Joke(
    @ColumnInfo(name = "joke") val joke: String,
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "is_Favorite") val isFavorite: Int
)
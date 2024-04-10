package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JokesDao {
    @Query("Select * From Joke where is_Favorite = 1")
    fun getFavoriteJokes(): List<Joke>

    @Query("SELECT * FROM Joke")
    fun getAllJokes(): List<Joke>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(joke: Joke)

}
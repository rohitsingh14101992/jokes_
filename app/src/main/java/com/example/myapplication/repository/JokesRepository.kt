package com.example.myapplication.repository

import com.example.myapplication.base.Result
import com.example.myapplication.data.Joke
import kotlinx.coroutines.flow.Flow

interface JokesRepository {
   suspend fun getRandomJoke(): Flow<Result<Joke>>
   suspend fun getJokes(isFavorite: Boolean): Flow<Result<List<Joke>>>
   suspend fun addJoke(joke: Joke) : Flow<Result<Joke>>
}
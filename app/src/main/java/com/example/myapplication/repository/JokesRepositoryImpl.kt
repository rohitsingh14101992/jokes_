package com.example.myapplication.repository

import com.example.myapplication.data.Joke
import com.example.myapplication.data.JokesDao
import com.example.myapplication.base.Result
import com.example.myapplication.network.JokesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class JokesRepositoryImpl(
    private val jokesService: JokesService,
    private val jokesDao: JokesDao
) : JokesRepository {

    override suspend fun getRandomJoke(): Flow<Result<Joke>> = flow {
        val response = jokesService.getRandomJoke()

        if (!response.isSuccessful || response.body() == null) {
            emit(Result.Error(Exception("Network Failed")))
            return@flow
        }

        response.body()?.run {
            jokesDao.insert(this)
            emit(Result.Success(this))
        }

    }.catch { e ->
        emit(Result.Error(e))
    }

    override suspend fun getJokes(isFavorite: Boolean): Flow<Result<List<Joke>>> = flow {

        val jokes = if (isFavorite) {
            jokesDao.getFavoriteJokes()
        } else {
            jokesDao.getAllJokes()
        }
        emit(Result.Success(jokes))
    }

    override suspend fun addJoke(joke: Joke): Flow<Result<Joke>> = flow {
        jokesDao.insert(joke)
        emit(Result.Success(joke))
    }
}
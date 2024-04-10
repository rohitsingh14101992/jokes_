package com.example.myapplication.network

import com.example.myapplication.data.Joke
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface JokesService {
    @GET("Any")
    fun getRandomJoke(): Response<Joke>
}
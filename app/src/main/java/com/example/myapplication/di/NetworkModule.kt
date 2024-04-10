package com.example.myapplication.di

import com.example.myapplication.network.JokesService
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

object NetworkModule {

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient)
            .baseUrl("https://v2.jokeapi.dev/joke/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providesJokesApi(retrofit: Retrofit): JokesService =
        retrofit.create(JokesService::class.java)

    @Provides
    fun providesOkhttp() = OkHttpClient()
}
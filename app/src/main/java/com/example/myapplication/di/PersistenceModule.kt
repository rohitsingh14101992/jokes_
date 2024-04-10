package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.JokesDao
import com.example.myapplication.data.JokesDatabase
import com.example.myapplication.network.JokesService
import com.example.myapplication.repository.JokesRepository
import com.example.myapplication.repository.JokesRepositoryImpl
import dagger.Provides
import javax.inject.Singleton

const val DATABASE_NAME = "database-name"

object PersistenceModule {
    @Provides
    fun providesDatabase(applicationContext: Context): JokesDatabase = Room.databaseBuilder(
        applicationContext,
        JokesDatabase::class.java, DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesJokesDao(database: JokesDatabase): JokesDao = database.jokesDao()

    @Provides
    @Singleton
    fun providesJokesRepository(jokesDao: JokesDao, jokesService: JokesService): JokesRepository =
        JokesRepositoryImpl(jokesService, jokesDao)
}
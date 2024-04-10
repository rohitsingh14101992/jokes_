package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.JokesApp
import com.example.myapplication.data.JokesDao
import com.example.myapplication.network.JokesService
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(jokesApp: JokesApp)

    fun getContext(): Context

    fun getJokesService(): JokesService

    fun getJokesDao(): JokesDao

}
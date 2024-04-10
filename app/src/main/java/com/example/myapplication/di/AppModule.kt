package com.example.myapplication.di

import dagger.Module

@Module(includes = [NetworkModule::class, PersistenceModule::class])
class AppModule
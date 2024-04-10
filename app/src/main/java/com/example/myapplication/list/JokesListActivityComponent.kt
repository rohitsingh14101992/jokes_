package com.example.myapplication.list

import com.example.myapplication.di.AppComponent
import dagger.Component


@Component(dependencies = [AppComponent::class], modules = [JokesListActivityModule::class])
interface JokesListActivityComponent {

    fun inject(activity: JokesListActivity)

}
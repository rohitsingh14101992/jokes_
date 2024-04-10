package com.example.myapplication.dashboard

import com.example.myapplication.di.AppComponent
import dagger.Component


@Component(dependencies = [AppComponent::class], modules = [DashboardActivityModule::class])
interface DashboardActivityComponent {

    fun inject(activity: DashboardActivity)

}
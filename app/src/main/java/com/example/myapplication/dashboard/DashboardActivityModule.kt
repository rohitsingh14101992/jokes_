package com.example.myapplication.dashboard

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.base.ViewModelProviderFactory
import com.example.myapplication.repository.JokesRepository
import dagger.Module
import dagger.Provides

@Module
class DashboardActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun providesActivity(): AppCompatActivity {
        return activity
    }

    @Provides
    fun provideTopHeadlineViewModel(repository: JokesRepository): DashboardViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(DashboardViewModel::class) {
                DashboardViewModel(repository)
            })[DashboardViewModel::class.java]
    }

}
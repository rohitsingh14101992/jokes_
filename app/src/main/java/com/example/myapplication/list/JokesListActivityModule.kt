package com.example.myapplication.list

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.base.ViewModelProviderFactory
import com.example.myapplication.repository.JokesRepository
import dagger.Module
import dagger.Provides

@Module
class JokesListActivityModule(
    private val activity: AppCompatActivity,
    private val isFavoriteScreen: Boolean
) {

    @Provides
    fun providesActivity(): AppCompatActivity {
        return activity
    }

    @Provides
    fun provideTopHeadlineViewModel(repository: JokesRepository): JokesListViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(JokesListViewModel::class) {
                JokesListViewModel(repository, isFavoriteScreen)
            })[JokesListViewModel::class.java]
    }

}
package com.example.myapplication.dashboard

import com.example.myapplication.base.UIState
import com.example.myapplication.data.Joke
import com.example.myapplication.base.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.JokesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DashboardViewModel(private val jokesRepository: JokesRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<UIState<Joke>>(UIState.Loading)

    val uiState: StateFlow<UIState<Joke>> = _uiState

    init {
        fetchRandomJoke()
    }

    private fun fetchRandomJoke() {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            jokesRepository.getRandomJoke().flowOn(Dispatchers.IO).collect {
                when (it) {
                    is Result.Success -> {
                        _uiState.value = UIState.Success(it.data)
                    }

                    is Result.Error -> {
                        _uiState.value = UIState.Error(it.exception.message ?: "Unknown Error")
                    }
                }
            }
        }
    }

    fun makeJokeFavorite(joke: Joke) {
       viewModelScope.launch {
           jokesRepository.addJoke(joke).flowOn(Dispatchers.IO).collect{
               //Update UI
           }
       }
    }
}
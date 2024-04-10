package com.example.myapplication.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.base.Result
import com.example.myapplication.base.UIState
import com.example.myapplication.data.Joke
import com.example.myapplication.repository.JokesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JokesListViewModel(
    private val jokesRepository: JokesRepository,
    private val isFavorites: Boolean
) : ViewModel() {
    private val _uiState = MutableStateFlow<UIState<List<Joke>>>(UIState.Loading)

    val uiState: StateFlow<UIState<List<Joke>>> = _uiState

    init {
        fetchJokes()
    }

    private fun fetchJokes() {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            jokesRepository.getJokes(isFavorites)
                .collect {
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

}
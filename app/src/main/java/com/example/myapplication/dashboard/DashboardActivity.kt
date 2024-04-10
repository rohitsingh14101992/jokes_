package com.example.myapplication.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.R
import com.example.myapplication.base.UIState
import com.example.myapplication.data.Joke
import com.example.myapplication.list.JokesListActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardActivity : AppCompatActivity() {
    @Inject
    private lateinit var viewModel: DashboardViewModel
    private lateinit var jokeTextView: TextView
    private lateinit var historyButton: Button
    private lateinit var favoriteButton: Button
    private lateinit var favoriteScreenButton: Button
    private var joke: Joke? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        jokeTextView = findViewById(R.id.tv_joke)
        historyButton = findViewById(R.id.btn_history)
        favoriteButton = findViewById(R.id.btn_favorite)
        favoriteScreenButton = findViewById(R.id.btn_favoriteScreen)

        favoriteButton.setOnClickListener {
            joke?.run {
                viewModel.makeJokeFavorite(this)
            }
        }

        favoriteScreenButton.setOnClickListener {
            openJokesList(true)
        }

        historyButton.setOnClickListener {
            openJokesList(false)
        }
        //Setup DI
        setupObserver()
    }

    private fun openJokesList(isFavorite: Boolean) {
        val intent = Intent(this, JokesListActivity::class.java)
        intent.putExtra("isFavorite", isFavorite)
        startActivity(intent)
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UIState.Success -> {
                            joke = it.data
                            jokeTextView.text = it.data.joke

                        }

                        is UIState.Loading -> {
                            //TODO
                        }

                        is UIState.Error -> {
                            Toast.makeText(this@DashboardActivity, it.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

}
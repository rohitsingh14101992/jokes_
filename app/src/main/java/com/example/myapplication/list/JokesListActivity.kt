package com.example.myapplication.list

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class JokesListActivity : AppCompatActivity() {
    @Inject
    private lateinit var viewModel: JokesListViewModel
    private lateinit var jokesAdapter: JokesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes_list)
        val recyclerView = findViewById<RecyclerView>(R.id.list_jokes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = jokesAdapter
        //SetupDI pass bundle
        setupObserver()
    }


    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UIState.Success -> {

                            jokesAdapter.submitList(it.data)

                        }

                        is UIState.Loading -> {
                            //TODO
                        }

                        is UIState.Error -> {
                            Toast.makeText(this@JokesListActivity, it.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}
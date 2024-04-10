package com.example.myapplication.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Joke

class JokesListAdapter : RecyclerView.Adapter<JokesListAdapter.JokeViewHolder>() {

    private var jokes: List<Joke> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_joke, parent, false)
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = jokes[position]
        holder.bind(joke)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    fun submitList(jokesList: List<Joke>) {
        jokes = jokesList
        notifyDataSetChanged()
    }

    inner class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val jokeText: TextView = itemView.findViewById(R.id.tv_joke_item)

        fun bind(joke: Joke) {
            jokeText.text = joke.joke
        }
    }
}

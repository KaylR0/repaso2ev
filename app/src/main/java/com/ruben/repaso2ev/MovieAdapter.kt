package com.ruben.repaso2ev

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter( private var moviesList: List<MovieEntity> = emptyList())
    : RecyclerView.Adapter<MovieViewHolder>() {
    fun updateList(list: List<MovieEntity>) {
        this.moviesList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }
    override fun getItemCount() = moviesList.size
}
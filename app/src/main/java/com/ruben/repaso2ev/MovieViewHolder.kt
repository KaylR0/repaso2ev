package com.ruben.repaso2ev

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ruben.repaso2ev.databinding.ItemLayoutBinding
import com.squareup.picasso.Picasso

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemLayoutBinding.bind(view)
    fun bind(movieEntity: MovieEntity) {
        //recibe los items de la lista
        Picasso.get().load(movieEntity.image).into(binding.ivMovie);
        binding.movieTitle.text = movieEntity.title
        binding.tvDirector.text = movieEntity.director
        binding.tvGenre.text = movieEntity.genre
        binding.tvDuration.text = movieEntity.duration
        binding.tvLeadActor.text = movieEntity.leadActor
        binding.tvReleaseDate.text = movieEntity.releaseDate
        binding.tvSynopsis.text = movieEntity.synopsis
        binding.tvWriters.text = movieEntity.writer1+"\n"+movieEntity.writer2+"\n"+movieEntity.writer3+"\n"+movieEntity.writer4
        //al ponerle root le decimos que hacemos referencia a cualquier parte del layout. Bien pulsemos el texto o la imagen, hará el setOnClick.
    }
}
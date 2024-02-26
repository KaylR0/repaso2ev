package com.ruben.repaso2ev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.room.Room
import com.ruben.repaso2ev.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var room: MoviesDB
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        room = getRoom()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fillDatabase()
        initUI()
    }
    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true

        CoroutineScope(Dispatchers.IO).launch {
            val response: List<MovieEntity> = room.getMovieDao().getMovies(query)
            runOnUiThread {
                adapter.updateList(response)
                binding.progressBar.isVisible = false
            }
        }
    }

    private fun fillDatabase(){
        binding.progressBar.isVisible = true

        CoroutineScope(Dispatchers.IO).launch {
            val movieList = MoviesProvider.getMovies().map{ it.toDatabase()}
            if(room.getMovieDao().getAllMovies().isNotEmpty()) {
                //si no está vacía la BD entra aquí
            }else{
                room.getMovieDao().insertAll(movieList)
            }
            runOnUiThread {
                binding.progressBar.isVisible = false
            }
        }
    }
    private fun initUI(){
          binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }
            override fun onQueryTextChange(newText: String?) = false
        })
        adapter = MovieAdapter()//se inicializa vacío
        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        binding.rvMovies.adapter = adapter
    }

    private fun getRoom(): MoviesDB{
        return Room
            .databaseBuilder(this, MoviesDB::class.java, "MoviesDB")
            .build()
    }
}
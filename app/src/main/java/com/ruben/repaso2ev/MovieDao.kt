package com.ruben.repaso2ev

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    suspend fun getAllMovies():List<MovieEntity>

    @Query("SELECT * FROM movie_table WHERE title LIKE '%'|| :query ||'%'")
    suspend fun getMovies(query:String):List<MovieEntity>

    /*@Query("SELECT * FROM movie_table WHERE id LIKE :id")
    suspend fun getMovies(id:Int):MovieEntity*/

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie:MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies:List<MovieEntity>)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovies()

}
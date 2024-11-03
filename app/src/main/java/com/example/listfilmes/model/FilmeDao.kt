package com.example.listfilmes.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FilmeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilme(filme: Filme)

    @Update
    suspend fun updateFilme(filme: Filme)

    @Query("SELECT * FROM filmes ORDER BY nome ASC")
    suspend fun getAllFilmes(): List<Filme>

    @Query("SELECT * FROM filmes WHERE id = :id LIMIT 1")
    suspend fun getFilmeById(id: Int): Filme?

    @Query("DELETE FROM filmes WHERE id = :id")
    suspend fun deleteFilme(id: Int)
}

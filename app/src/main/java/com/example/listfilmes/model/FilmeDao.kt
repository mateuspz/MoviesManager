package com.example.listfilmes.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface FilmeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilme(filme: Filme)

    @Query("SELECT * FROM filmes ORDER BY nome ASC")
    suspend fun getAllFilmes(): List<Filme>

    @Query("SELECT * FROM filmes WHERE nome = :nome LIMIT 1")
    suspend fun getFilmeByName(nome: String): Filme?

    @Query("DELETE FROM filmes WHERE nome = :nome")
    suspend fun deleteFilme(nome: String)
}
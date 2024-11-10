package com.example.listfilmes.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FilmeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirFilme(filme: Filme)

    @Update
    suspend fun atualizarFilme(filme: Filme)

    @Query("SELECT * FROM filmes ORDER BY nome ASC")
    suspend fun obterTodosFilmes(): List<Filme>

    @Query("SELECT * FROM filmes WHERE id = :id LIMIT 1")
    suspend fun obterFilmePorId(id: Int): Filme?

    @Query("DELETE FROM filmes WHERE id = :id")
    suspend fun deletarFilme(id: Int)
}

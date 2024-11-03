package com.example.listfilmes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmes")
data class Filme(
    @PrimaryKey val nome: String,           // Nome do filme (único)
    val anoLancamento: Int,                 // Ano de lançamento
    val produtora: String,                  // Estúdio ou produtora
    val duracao: Int,                       // Tempo de duração em minutos
    val assistido: Boolean,                 // Flag para indicar se foi assistido
    val nota: Float?,                       // Nota dada pelo usuário (0 a 10)
    val genero: String                      // Gênero do filme
)


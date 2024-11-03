package com.example.listfilmes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmes")
data class Filme(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val anoLancamento: Int,
    val produtora: String,
    val duracao: Int,
    val assistido: Boolean,
    val nota: Float?,
    val genero: String
)


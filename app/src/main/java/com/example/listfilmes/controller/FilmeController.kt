package com.example.listfilmes.controller

import android.content.Context
import com.example.listfilmes.model.Filme
import com.example.listfilmes.model.FilmeDao
import com.example.listfilmes.model.FilmeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class FilmeController(context: Context) {

    private val filmeDao: FilmeDao = FilmeDatabase.getInstance(context).filmeDao()

    // Função para adicionar um filme no banco de dados
    suspend fun adicionarFilme(filme: Filme) {
        withContext(Dispatchers.IO) {
            filmeDao.inserirFilme(filme)
        }
    }

    // Função para atualizar um filme existente
    suspend fun atualizarFilme(filme: Filme) {
        withContext(Dispatchers.IO) {
            filmeDao.atualizarFilme(filme)
        }
    }

    // Função para deletar um filme
    suspend fun deletarFilme(filme: Filme) {
        withContext(Dispatchers.IO) {
            filmeDao.deletarFilme(filme.id)
        }
    }

    // Função para obter todos os filmes
    suspend fun obterTodosFilmes(): List<Filme> {
        return filmeDao.obterTodosFilmes() // Agora retornamos diretamente a lista de filmes
    }

    // Função para obter um filme pelo ID
    suspend fun obterFilmePorId(id: Int): Filme? {
        return withContext(Dispatchers.IO) {
            filmeDao.obterFilmePorId(id)
        }
    }
}
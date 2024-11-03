package com.example.listfilmes.controller

import com.example.listfilmes.model.Filme
import com.example.listfilmes.model.FilmeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmeController(private val filmeDao: FilmeDao) {

    // Método para adicionar um novo filme
    suspend fun addFilme(filme: Filme) {
        withContext(Dispatchers.IO) {
            filmeDao.insertFilme(filme)
        }
    }

    // Método para atualizar um filme existente
    suspend fun updateFilme(filme: Filme) {
        withContext(Dispatchers.IO) {
            filmeDao.updateFilme(filme)
        }
    }

    // Método para buscar todos os filmes
    suspend fun getAllFilmes(): List<Filme> {
        return withContext(Dispatchers.IO) {
            filmeDao.getAllFilmes()
        }
    }

    // Método para buscar um filme pelo ID
    suspend fun getFilmeById(id: Int): Filme? {
        return withContext(Dispatchers.IO) {
            filmeDao.getFilmeById(id)
        }
    }

    // Método para deletar um filme pelo ID
    suspend fun deleteFilme(id: Int) {
        withContext(Dispatchers.IO) {
            filmeDao.deleteFilme(id)
        }
    }
}
package com.example.listfilmes.view

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ListFilmes.R

import com.example.listfilmes.controller.FilmeController
import com.example.listfilmes.model.Filme
import kotlinx.coroutines.launch

class DetalhesFilmeActivity : AppCompatActivity() {
// Declarando as variáveis para as Views
private lateinit var textViewNome: TextView
private lateinit var textViewAnoLancamento: TextView
private lateinit var textViewProdutora: TextView
private lateinit var textViewDuracao: TextView
private lateinit var textViewNota: TextView
private lateinit var textViewGenero: TextView
private lateinit var textViewAssistido: TextView
private lateinit var filmeController: FilmeController

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detalhes_filme)

    // Inicializando as Views
    textViewNome = findViewById(R.id.textViewNome)
    textViewAnoLancamento = findViewById(R.id.textViewAnoLancamento)
    textViewProdutora = findViewById(R.id.textViewProdutora)
    textViewDuracao = findViewById(R.id.textViewDuracao)
    textViewNota = findViewById(R.id.textViewNota)
    textViewGenero = findViewById(R.id.textViewGenero)
    textViewAssistido = findViewById(R.id.textViewAssistido)

    // Inicializando o controlador
    filmeController = FilmeController(this)

    // Obter o ID do filme e carregar seus dados
    val filmeId = intent.getIntExtra("FILME_ID", -1)
    if (filmeId != -1) {
        carregarDadosFilme(filmeId)
    }
}

// Função para carregar os dados do filme de forma assíncrona
private fun carregarDadosFilme(filmeId: Int) {
    lifecycleScope.launch {
        try {
            // Chama a função suspensa para obter o filme pelo ID
            val filme = filmeController.obterFilmePorId(filmeId)

            // Verifica se o filme foi encontrado e exibe os dados
            filme?.let {
                carregarDadosNaUI(it)
            } ?: run {
                // Caso o filme não seja encontrado
                mostrarErro("Filme não encontrado")
            }
        } catch (e: Exception) {
            // Trata erros, caso haja algum problema na obtenção do filme
            mostrarErro("Erro ao carregar os dados do filme")
        }
    }
}

// Função para atualizar os dados da interface com o filme obtido
private fun carregarDadosNaUI(filme: Filme) {
    textViewNome.text = filme.nome
    textViewAnoLancamento.text = "Ano: ${filme.anoLancamento}"
    textViewProdutora.text = "Produtora: ${filme.produtora}"
    textViewDuracao.text = "Duração: ${filme.duracao} minutos"
    textViewNota.text = "Nota: ${filme.nota?.toString() ?: "N/A"}"
    textViewGenero.text = "Gênero: ${filme.genero}"
    textViewAssistido.text = if (filme.assistido) "Assistido" else "Não assistido"
}

// Função para mostrar uma mensagem de erro
private fun mostrarErro(mensagem: String) {
    // Exibe uma Toast ou qualquer outro tipo de erro
    Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
}
}
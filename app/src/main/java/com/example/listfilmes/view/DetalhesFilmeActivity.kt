package com.example.listfilmes.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.listfilmes.R
import com.example.listfilmes.controller.FilmeController
import com.example.listfilmes.model.Filme

class DetalhesFilmeActivity : AppCompatActivity() {

    private lateinit var textViewNome: TextView
    private lateinit var textViewAnoLancamento: TextView
    private lateinit var textViewProdutora: TextView
    private lateinit var textViewDuracao: TextView
    private lateinit var textViewNota: TextView
    private lateinit var textViewGenero: TextView
    private lateinit var textViewAssistido: TextView
    private var filmeController: FilmeController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_filme)

        // Inicializar os componentes de UI
        textViewNome = findViewById(R.id.textViewNome)
        textViewAnoLancamento = findViewById(R.id.textViewAnoLancamento)
        textViewProdutora = findViewById(R.id.textViewProdutora)
        textViewDuracao = findViewById(R.id.textViewDuracao)
        textViewNota = findViewById(R.id.textViewNota)
        textViewGenero = findViewById(R.id.textViewGenero)
        textViewAssistido = findViewById(R.id.textViewAssistido)

        // Inicializar o controlador
        filmeController = FilmeController(this)

        // Obter o ID do filme e carregar seus dados
        val filmeId = intent.getIntExtra("FILME_ID", -1)
        if (filmeId != -1) {
            val filme = filmeController?.obterFilmePorId(filmeId)
            filme?.let { carregarDadosFilme(it) }
        }
    }

    private fun carregarDadosFilme(filme: Filme) {
        textViewNome.text = filme.nome
        textViewAnoLancamento.text = "Ano: ${filme.anoLancamento}"
        textViewProdutora.text = "Produtora: ${filme.produtora}"
        textViewDuracao.text = "Duração: ${filme.duracao} minutos"
        textViewNota.text = "Nota: ${filme.nota?.toString() ?: "N/A"}"
        textViewGenero.text = "Gênero: ${filme.genero}"
        textViewAssistido.text = if (filme.assistido) "Assistido" else "Não assistido"
    }
}

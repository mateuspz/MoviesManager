package com.example.listfilmes.view

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.listfilmes.R
import com.example.listfilmes.controller.FilmeController
import com.example.listfilmes.model.Filme

class AddEditFilmeActivity : AppCompatActivity() {

    private lateinit var editTextNome: EditText
    private lateinit var editTextAnoLancamento: EditText
    private lateinit var editTextProdutora: EditText
    private lateinit var editTextDuracao: EditText
    private lateinit var editTextNota: EditText
    private lateinit var spinnerGenero: Spinner
    private lateinit var switchAssistido: Switch
    private lateinit var buttonSalvar: Button
    private var filmeController: FilmeController? = null
    private var filmeId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_filme)

        // Inicializar os componentes de UI
        editTextNome = findViewById(R.id.textViewNome)
        editTextAnoLancamento = findViewById(R.id.editTextAnoLancamento)
        editTextProdutora = findViewById(R.id.editTextProdutora)
        editTextDuracao = findViewById(R.id.editTextDuracao)
        editTextNota = findViewById(R.id.editTextNota)
        spinnerGenero = findViewById(R.id.spinnerGenero)
        switchAssistido = findViewById(R.id.switchAssistido)
        buttonSalvar = findViewById(R.id.buttonSalvar)

        // Configurar o spinner de gêneros
        val generos = listOf("Romance", "Aventura", "Terror", "Comédia", "Drama")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, generos)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGenero.adapter = spinnerAdapter

        // Inicializar o controlador
        filmeController = FilmeController(this)

        // Verificar se é uma edição de filme ou adição de novo filme
        filmeId = intent.getIntExtra("FILME_ID", -1).takeIf { it != -1 }
        if (filmeId != null) {
            // Carregar os dados do filme para edição
            val filme = filmeController?.obterFilmePorId(filmeId!!)
            filme?.let { carregarDadosFilme(it) }
            editTextNome.isEnabled = false // O nome não pode ser editado
        }

        // Configurar o botão de salvar
        buttonSalvar.setOnClickListener {
            salvarFilme()
        }
    }

    private fun carregarDadosFilme(filme: Filme) {
        editTextNome.setText(filme.nome)
        editTextAnoLancamento.setText(filme.anoLancamento.toString())
        editTextProdutora.setText(filme.produtora)
        editTextDuracao.setText(filme.duracao.toString())
        editTextNota.setText(filme.nota?.toString() ?: "")
        switchAssistido.isChecked = filme.assistido

        // Definir o gênero selecionado no spinner
        val generoPosition = (spinnerGenero.adapter as ArrayAdapter<String>).getPosition(filme.genero)
        spinnerGenero.setSelection(generoPosition)
    }

    private fun salvarFilme() {
        val nome = editTextNome.text.toString()
        val anoLancamento = editTextAnoLancamento.text.toString().toIntOrNull() ?: return
        val produtora = editTextProdutora.text.toString()
        val duracao = editTextDuracao.text.toString().toIntOrNull() ?: return
        val nota = editTextNota.text.toString().toFloatOrNull()
        val genero = spinnerGenero.selectedItem.toString()
        val assistido = switchAssistido.isChecked

        val filme = Filme(
            id = filmeId ?: 0,
            nome = nome,
            anoLancamento = anoLancamento,
            produtora = produtora,
            duracao = duracao,
            assistido = assistido,
            nota = nota,
            genero = genero
        )

        if (filmeId == null) {
            filmeController?.adicionarFilme(filme)
        } else {
            filmeController?.atualizarFilme(filme)
        }

        finish()
    }
}

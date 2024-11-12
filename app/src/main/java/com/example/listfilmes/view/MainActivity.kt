package com.example.listfilmes.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ListFilmes.R
import com.example.listfilmes.adapter.FilmeAdapter
import com.example.listfilmes.controller.FilmeController
import com.example.listfilmes.model.Filme
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var filmeAdapter: FilmeAdapter
    private var filmeController: FilmeController? = null
    private val listaFilmes = mutableListOf<Filme>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val buttonAdicionarFilme = findViewById<Button>(R.id.buttonAdicionarFilme)
        buttonAdicionarFilme.setOnClickListener {
            abrirAddEditFilmeActivity()
        }

        // Inicializando o controlador e a RecyclerView
        filmeController = FilmeController(this)
        recyclerView = findViewById(R.id.recyclerViewFilmes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        carregarFilmes()
    }

    private fun carregarFilmes() {
        lifecycleScope.launch {
            try {
                listaFilmes.clear()
                listaFilmes.addAll(filmeController?.obterTodosFilmes() ?: emptyList())
                atualizarAdapter()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Erro ao carregar filmes", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun atualizarAdapter() {
        filmeAdapter = FilmeAdapter(
            filmes = listaFilmes,

            onItemClick = { filme -> abrirDetalhesFilme(filme) },
            onEditClick = { filme -> abrirAddEditFilmeActivity(filme) },
            onDeleteClick = { filme -> excluirFilme(filme) }

        )
        recyclerView.adapter = filmeAdapter
    }

    private fun abrirAddEditFilmeActivity(filme: Filme? = null) {
        val intent = Intent(this, AddFilmeActivity::class.java)
        filme?.let {
            intent.putExtra("FILME_ID", it.id)
        }
        startActivity(intent)
    }

    private fun abrirDetalhesFilme(filme: Filme) {
        val intent = Intent(this, DetalhesFilmeActivity::class.java)
        intent.putExtra("FILME_ID", filme.id)
        startActivity(intent)
    }

    private fun excluirFilme(filme: Filme) {
        lifecycleScope.launch {
            try {
                filmeController?.deletarFilme(filme)
                listaFilmes.remove(filme)
                atualizarAdapter()
                Toast.makeText(this@MainActivity, "Filme '${filme.nome}' excluído.", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Erro ao excluir filme", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        carregarFilmes() // Recarrega os filmes ao voltar para a Activity
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_filme -> {
                abrirAddEditFilmeActivity()
                true
            }
            R.id.menu_ordenar_nome -> {
                ordenarFilmesPorNome()
                true
            }
            R.id.menu_ordenar_nota -> {
                ordenarFilmesPorNota()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun ordenarFilmesPorNome() {
        listaFilmes.sortBy { it.nome }
        atualizarAdapter()
    }

    private fun ordenarFilmesPorNota() {
        listaFilmes.sortByDescending { it.nota ?: 0f }
        atualizarAdapter()
    }
}

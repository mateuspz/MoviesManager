package com.example.listfilmes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ListFilmes.R
//import com.example.listfilmes.R
import com.example.listfilmes.model.Filme

class FilmeAdapter(
    private val filmes: List<Filme>,
    private val onItemClick: (Filme) -> Unit,
    private val onEditClick: (Filme) -> Unit,
    private val onDeleteClick: (Filme) -> Unit
) : RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    // ViewHolder para os itens da lista de filmes
    inner class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNomeFilme: TextView = itemView.findViewById(R.id.textViewNomeFilme)
        val textViewAnoLancamento: TextView = itemView.findViewById(R.id.textViewAnoLancamento)
        val textViewProdutora: TextView = itemView.findViewById(R.id.textViewProdutora)
        val textViewNota: TextView = itemView.findViewById(R.id.textViewNota)
        val buttonEdit: Button = itemView.findViewById(R.id.buttonEditarFilme)
        val buttonDelete: Button = itemView.findViewById(R.id.buttonExcluirFilme)

        fun bind(filme: Filme) {
            textViewNomeFilme.text = filme.nome
            textViewAnoLancamento.text = "Ano: ${filme.anoLancamento}"
            textViewProdutora.text = "Produtora: ${filme.produtora}"
            textViewNota.text = "Nota: ${filme.nota?.toString() ?: "N/A"}"

            buttonEdit.setOnClickListener { onEditClick(filme) }
            buttonDelete.setOnClickListener { onDeleteClick(filme) }

            itemView.setOnClickListener { onItemClick(filme) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filme, parent, false)
        return FilmeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        holder.bind(filmes[position])
    }

    override fun getItemCount() = filmes.size
}


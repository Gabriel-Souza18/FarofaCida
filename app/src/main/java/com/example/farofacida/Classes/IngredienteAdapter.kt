package com.example.farofacida.Classes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farofacida.R

class IngredienteAdapter(private val ingredientes: List<Ingrediente>) :
    RecyclerView.Adapter<IngredienteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.etIngredienteNome)
        val quantidade: TextView = itemView.findViewById(R.id.etIngredienteQuantidade)
        val preco: TextView = itemView.findViewById(R.id.etIngredientePreco)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingrediente, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingrediente = ingredientes[position]
        holder.nome.text = ingrediente.getNome()
        holder.quantidade.text = ingrediente.getQuantidade().toString()
        holder.preco.text = ingrediente.getPreco().toString()
    }

    override fun getItemCount(): Int = ingredientes.size
}
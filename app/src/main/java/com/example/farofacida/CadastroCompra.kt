package com.example.farofacida

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.farofacida.BancodeDados.CriaBanco
import com.example.farofacida.Classes.Ingrediente
import com.example.farofacida.Classes.IngredienteAdapter
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*


class CadastroCompra : AppCompatActivity() {

    private var db = CriaBanco(this)

    private lateinit var ingredientesAdapter: IngredienteAdapter
    private val ingredientes = mutableListOf<Ingrediente>()

    private lateinit var etDataCompra : TextInputEditText
    private val calendario = Calendar.getInstance()

    private lateinit var etNome: EditText
    private lateinit var etQuantidade: EditText
    private lateinit var etPreco: EditText
    private lateinit var etLugar: EditText

    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_compra)

        etDataCompra = findViewById(R.id.etDataCompra)

        val rvIngredientes = findViewById<RecyclerView>(R.id.rvIngredientes)
        ingredientesAdapter = IngredienteAdapter(ingredientes)
        rvIngredientes.adapter = ingredientesAdapter
        // Configurar o clique no campo de data
        etDataCompra.setOnClickListener {
            mostrarSeletorData()
        }
    }
    fun NovoIngrediente(view: View){
        val dialogView = LayoutInflater.from(this).inflate(R.layout.layout_add_ingrediente, null)

        dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        etNome = dialogView.findViewById<EditText>(R.id.etIngredienteNome)
        etQuantidade= dialogView.findViewById<EditText>(R.id.etIngredienteQuantidade)
        etPreco = dialogView.findViewById<EditText>(R.id.etIngredientePreco)

        Toast.makeText(this, "Adicionando Novo Ingrediente", Toast.LENGTH_SHORT).show()
        dialog.show()
    }
    fun CadastrarCompra(view: View){
        val dataCompra = etDataCompra.text.toString()
        val lugarCompra= findViewById<EditText>(R.id.etLugarCompra).text.toString()
        db.addCompra(lugarCompra, dataCompra)
        var i=0
        for (i in 0 .. ingredientes.size - 1) {
            db.addIngrediente(ingredientes[i].getNome())
            val idIngrediente = db.getIdIngrediente(ingredientes[i].getNome())
            db.addCompraIngrediente(
                idIngrediente,
                dataCompra,
                ingredientes[i].getQuantidade(),
                ingredientes[i].getPreco())
        }
        Toast.makeText(this, "Compra Salva", Toast.LENGTH_SHORT).show()
    }
    fun AdicionarIngrediente(view: View){
        var novoIngrediente = Ingrediente(
            etNome.text.toString(),
            etQuantidade.text.toString().toInt(),
            etPreco.text.toString().toDouble()
        )
        ingredientes.add(novoIngrediente)
        dialog.dismiss()
    }
    fun CancelarIngrediente(view: View){
        dialog.dismiss()
    }

    fun atualizaLista(view: View){

        Toast.makeText(this, "Ingrediente adicionado com sucesso!", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarSeletorData(){
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            { _, anoSelecionado, mesSelecionado, diaSelecionado ->
                calendario.set(anoSelecionado, mesSelecionado, diaSelecionado)

                val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val dataFormatada = formato.format(calendario.time)

                etDataCompra.setText(dataFormatada)
            },ano, mes, dia,
        )
        datePickerDialog.show()
    }

}


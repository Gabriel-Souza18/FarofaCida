package com.example.farofacida

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*


class CadastroCompra : AppCompatActivity() {

    private lateinit var etDataCompra : TextInputEditText
    private val calendario = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_compra)

        etDataCompra = findViewById(R.id.etDataCompra)

        // Configurar o clique no campo de data
        etDataCompra.setOnClickListener {
            mostrarSeletorData()
        }
    }
    fun NovoIngrediente(view: View){
        Toast.makeText(this, "Novo Ingrediente", Toast.LENGTH_SHORT).show()
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


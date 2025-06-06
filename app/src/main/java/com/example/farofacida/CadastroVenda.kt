package com.example.farofacida

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.DatePickerDialog
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.farofacida.BancodeDados.CriaBanco
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*
import kotlin.toString


class CadastroVenda : AppCompatActivity() {


    private lateinit var etDataVenda: TextInputEditText
    private val calendario = Calendar.getInstance()

    private lateinit var et_clienteNome: EditText
    private lateinit var et_valorUnitario: EditText
    private lateinit var et_quantidade: EditText
    private lateinit var et_pagamento: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_venda)

        etDataVenda = findViewById(R.id.etDataVenda)

        et_clienteNome = findViewById(R.id.etCVenda_nome)
        et_valorUnitario = findViewById(R.id.etCVenda_valor)
        et_quantidade = findViewById(R.id.etCVenda_quantidade)
        et_pagamento = findViewById(R.id.etCVenda_pagamento)


        // Configurar o clique no campo de data
        etDataVenda.setOnClickListener {
            mostrarSeletorData()
        }
    }

    fun cadastrarVenda(view: View){
        val nomeCliente = et_clienteNome.text.toString()
        val valorUnitario = et_valorUnitario.text.toString().toDouble()
        val quantidade = et_quantidade.text.toString().toInt()
        val pagamento = et_pagamento.text.toString()
        val dataVenda = etDataVenda.text.toString()

        val Pnome : String
        val Snome : String
        if (nomeCliente.contains(" ")) {
            val partes = nomeCliente.split(" ")
            Pnome = partes[0]
            Snome = partes.subList(1, partes.size).joinToString(" ")
        } else {
            Pnome = nomeCliente
            Snome = ""
        }

        try{
            val db = CriaBanco(this)

            var idCliente = db.ClientebyName(Pnome, Snome)
            if (idCliente == -1) {
                Log.d("BD","Novo Cliente")
                db.addCliente(Pnome, Snome)
                idCliente = db.ClientebyName(Pnome, Snome)
            }
            db.addVenda(idCliente, valorUnitario, quantidade, pagamento, dataVenda)
            db.close()
            Log.e("BD", "Venda cadastrada com sucesso.")
        }
        catch (e: Exception){
            Log.e("Erro", "Erro ao cadastrar venda: ${e.message}")
            return
        }
        Toast.makeText(this, "Venda cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
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

                etDataVenda.setText(dataFormatada)
            },ano, mes, dia,
        )
        datePickerDialog.show()
    }
}
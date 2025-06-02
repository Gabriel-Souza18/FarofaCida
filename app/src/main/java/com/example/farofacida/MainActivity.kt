package com.example.farofacida

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.farofacida.BancodeDados.CriaBanco

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    fun testarBanco(view: View){
        val db = CriaBanco(this)
        db.addCliente("Gabriel", "Souza")
        db.addIngrediente("Farinha", "kg")
        db.addCompra("Mercado")
        db.addCompraIngrediente(1,1, 2, 10.0)

        db.printarClientes()
        db.printarCompras()
    }
    fun cadastroVenda(view: View){
        val intent = Intent(this, CadastroVenda::class.java)
        view.context.startActivity(intent)

    }
    fun cadastroCompra(view: View){
        val intent = Intent(this, CadastroCompra::class.java)
        view.context.startActivity(intent)
    }
}
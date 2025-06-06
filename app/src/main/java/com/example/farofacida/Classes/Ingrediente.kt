package com.example.farofacida.Classes


class Ingrediente {
    private var nome: String
    private var quantidade: Int
    private var preco: Double


    constructor(nome: String, quantidade: Int, preco: Double) {
        this.nome = nome
        this.quantidade = quantidade
        this.preco = preco
    }
    fun getNome(): String {
        return nome
    }
    fun getQuantidade(): Int{
        return quantidade
    }
    fun getPreco(): Double{
        return preco
    }
}

package com.example.farofacida.BancodeDados;

public class Querys {
    // Consulta para obter todos os clientes
    public static final String SELECT_ALL_CLIENTES = "SELECT * FROM Cliente";

    // Consulta para obter todas as vendas
    public static final String SELECT_ALL_VENDAS = "SELECT * FROM Venda";
    //consulta pra todos os Compras Ingredientes
    public static final String SELECT_ALL_COMPRAS_INGREDIENTES = "SELECT * FROM CompraIngrediente";
    // Consulta para obter todas as compras
    public static final String SELECT_ALL_COMPRAS = "SELECT * FROM Compra";

    // Consulta para obter todos os ingredientes
    public static final String SELECT_ALL_INGREDIENTES = "SELECT * FROM Ingrediente";

    // Consulta para obter todos os ingredientes de uma compra específica
    public static final String SELECT_INGREDIENTES_BY_COMPRA_ID = "SELECT * FROM CompraIngrediente WHERE idCompra = ?";

    // Consulta para obter um cliente específico pelo ID
    public static final String SELECT_CLIENTE_BY_ID = "SELECT * FROM Cliente WHERE id = ?";

    // Consulta para obter uma venda específica pelo ID
    public static final String SELECT_VENDA_BY_ID = "SELECT * FROM Venda WHERE id = ?";

    // Consulta para obter uma compra específica pelo ID
    public static final String SELECT_COMPRA_BY_ID = "SELECT * FROM Compra WHERE id = ?";

    public static final String SELECT_COMPRAS_BY_CLIENTE_ID = "SELECT * FROM Compra WHERE idCliente = ?";


}


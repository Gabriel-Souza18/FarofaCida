package com.example.farofacida.BancodeDados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log; // Adicione esta importação

import androidx.annotation.Nullable;
public class CriaBanco extends SQLiteOpenHelper{
    //Tabela Cliente
    // Nome do banco e versão
    private static final String DB_NOME = "Teste2.db";
    private static final int DB_VERSAO = 1;

    //Tabela Cliente
    private static final String TABELA_CLIENTE = "Cliente";
    private static final String COL_CLIENTE_ID = "id";
    private static final String COL_CLIENTE_NOME = "Pnome";
    private static final String COL_CLIENTE_SOBRENOME = "Sobrenome";

    //Tabela Venda
    private static final String TABELA_VENDA = "Venda";
    private static final String COL_VENDA_ID = "id";
    private static final String COL_VENDA_VALOR = "valor";
    private static final String COL_VENDA_DATA = "data";
    private static final String COL_VENDA_PAGAMENTO = "pagamento";
    private static final String COL_VENDA_CLIENTE = "idCliente";
    private static final String COL_VENDA_QUANTIDADE = "quantidade";

    //Tabela Compra
    private static final String TABELA_COMPRA = "Compra";
    private static final String COL_COMPRA_ID = "id";
    private static final String COL_COMPRA_LUGAR = "lugar";
    private static final String COL_COMPRA_DATA = "data";

    //Tabela compraIngrediente
    private static final String TABELA_COMPRA_INGREDIENTE ="CompraIngrediente";
    private static final String COL_COMPRA_INGR_COMPRA_ID = "idCompra";
    private static final String COL_COMPRA_INGR_ID = "id";
    private static final String COL_COMPRA_INGR_INGREDIENTE_ID = "idIngrediente";
    private static final String COL_COMPRA_INGR_QUANTIDADE = "quantidade";
    private static final String COL_COMPRA_INGR_PRECO = "preco";

    //Tabela Ingrediente
    private static final String TABELA_INGREDIENTE = "Ingrediente";
    private static final String COL_INGREDIENTE_ID = "id";
    private static final String COL_INGREDIENTE_NOME = "nome";



    public CriaBanco(@Nullable Context context) {
        super(context, DB_NOME, null, DB_VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
            String sql_cliente = "CREATE TABLE " + TABELA_CLIENTE + " (" +
                    COL_CLIENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_CLIENTE_NOME + " TEXT NOT NULL, " +
                    COL_CLIENTE_SOBRENOME + " TEXT NOT NULL)";

            String sql_venda = "CREATE TABLE " + TABELA_VENDA + " (" +
                    COL_VENDA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_VENDA_CLIENTE + " INTEGER NOT NULL, " +
                    COL_VENDA_VALOR + " REAL NOT NULL, " +
                    COL_VENDA_QUANTIDADE + " INTEGER NOT NULL, " +
                    COL_VENDA_PAGAMENTO + " TEXT NOT NULL, " +
                    COL_VENDA_DATA + " TEXT NOT NULL, " +
                    "FOREIGN KEY(" + COL_VENDA_CLIENTE + ") REFERENCES " +
                    TABELA_CLIENTE + "(" + COL_CLIENTE_ID + "))";

            String sql_compra = "CREATE TABLE " + TABELA_COMPRA + " (" +
                    COL_COMPRA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_COMPRA_LUGAR + " TEXT NOT NULL)";

            //TODO: ADD data e colocar ela como pk
            String sql_compraIngrediente = "CREATE TABLE " + TABELA_COMPRA_INGREDIENTE + " (" +
                    COL_COMPRA_INGR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_COMPRA_INGR_COMPRA_ID + " INTEGER NOT NULL, " +
                    COL_COMPRA_INGR_INGREDIENTE_ID + " INTEGER NOT NULL, " +
                    COL_COMPRA_INGR_QUANTIDADE + " INTEGER NOT NULL, " +
                    COL_COMPRA_INGR_PRECO + " REAL NOT NULL, " +
                    "FOREIGN KEY(" + COL_COMPRA_INGR_INGREDIENTE_ID + ") REFERENCES " +
                    TABELA_INGREDIENTE + "(" + COL_INGREDIENTE_ID + "), " +
                    "FOREIGN KEY(" + COL_COMPRA_INGR_COMPRA_ID + ") REFERENCES " +
                    TABELA_COMPRA + "(" + COL_COMPRA_ID + "))";

            String sql_ingrediente = "CREATE TABLE " + TABELA_INGREDIENTE + " (" +
                    COL_INGREDIENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_INGREDIENTE_NOME + " TEXT NOT NULL )" ;
        db.execSQL(sql_cliente);
        db.execSQL(sql_venda);
        db.execSQL(sql_compra);
        db.execSQL(sql_compraIngrediente);
        db.execSQL(sql_ingrediente);
        Log.e("BD", "Banco de dados criado com sucesso.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_CLIENTE);
        onCreate(db);
    }
    public void addCliente(String nome, String sobrenome){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABELA_CLIENTE + " (" +
                COL_CLIENTE_NOME + ", " +
                COL_CLIENTE_SOBRENOME + ") VALUES (?,?)";
        db.execSQL(sql, new String[]{nome, sobrenome});
        db.close();
        Log.e("BD", "Cliente adicionado com sucesso.");
    }
    public void addVenda(int idCliente, double valor, int quantidade, String pagamento, String data){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABELA_VENDA + " (" +
                COL_VENDA_CLIENTE + ", " +
                COL_VENDA_VALOR + ", " +
                COL_VENDA_QUANTIDADE + ", " +
                COL_VENDA_PAGAMENTO + ", " +
                COL_VENDA_DATA + ") VALUES (?,?,?,?,?)";
        db.execSQL(sql, new Object[]{idCliente, valor, quantidade,pagamento ,data});
        db.close();
        Log.e("BD", "Venda adicionada com sucesso.");
    }
    public void addCompra(String lugar, String data){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABELA_COMPRA + " (" +
                COL_COMPRA_LUGAR +
                COL_COMPRA_DATA + ") VALUES (?,?)";
        db.execSQL(sql, new Object[]{lugar, data} );
        db.close();
        Log.e("BD", "Compra adicionada com sucesso.");
    }
    public void addCompraIngrediente(int idIngrediente, int idCompra, int quantidade, double preco){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABELA_COMPRA_INGREDIENTE + " (" +
                COL_COMPRA_INGR_INGREDIENTE_ID + ", " +
                COL_COMPRA_INGR_COMPRA_ID + ", " +
                COL_COMPRA_INGR_QUANTIDADE+ ", " +
                COL_COMPRA_INGR_PRECO + ") VALUES (?,?,?,?)";
        db.execSQL(sql, new Object[]{idIngrediente, idCompra, quantidade, preco});
        db.close();
        Log.e("BD", "CompraIngrediente adicionado com sucesso.");
    }
    public void addIngrediente(String nome){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABELA_INGREDIENTE + " (" +
                COL_INGREDIENTE_NOME +" ) VALUES (?)";
        db.execSQL(sql, new String[]{nome });
        db.close();
        Log.e("BD", "Ingrediente adicionado com sucesso.");
    }

    public void RemoverCliente(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABELA_CLIENTE + " WHERE " + COL_CLIENTE_ID + " = ?";
        db.execSQL(sql, new String[]{String.valueOf(id)});
        db.close();
        Log.e("BD", "Cliente removido com sucesso.");
    }
    public void RemoverVenda(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABELA_VENDA + " WHERE " + COL_VENDA_ID + " = ?";
        db.execSQL(sql, new String[]{String.valueOf(id)});
        db.close();
        Log.e("BD", "Venda removida com sucesso.");
    }

    public void removerCompra(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABELA_COMPRA + " WHERE " + COL_COMPRA_ID + " = ?";
        db.execSQL(sql, new String[]{String.valueOf(id)});
        db.close();
        Log.e("BD", "Compra removida com sucesso.");
    }
    public void removerCompraIngrediente(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABELA_COMPRA_INGREDIENTE + " WHERE " + COL_COMPRA_INGR_ID + " = ?";
        db.execSQL(sql, new String[]{String.valueOf(id)});
        db.close();
        Log.e("BD", "CompraIngrediente removido com sucesso.");
    }

    public void removerIngrediente(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABELA_INGREDIENTE + " WHERE " + COL_INGREDIENTE_ID + " = ?";
        db.execSQL(sql, new String[]{String.valueOf(id)});
        db.close();
        Log.e("BD", "Ingrediente removido com sucesso.");
    }
    public Boolean limparBanco() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DELETE FROM " + TABELA_CLIENTE);
            db.execSQL("DELETE FROM " + TABELA_VENDA);
            db.execSQL("DELETE FROM " + TABELA_COMPRA);
            db.execSQL("DELETE FROM " + TABELA_COMPRA_INGREDIENTE);
            db.execSQL("DELETE FROM " + TABELA_INGREDIENTE);
            db.close();
            Log.e ("BD", "Banco de dados limpo com sucesso.");
            return true;
        }
        catch (Exception e) {
            Log.e("BD", "Erro ao limpar o banco de dados: " + e.getMessage());
            return false;
        }
    }
    public Cursor consultarClientes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = Querys.SELECT_ALL_CLIENTES;
        return db.rawQuery(sql, null);
    }
    public Cursor consultarVendas() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = Querys.SELECT_ALL_VENDAS;
        return db.rawQuery(sql, null);
    }
    public Cursor consultarCompras() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = Querys.SELECT_ALL_COMPRAS;
        return db.rawQuery(sql, null);
    }
    public Cursor consultarIngredientes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = Querys.SELECT_ALL_INGREDIENTES;
        return db.rawQuery(sql, null);
    }
    public Cursor consultarIngredientesPorCompraId(int idCompra) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = Querys.SELECT_INGREDIENTES_BY_COMPRA_ID;
        return db.rawQuery(sql, new String[]{String.valueOf(idCompra)});
    }
    public Cursor consultarClientePorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = Querys.SELECT_CLIENTE_BY_ID;
        return db.rawQuery(sql, new String[]{String.valueOf(id)});
    }
    public Cursor consultarVendaPorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = Querys.SELECT_VENDA_BY_ID;
        return db.rawQuery(sql, new String[]{String.valueOf(id)});
    }
    public Cursor comprasPorClienteId(int idCliente) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = Querys.SELECT_COMPRAS_BY_CLIENTE_ID;
        return db.rawQuery(sql, new String[]{String.valueOf(idCliente)});
    }
    public void printarTabelas() {
        SQLiteDatabase db = this.getReadableDatabase();

        // Cliente
        Cursor c = db.rawQuery(Querys.SELECT_ALL_CLIENTES, null);
        Log.d("BD", "Tabela Cliente:");
        while (c.moveToNext()) {
            Log.d("BD", "id: " + c.getInt(0) + ", nome: " + c.getString(1) + ", sobrenome: " + c.getString(2));
        }
        c.close();

        // Ingrediente
        c = db.rawQuery(Querys.SELECT_ALL_INGREDIENTES, null);
        Log.d("BD", "Tabela Ingrediente:");
        while (c.moveToNext()) {
            Log.d("BD", "id: " + c.getInt(0) + ", nome: " + c.getString(1) + ", unidade: " + c.getString(2));
        }
        c.close();

        // Compra
        c = db.rawQuery(Querys.SELECT_ALL_COMPRAS, null);
        Log.d("BD", "Tabela Compra:");
        while (c.moveToNext()) {
            Log.d("BD", "id: " + c.getInt(0) + ", lugar: " + c.getString(1));
        }
        c.close();

        // CompraIngrediente
        c = db.rawQuery(Querys.SELECT_ALL_COMPRAS_INGREDIENTES, null);
        Log.d("BD", "Tabela CompraIngrediente:");
        while (c.moveToNext()) {
            Log.d("BD", "id: " + c.getInt(0) + ", idCompra: " + c.getInt(1) + ", idIngrediente: " + c.getInt(2) +
                    ", quantidade: " + c.getInt(3) + ", preco: " + c.getDouble(4));
        }
        c.close();

        // Venda
        c = db.rawQuery(Querys.SELECT_ALL_VENDAS, null);
        Log.d("BD", "Tabela Venda:");
        while (c.moveToNext()) {
            Log.d("BD", "id: " + c.getInt(0) + ", idCliente: " + c.getInt(1) + ", valor: " + c.getDouble(2) +
                    ", quantidade: " + c.getInt(3) +"Pagamento"+c.getString(4) +", data: " + c.getString(5));
        }
        c.close();

        db.close();
    }
    public int  ClientebyName(String nome, String sobrenome){
       SQLiteDatabase db = this.getWritableDatabase();
       String sql = "SELECT id FROM " + TABELA_CLIENTE + " WHERE " + COL_CLIENTE_NOME + " = ? AND " + COL_CLIENTE_SOBRENOME + " = ?";

       Cursor cursor = db.rawQuery(sql, new String[]{nome, sobrenome});

       return cursor.moveToFirst() ? cursor.getInt(0) : -1; // Retorna o ID do cliente ou -1 se não encontrado
    }
    public int getIdIngrediente(String nome){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT id FROM "+ TABELA_INGREDIENTE + " WHERE " + COL_INGREDIENTE_NOME + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{nome});

    return cursor.moveToFirst() ? cursor.getInt(0) : -1; // Retorna o ID do ingrediente ou -1 se não encontrado
    }
    public int getIdCompra(String Data){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT id FROM "+ TABELA_COMPRA + " WHERE " + COL_COMPRA_DATA + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{Data});

        return cursor.moveToFirst() ? cursor.getInt(0) : -1; // Retorna o ID da compra ou -1 se não encontrado
    }
}

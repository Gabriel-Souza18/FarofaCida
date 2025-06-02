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
    private static final String DB_NOME = "farofas.db";
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
    private static final String COL_VENDA_CLIENTE = "idCliente";
    private static final String COL_VENDA_QUANTIDADE = "quantidade";

    //Tabela Compra
    private static final String TABELA_COMPRA = "Compra";
    private static final String COL_COMPRA_ID = "id";
    private static final String COL_COMPRA_LUGAR = "lugar";

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
    private static final String COL_INGREDIENTE_MEDIDA = "unidadeMedida";


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
                    COL_VENDA_DATA + " TEXT NOT NULL, " +
                    "FOREIGN KEY(" + COL_VENDA_CLIENTE + ") REFERENCES " +
                    TABELA_CLIENTE + "(" + COL_CLIENTE_ID + "))";

            String sql_compra = "CREATE TABLE " + TABELA_COMPRA + " (" +
                    COL_COMPRA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_COMPRA_LUGAR + " TEXT NOT NULL)";

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
                    COL_INGREDIENTE_NOME + " TEXT NOT NULL, " +
                    COL_INGREDIENTE_MEDIDA + " TEXT NOT NULL)";
        db.execSQL(sql_cliente);
        db.execSQL(sql_venda);
        db.execSQL(sql_compra);
        db.execSQL(sql_compraIngrediente);
        db.execSQL(sql_ingrediente);
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
    }
    public void addVenda(int idCliente, double valor, int quantidade, String data){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABELA_VENDA + " (" +
                COL_VENDA_CLIENTE + ", " +
                COL_VENDA_VALOR + ", " +
                COL_VENDA_QUANTIDADE + ", " +
                COL_VENDA_DATA + ") VALUES (?,?,?,?)";
        db.execSQL(sql, new Object[]{idCliente, valor, quantidade, data});
        db.close();
    }
    public void addCompra(String lugar){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABELA_COMPRA + " (" +
                COL_COMPRA_LUGAR + ") VALUES (?)";
        db.execSQL(sql, new String[]{lugar});
        db.close();
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
    }
    public void addIngrediente(String nome, String unidadeMedida){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABELA_INGREDIENTE + " (" +
                COL_INGREDIENTE_NOME + ", " +
                COL_INGREDIENTE_MEDIDA + ") VALUES (?,?)";
        db.execSQL(sql, new String[]{nome, unidadeMedida});
        db.close();
    }

    public void printarClientes(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABELA_CLIENTE, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                do{
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_CLIENTE_ID));
                    String nome = cursor.getString(cursor.getColumnIndexOrThrow(COL_CLIENTE_NOME));
                    String sobrenome = cursor.getString(cursor.getColumnIndexOrThrow(COL_CLIENTE_SOBRENOME));

                    Log.d("BANCO", "ID: " + id + " Nome: " + nome + " Sobrenome: " + sobrenome);

                }while(cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
    }
    public void printarVendas(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABELA_VENDA, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                do{
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_VENDA_ID));
                    int idCliente = cursor.getInt(cursor.getColumnIndexOrThrow(COL_VENDA_CLIENTE));
                    double valor = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_VENDA_VALOR));
                    int quantidade = cursor.getInt(cursor.getColumnIndexOrThrow(COL_VENDA_QUANTIDADE));
                    String data = cursor.getString(cursor.getColumnIndexOrThrow(COL_VENDA_DATA));

                    Log.d("BANCO", "ID: " + id + " ID Cliente: " + idCliente + " Valor: " + valor +
                            " Quantidade: " + quantidade + " Data: " + data);

                }while(cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
    }
    public void printarCompras(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABELA_COMPRA, null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                do{
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_COMPRA_ID));
                    String lugar = cursor.getString(cursor.getColumnIndexOrThrow(COL_COMPRA_LUGAR));
                    Cursor ingr = db.rawQuery("SELECT * FROM " + TABELA_COMPRA_INGREDIENTE + " WHERE " +
                            COL_COMPRA_INGR_COMPRA_ID + " = ?", new String[]{String.valueOf(id)});
                    if (ingr != null) {
                        if (ingr.moveToFirst()) {
                            do {
                                int ingrId = ingr.getInt(ingr.getColumnIndexOrThrow(COL_COMPRA_INGR_ID));
                                int ingrQuantidade = ingr.getInt(ingr.getColumnIndexOrThrow(COL_COMPRA_INGR_QUANTIDADE));
                                double ingrPreco = ingr.getDouble(ingr.getColumnIndexOrThrow(COL_COMPRA_INGR_PRECO));
                                Log.d("BANCO", "ID Compra: " + id + " ID Ingrediente: " + ingrId +
                                        " Quantidade: " + ingrQuantidade + " Preço: " + ingrPreco);
                            } while (ingr.moveToNext());
                        }
                        ingr.close();
                    }
                }while(cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
    }
}

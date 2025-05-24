package com.example.farofacida.BancodeDados;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CriaBanco extends SQLiteOpenHelper{
    //Tabela Cliente
    private static final String DBCLIENTE = "Cliente.db";
    private static final String DBCLIENTENOME = "Pnome";
    private static final String DBCLIENTEID = "id";
    private static final String DBCLIENTESOBRENOME = "SOBRENOME";
    //Tabela Venda
    private static final String DBVENDA = "Venda.db";
    private static final String DBVENDAID = "id";
    private static final String DBVENDAVALOR = "valor";
    private static final String DBVENDADATA = "data";
    private static final String DBVENDACLIENTE = "idCliente";
    private static final String DBVENDAQUANTIDADE = "quantidade";
    //Tabela Compra
    private static final String DBCOMPRA = "Compra.db";
    private static final String DBCOMPRAID = "id";
    private static final String DBCOMPRALUGAR = "lugar";
    //Tabela compraIngrediente
    private static final String DBCOMPRAINGREDIENTE ="compraIngrediente.db";
    private static final String DBCOMPRAINGRID = "id";
    private static final String DBCOMPRAINGRINGREDENTEID = "idIngrediente";
    private static final String DBCOMPRAINGRQUANTIDADE = "quantidade";
    private static final String DBCOMPRAINGRPRECO = "preco";
    //Tabela Ingrediente
    private static final String DBINGREDIENTE = "Ingrediente.db";
    private static final String DBINGREDIENTEID = "id";
    private static final String DBINGREDIENTENOME = "nome";
    private static final String DBINGREDIENTEMEDIDA = "unidadeMedida";




    public CriaBanco(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_cliente = "CREATE TABLE IF NOT EXISTS " + DBCLIENTE + " (" +
                DBCLIENTEID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBCLIENTENOME + " TEXT, " +
                DBCLIENTESOBRENOME + " TEXT)";

        String sql_venda = "CREATE TABLE IF NOT EXISTS " + DBVENDA + " (" +
                DBVENDAID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBVENDACLIENTE + " INTEGER, " +
                DBVENDAVALOR + " REAL, " +
                DBVENDAQUANTIDADE + " INTEGER, " +
                DBVENDADATA + " TEXT )" ;

        String sql_compra = "CREATE TABLE IF NOT EXISTS " + DBCOMPRA + " (" +
                DBCOMPRAID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBCOMPRALUGAR + " TEXT)"+
                DBCOMPRALUGAR + " TEXT)";

        String sql_compraIngrediente = "CREATE TABLE IF NOT EXISTS " + DBCOMPRAINGREDIENTE + " (" +
                DBCOMPRAINGRID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBCOMPRAINGRINGREDENTEID+ " INTEGER, " +
                DBCOMPRAINGRQUANTIDADE + " INTEGER, " +
                DBCOMPRAINGRPRECO + " REAL)";

        String sql_ingrediente = "CREATE TABLE IF NOT EXISTS " + DBINGREDIENTE + " (" +
                DBINGREDIENTEID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBINGREDIENTENOME + " TEXT)"+
                DBINGREDIENTEMEDIDA+" TEXT)";


        db.execSQL(sql_cliente);
        db.execSQL(sql_venda);
        db.execSQL(sql_compra);
        db.execSQL(sql_compraIngrediente);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DBCLIENTE);
        onCreate(db);
    }


}

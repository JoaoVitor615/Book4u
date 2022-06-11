package com.example.book4u;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper {
    public static final String Tabela_Livro = "tbLivro";
    public static final String Coluna_Id = "lv_id";
    public static final String Coluna_Titulo = "lv_titulo";
    public static final String Coluna_Autor = "lv_autor";
    public static final String Coluna_Pag = "lv_paginas";
    public static final String Coluna_Cat = "lv_categoria";
    public static final String Coluna_Link = "lv_link";

    private static final String DATABASE_Nome = "LivrosApi.db";
    private static final int DATABASE_VERSION = 1;

    public BancoDeDados(Context context) {
        super(context, DATABASE_Nome, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CriaLivro = "create table " + Tabela_Livro + "( "
                + Coluna_Id + " text primary key autoincrement, "
                + Coluna_Titulo + " text not null, "
                + Coluna_Autor + " text not null, "
                + Coluna_Pag + " text, "
                + Coluna_Cat + " text, "
                + Coluna_Link + " text);";
        db.execSQL(CriaLivro);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}

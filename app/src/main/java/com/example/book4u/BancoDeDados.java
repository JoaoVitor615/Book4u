//package com.example.book4u;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class BancoDeDados extends SQLiteOpenHelper {
//    public static final String Tabela_Livro = "tbLivro";
//    public static final String Coluna_Id = "lv_id";
//    public static final String Coluna_Titulo = "lv_titulo";
//    public static final String Coluna_Autor = "lv_autor";
//    public static final String Coluna_Pag = "lv_paginas";
//    public static final String Coluna_Cat = "lv_categoria";
//    public static final String Coluna_Link = "lv_link";
//
//    private static final String DATABASE_Nome = "LivrosApi.db";
//    private static final int DATABASE_VERSION = 1;
//
//    public BancoDeDados(Context context) {
//        super(context, DATABASE_Nome, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String Criacao_tabelaUser = "create table " + Tabela_Livro + "( "
//                + Coluna_Id + " text primary key, "
//                + Coluna_Titulo + " text not null, "
//                + Coluna_Autor + " text not null, "
//                + Coluna_Pag + " text, "
//                + Coluna_Cat + " text, "
//                + Coluna_Link + " text);";
//        db.execSQL(Criacao_tabelaUser);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//    }
//
//    void addUser(Livro livro){
//        //estancia para escrita no banco
//        SQLiteDatabase db=this.getWritableDatabase();
//
//        ContentValues values= new ContentValues();
//        values.put(Coluna_Id, livro.getId());
//        values.put(Coluna_Titulo, livro.getTitulo());
//        values.put(Coluna_Autor, livro.getAutor());
//        values.put(Coluna_Pag, livro.getPagina());
//        values.put(Coluna_Cat, livro.getCategoria());
//        values.put(Coluna_Link, livro.getLink());
//
//        //inseri no banco
//        db.insert(Tabela_Livro, null, values);
//        db.close();
//    }
//
//    //select por login
//    Livro selecionarLivro(String login){
//        SQLiteDatabase db=this.getReadableDatabase();
//
//        Cursor cursor=db.query(Tabela_Livro,
//                new String[]{Coluna_Id, Coluna_Titulo, Coluna_Autor, Coluna_Pag, Coluna_Cat, Coluna_Link},
//                Coluna_Id+"=?",new String[]{String.valueOf(login)},null, null, null,null);
//
//    }
//
//}

package com.example.book4u;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LivroDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public LivroDAO(Context applicationContext) {
        conexao = new Conexao(applicationContext);
        banco = conexao.getWritableDatabase();
    }

    public long cadastrarLivro(Livro livro){
        ContentValues values = new ContentValues();
        values.put("idLivro", livro.getId());
        values.put("tituloLivro", livro.getTitulo());
        values.put("autorLivro", livro.getAutor());

        return banco.insert("tbLivro", null, values);
    }

    public Livro buscarLivro(String id){
        Livro livro = new Livro();
        Cursor cursor = banco.query("tbLivro",
                new String[]{
                    "idLivro," +
                    "tituloLivro," +
                    "autorLivro"
                },
                "idLivro = ?",
                new String[]{id},
                null,
                null,
                null,
                String.valueOf(1));

        while(cursor.moveToNext()){
            livro.setId(cursor.getString(0));
            livro.setTitulo(cursor.getString(1));
            livro.setAutor(cursor.getString(2));
        }

        return livro;
    }
}

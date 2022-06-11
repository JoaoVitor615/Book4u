package com.example.book4u;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {

    private SQLiteDatabase db;
    private BancoDeDados banco;

    public BancoController(Context context){
        banco = new BancoDeDados(context);
    }

    public String addLivro(Livro livro){
        ContentValues values;
        long resultado;

        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put(banco.Coluna_Id, livro.getId());
        values.put(banco.Coluna_Titulo, livro.getTitulo());
        values.put(banco.Coluna_Autor, livro.getAutor());
        values.put(banco.Coluna_Pag, livro.getPagina());
        values.put(banco.Coluna_Cat, livro.getCategoria());
        values.put(banco.Coluna_Link, livro.getLink());

        resultado = db.insert(BancoDeDados.Tabela_Livro, null, values);
        db.close();

        if (resultado ==-1) {
            return "Erro ao inserir registro";
        }else {
            return "Registro Inserido com sucesso";
        }

    }
}

package com.example.book4u;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FavoritosDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public FavoritosDAO(Context applicationContext) {
        conexao = new Conexao(applicationContext);
        banco = conexao.getWritableDatabase();
    }

    public List<Favoritos> listarFavoritos(String fkUsuario){
        List<Favoritos> listaFavoritos = new ArrayList<>();

        Cursor cursor = banco.query("tbFavoritos",
                new String[]{
                        "fkLivro"
                },
                "fkUsuario = ?",
                new String[]{fkUsuario},
                null,
                null,
                null);

        while(cursor.moveToNext()){
            Favoritos favoritos = new Favoritos();

            favoritos.setLivro(cursor.getString(0));

            listaFavoritos.add(favoritos);
        }

        return listaFavoritos;
    }

    public long cadastrarFavorito(Usuario usuario, Livro livro){
        ContentValues values = new ContentValues();
        values.put("fkUsuario", usuario.getId());
        values.put("idLivro", livro.getId());

        return banco.insert("tbFavoritos", null, values);
    }


}

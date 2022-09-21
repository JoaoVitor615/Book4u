package com.example.book4u;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Favoritos {
    private int usuario;
    private String livro;

    public Favoritos(int idUsuario, String idLivro){
        usuario = idUsuario;
        livro = idLivro;
    }

    public Favoritos(){ }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }
}

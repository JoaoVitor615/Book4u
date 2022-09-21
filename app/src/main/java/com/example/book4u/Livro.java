package com.example.book4u;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Livro {

    private String id, titulo, autor, link;

    public Livro(String idLivro, String tituloLivro, String autorLivro, String linkLivro) {
        id = idLivro;
        titulo = tituloLivro;
        autor = autorLivro;
        link = linkLivro;
    }

    public Livro(String idLivro){

    }

    public Livro() {

    }

    //métodos get
    public String getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }

    //métodos set
    public void setId(String id){
        this.id = id;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

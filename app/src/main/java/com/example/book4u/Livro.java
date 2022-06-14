package com.example.book4u;

public class Livro {

    private String id;
    private String titulo;

    public Livro(String idLivro, String tituloLivro) {
        this.id=idLivro;
        this.titulo=tituloLivro;

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

    //métodos set
    public void setId(String id){
        this.id = id;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }




}

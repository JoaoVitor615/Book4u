package com.example.book4u;

public class Livro {

    private String id;

    private String titulo;

    private String autor;

    private String pagina;

    private String categoria;

    private String link;

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getPagina() {
        return pagina;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getLink() {
        return link;
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

    public void setPagina(String pagina){
        this.pagina = pagina;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public void setLink(String link){
        this.link = link;
    }


}

package com.example.book4u;

import android.content.Context;

public class Usuario {
    private String Nome, Login, Senha;
    private int Id;

    public Usuario(){}

    public Usuario(int id, String nome, String login, String senha){
        Id = id;
        Nome = nome;
        Login = login;
        Senha = senha;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}

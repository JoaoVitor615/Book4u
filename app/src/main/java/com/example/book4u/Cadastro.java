package com.example.book4u;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cadastro extends AppCompatActivity {

    Button btnCadastrar;
    EditText txtNome, txtEmail, txtSenha;
    String ClienteNome, ClienteEmail, ClienteSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro);

        btnCadastrar = findViewById(R.id.btncadastrar);
        txtNome = findViewById(R.id.nome);
        txtEmail = findViewById(R.id.email);
        txtSenha = findViewById(R.id.senha);

        btnCadastrar.setOnClickListener(v -> {
            ClienteNome = txtNome.getText().toString();
            ClienteEmail = txtEmail.getText().toString();
            ClienteSenha = txtSenha.getText().toString();

            Usuario usuario = new Usuario( 1, ClienteNome, ClienteEmail, ClienteSenha);

            UsuarioDAO usuarioDAO = new UsuarioDAO(Cadastro.this);

            try {
                usuarioDAO.cadastrarUsuario(usuario);
                Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
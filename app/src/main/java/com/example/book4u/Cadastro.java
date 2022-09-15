package com.example.book4u;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cadastro extends AppCompatActivity {

    Button btnConcluir;
    TextView txtTitulo;
    EditText txtNome, txtLogin, txtSenha;
    String UsuarioNome, UsuarioLogin, UsuarioSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastro);

        btnConcluir = findViewById(R.id.btnConcluir);
        txtNome = findViewById(R.id.txtNome);
        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtSenha);
        txtTitulo = findViewById(R.id.txtTitulo);

        Intent intent = getIntent();

        // EDITAR
        if (intent.hasExtra("Usuario")) {
            txtTitulo.setText(R.string.editar);

            Usuario usuario = ((Usuario) intent.getSerializableExtra("Usuario"));

            txtNome.setText(usuario.getNome());
            txtLogin.setText(usuario.getLogin());

            btnConcluir.setOnClickListener(v -> {
                UsuarioNome = txtNome.getText().toString();
                UsuarioLogin = txtLogin.getText().toString();
                UsuarioSenha = txtSenha.getText().toString();

                Usuario usuarioUpdate = new Usuario(
                        usuario.getId(),
                        UsuarioLogin,
                        UsuarioSenha,
                        UsuarioNome);

                UsuarioDAO usuarioDAO = new UsuarioDAO(Cadastro.this);

                try{
                    usuarioDAO.updateUsuario(usuarioUpdate);
                    Toast.makeText(getApplicationContext(), "Alteração efetuada.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), ProfileActivity.class));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
        // CADASTRAR
        else{
            btnConcluir.setOnClickListener(v -> {
                UsuarioNome = txtNome.getText().toString();
                UsuarioLogin = txtLogin.getText().toString();
                UsuarioSenha = txtSenha.getText().toString();

                Usuario usuario = new Usuario(UsuarioLogin, UsuarioSenha, UsuarioNome);

                UsuarioDAO usuarioDAO = new UsuarioDAO(Cadastro.this);

                try {
                    usuarioDAO.cadastrarUsuario(usuario);
                    Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getBaseContext(), LoginActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
package com.example.book4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProfileActivity extends AppCompatActivity {

    private static final String FILE_NAME = "usuarioLogado.json";
    TextView txtUsuario;
    EditText txtLogin, txtSenha;
    Button btnEditar, btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtLogin = findViewById(R.id.edtxtLogin);
        txtSenha = findViewById(R.id.edtxtSenha);
        btnEditar = findViewById(R.id.btnEditar);
        btnSair = findViewById(R.id.btnSair);

        Gson gson = new Gson();
        String usuarioJson = lerDados();
        Usuario usuario = gson.fromJson(usuarioJson, Usuario.class);

        txtUsuario.setText(usuario.getNome());
        txtLogin.setHint(usuario.getLogin());

        btnEditar.setOnClickListener(v -> {
            Intent intentEditar = new Intent(getApplicationContext(), Cadastro.class);
            intentEditar.putExtra("Usuario", usuario);
            startActivity(intentEditar);
        });

        ImageButton btnClose = findViewById(R.id.close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }
        });

        btnSair.setOnClickListener(v -> {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        });
    }

    private String lerDados() {
        FileInputStream fis;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
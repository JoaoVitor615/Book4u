package com.example.book4u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity {

    ListView listViewFavoritos;
    List<Favoritos> listaFavoritos;
    FavoritosDAO favoritosDAO;
    private static final String FILE_NAME = "usuarioLogado.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        Gson gson = new Gson();
        String usuarioJson = lerDados();
        Usuario usuario = gson.fromJson(usuarioJson, Usuario.class);

        String fkUsuario = String.valueOf(usuario.getId());

        favoritosDAO = new FavoritosDAO(getApplicationContext());
        listaFavoritos = favoritosDAO.listarFavoritos(fkUsuario);

        listViewFavoritos = findViewById(R.id.listviewFavoritos);

        ListViewFavoritosAdapter adapter = new ListViewFavoritosAdapter(this, R.layout.listview_favoritos, listaFavoritos);
        listViewFavoritos.setAdapter(adapter);
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
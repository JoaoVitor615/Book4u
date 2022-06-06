package com.example.book4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EditText nomeLivro;
    private TextView nomeTitulo;
    private TextView nomeAutor;


    ImageButton btnVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);
        nomeLivro = findViewById(R.id.inputLivro);
        nomeTitulo = findViewById(R.id.tituloText);
        nomeAutor = findViewById(R.id.autorText);
        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        btnVoltar = findViewById(R.id.VoltarLivros);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void buscaLivros(View view) {
        String queryString = nomeLivro.getText().toString();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
            nomeAutor.setText(R.string.vazio);
            nomeTitulo.setText(R.string.carregando);
        } else {
            if (queryString.length() == 0) {
                nomeAutor.setText(R.string.vazio);
                nomeTitulo.setText(R.string.termo_vazio);
            } else {
                nomeAutor.setText(" ");
                nomeTitulo.setText(R.string.sem_conexao);
            }
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new LoadLivros(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            int i = 0;
            String titulo = null;
            String autor = null;
            while (i < itemsArray.length() &&
                    (autor == null && titulo == null)) {
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                try {
                    titulo = volumeInfo.getString("title");
                    autor = volumeInfo.getString("authors");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
            if (titulo != null && autor != null) {
                nomeTitulo.setText(titulo);
                nomeAutor.setText(autor);
            } else {
                nomeTitulo.setText(R.string.sem_resultado);
                nomeAutor.setText(R.string.vazio);
            }
        } catch (Exception e) {
            nomeTitulo.setText(R.string.sem_resultado);
            nomeAutor.setText(R.string.vazio);
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }
}
package com.example.book4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import android.widget.Toast;


public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EditText nomeLivro;
    private TextView nomeTitulo;
    private TextView nomeAutor;
    private TextView nomePag;
    private TextView nomeCat;
    private Button buttonLink;
    public String stringLink = null;

    Livro livro = new Livro();
    BancoDeDados db=new BancoDeDados(this);

    ImageButton btnVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);
        nomeLivro = findViewById(R.id.inputLivro);
        nomeTitulo = findViewById(R.id.tituloText);
        nomeAutor = findViewById(R.id.autorText);
        nomePag = findViewById(R.id.pagText);
        nomeCat = findViewById(R.id.catText);
        buttonLink = findViewById(R.id.btnLink);
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

        buttonLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stringLink != null){
                    gotoUrl(stringLink);
                }else{
                    Toast.makeText(getApplicationContext(), "Sem Link disponível", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void gotoUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
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
            String id = null;
            String titulo = null;
            String autor = null;
            String pag = null;
            String cat = null;
            String link = null;

            while (i < itemsArray.length() &&
                    (autor == null && titulo == null)) {
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                try {
                    id = book.getString("id");
                    titulo = volumeInfo.getString("title");
                    autor = volumeInfo.getString("authors");
                    pag = volumeInfo.getString("pageCount");
                    cat = volumeInfo.getString("categories");
                    link = volumeInfo.getString("previewLink");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }

            if (titulo != null && autor != null) {


                livro.setId(id);
                livro.setTitulo(titulo);
                livro.setAutor(autor);
                livro.setPagina(pag);
                livro.setCategoria(cat);
                livro.setLink(link);



//                nomeTitulo.setText(titulo);
//
//                autor = autor.replaceAll("\\[", "");
//                autor = autor.replaceAll("\\]", "");
//                autor = autor.replaceAll("\\\"", "");
//                //nomeAutor.setText(autor);
//
//                //nomePag.setText("N° de páginas: " + pag);
//
//                if(cat != null){
//                    cat = cat.replaceAll("\\[", "");
//                    cat = cat.replaceAll("\\]", "");
//                    cat = cat.replaceAll("\\\"", "");
//                   // nomeCat.setText("Categoria: " + cat);
//                } else {
//                   // nomeCat.setText("Categoria: Não Identificado");
//                }
//
//                stringLink = link;

            } else {
                nomeTitulo.setText(R.string.sem_resultado);
                nomeAutor.setText(R.string.vazio);
                Toast.makeText(getApplicationContext(),"else", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            nomeTitulo.setText(R.string.vazio);
            nomeAutor.setText(R.string.vazio);
            nomeCat.setText(R.string.vazio);
            nomePag.setText(R.string.vazio);
            stringLink = null;
            Toast.makeText(getApplicationContext(),"Nenhum livro encontrado", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }


}
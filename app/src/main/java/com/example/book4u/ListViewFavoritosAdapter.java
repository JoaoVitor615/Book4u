package com.example.book4u;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListViewFavoritosAdapter extends BaseAdapter {
    private final int layout;
    private final Context context;
    List<Favoritos> listaFavoritos;

    public ListViewFavoritosAdapter(Context context, int layout, List<Favoritos> listaFavoritos) {
        this.layout = layout;
        this.context = context;
        this.listaFavoritos = listaFavoritos;
    }

    @Override
    public int getCount() {
        return listaFavoritos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaFavoritos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txtTituloLivro, txtAutorLivro;
        Button btnLink;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ListViewFavoritosAdapter.ViewHolder holder = new ListViewFavoritosAdapter.ViewHolder();

        if(row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtTituloLivro = row.findViewById(R.id.tituloText);
            holder.txtAutorLivro = row.findViewById(R.id.autorText);
            holder.btnLink = row.findViewById(R.id.btnLink);

            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }

        Favoritos favoritos = listaFavoritos.get(position);

        LivroDAO livroDAO = new LivroDAO(context);
        Livro livro = livroDAO.buscarLivro(favoritos.getLivro());

        holder.txtTituloLivro.setText(livro.getTitulo());
        holder.txtAutorLivro.setText(livro.getAutor());

        holder.btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(livro.getLink() != null){
                    Uri uri = Uri.parse(livro.getLink());
                    context.startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }else{
                    Toast.makeText(context, "Sem Link disponível", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return row;
    }
}

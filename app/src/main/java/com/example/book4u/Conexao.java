package com.example.book4u;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "db_book4u.db";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbUsuario(" +
                "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "login TEXT NOT NULL," +
                "senha TEXT NOT NULL," +
                "nome TEXT NOT NULL)");
        db.execSQL("CREATE TABLE tbLivro(" +
                "idLivro INTEGER," +
                "tituloLivro TEXT NOT NULL," +
                "autorLivro TEXT NOT NULL," +
                "linkLivro TEXT)");
        db.execSQL("CREATE TABLE tbFavoritos(" +
                "fkUsuario INTEGER," +
                "fkLivro TEXT," +
                "FOREIGN KEY (fkUsuario) REFERENCES tbUsuario (idUsuario), " +
                "FOREIGN KEY (fkLivro) REFERENCES tbLivro (idLivro))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbUsuario");
        db.execSQL("DROP TABLE IF EXISTS tbFavoritos");

        onCreate(db);
    }

    public Cursor getNovaQuery(String sql)
    {
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        return cursor;
    }
}
//package com.example.book4u;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.DatabaseUtils;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class BancoDeDados extends SQLiteOpenHelper {
//    public static final String Tabela_Livro = "tbLivro";
//    public static final String Coluna_Id = "lv_id";
//    public static final String Coluna_Titulo = "lv_titulo";
//
//
//    private static final String DATABASE_Nome = "LivrosApi.db";
//    private static final int DATABASE_VERSION = 1;
//
//    public BancoDeDados(Context context) {
//        super(context, DATABASE_Nome, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CriaLivro = "create table " + Tabela_Livro + "( "
//                + Coluna_Id + " text primary key AUTOINCREMENT NOT NULL, "
//                + Coluna_Titulo + " text not null);";
//        db.execSQL(CriaLivro);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//    }
//
//    public String addLivro(Livro livro){
//        long resultado;
//        //estancia para escrita no banco
//        SQLiteDatabase db=this.getWritableDatabase();
//
//        ContentValues values= new ContentValues();
//        values.put(Coluna_Id, livro.getId());
//        values.put(Coluna_Titulo, livro.getTitulo());
//
//
//        //inseri no banco
//        resultado = db.insert(Tabela_Livro, null, values);
//        db.close();
//
//        if (resultado ==-1) {
//            return "Erro ao inserir registro";
//        }else{
//            return "Registro Inserido com sucesso";
//        }
//    }
//
//    Livro buscaLivro(String id){
//        SQLiteDatabase db=this.getReadableDatabase();
//
//        Cursor cursor=db.query(Tabela_Livro,
//                new String[]{Coluna_Id, Coluna_Titulo},
//                Coluna_Id+"=?",new String[]{String.valueOf(id)},null, null, null,null);
//        if(cursor!=null && cursor.getCount()>0){
//            cursor.moveToFirst();
//        }
//
//        else if(cursor.getCount() == 0){
//            Livro livroEspecifico = new Livro(
//                    "naoExiste",
//                    "naoExiste");
//            return livroEspecifico;
//        }
//
//
//        Livro livroEspecifico= new Livro(
//                cursor.getString(0),
//                cursor.getString(1));
//        return livroEspecifico;
//
//    }
//
//
//}

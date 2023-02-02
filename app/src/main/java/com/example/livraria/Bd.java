package com.example.livraria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Bd extends SQLiteOpenHelper {
    private Context context;

    private static final String DBNAME="livraria.db";
    private static final String TABELA_NOME= "livro";
    private static final String COLUNA_ID= "id";
    private static final String COLUNA_TITULO= "titulo";
    private static final String COLUNA_AUTOR= "autor";
    private static final String COLUNA_PAGINAS= "paginas";



    public Bd(Context context) {
        super(context, "livraria.db", null, 1);
    }

    @Override //Criar tabelas
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(nome TEXT primary key, senha TEXT)");

        String query = "create TABLE " + TABELA_NOME + " (" + COLUNA_TITULO + " TEXT, " +
                COLUNA_AUTOR + " TEXT, " +
                COLUNA_PAGINAS + " INTEGER);";
        db.execSQL(query);;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuarios ");
        db.execSQL("drop table if exists " +TABELA_NOME);
        onCreate(db);
    }
    public Boolean inserirDados(String nome, String senha){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", nome);
        values.put("senha", senha);

        long result= db.insert("usuarios", null, values);
        if(result==-1)return false;
        else
            return true;
    }

    //verficar usuario
    public Boolean verficarUsuario(String nome){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from usuarios where nome=?", new String[]{nome});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    //Verificar senha

    public Boolean verificarSenha(String nome, String senha){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from usuarios where nome=? and senha=?", new String[]{nome,senha});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
//ADicionar Livro
    void addLivro(String titulo, String autor, int paginas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUNA_TITULO, titulo);
        cv.put(COLUNA_AUTOR, autor);
        cv.put(COLUNA_PAGINAS, paginas);
        long result = db.insert(TABELA_NOME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Erro", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Adicionado com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }
    //ler dados da tabela
    Cursor lerDados(){
        String query = "SELECT * FROM " + TABELA_NOME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    //Atualizar
    void atualizar(String row_id, String titulo, String autor, String paginas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUNA_TITULO, titulo);
        cv.put(COLUNA_AUTOR, autor);
        cv.put(COLUNA_PAGINAS, paginas);

        long result = db.update(TABELA_NOME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Erro", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();
        }

    }
    //Apagar dados
    void apagar(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABELA_NOME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Erro ao apagar.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Apagado com sucesso.", Toast.LENGTH_SHORT).show();
        }
    }

    void apagarTudo(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABELA_NOME);
    }
}

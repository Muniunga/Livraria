package com.example.livraria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Bd extends SQLiteOpenHelper {

    public static final String DBNAME="noticias.db";

    public Bd(Context context) {
        super(context, "noticias.db", null, 1);
    }

    @Override //Criar tabelas
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(nome TEXT primary key, senha TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuarios ");
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
}

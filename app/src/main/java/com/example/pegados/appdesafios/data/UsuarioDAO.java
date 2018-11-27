package com.example.pegados.appdesafios.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // modelo singleton

    private static UsuarioDAO instance;
    private SQLiteDatabase db;

    private String[] colunas = {"id", "cpf", "nome", "email"};

    public static UsuarioDAO getInstance(Context context){
        if (instance == null){
            instance = new UsuarioDAO(context.getApplicationContext());
        }
        return instance;
    }

    private UsuarioDAO(Context context){
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public void save(Usuario usuario){

        ContentValues values = new ContentValues();
        values.put("cpf", usuario.getCpf());
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());

        long id = db.insert("usuario", null, values);
        usuario.setId((int)id);
    }


}

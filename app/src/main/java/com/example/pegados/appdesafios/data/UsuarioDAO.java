package com.example.pegados.appdesafios.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.pegados.appdesafios.activity.LoginActivity;

public class UsuarioDAO {

    // modelo singleton

    private static UsuarioDAO instance;
    private SQLiteDatabase db;

    private String[] colunas = {"id", "cpf", "nome", "email", "senha"};

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
        values.put("senha", usuario.getSenha());

        long id = db.insert("usuario", null, values);
        usuario.setId((int)id);
    }


    public  boolean loginOK(String email, String senha){
        String[] columns = {"email", "senha"};
        try(Cursor c = db.query("usuario", columns, null, null, null, null, "email")){
            if (c.moveToFirst()){
                do {
                    if (email.equals(c.getString(c.getColumnIndex("email"))) &&
                            senha.equals(c.getString(c.getColumnIndex("senha")))){
                            return true;
                    }

                }while ( (c.moveToNext()));
            }
        }

        return false;


    }


}

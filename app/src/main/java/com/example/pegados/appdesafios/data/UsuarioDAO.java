package com.example.pegados.appdesafios.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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


    public int loginOK(String email, String senha){
        String[] columns = {"id", "email", "senha"};
        try(Cursor c = db.query("usuario", columns, null, null, null, null, "email")){
            if (c.moveToFirst()){
                do {
                    if (email.equals(c.getString(c.getColumnIndex("email"))) &&
                            senha.equals(c.getString(c.getColumnIndex("senha")))){
                            return c.getInt(c.getColumnIndex("id"));
                    }

                }while ( (c.moveToNext()));
            }
        }

        return 0;

    }


    // método que retorna a tupla do atual cursor
    private static Usuario fromCursor(Cursor c){
        int id = c.getInt(c.getColumnIndex("id"));
        String nome = c.getString(c.getColumnIndex("cpf"));
        String cpf = c.getString(c.getColumnIndex("nome"));
        String email = c.getString(c.getColumnIndex("email"));
        String senha = c.getString(c.getColumnIndex("senha"));

        return new Usuario(id, nome, cpf, email, senha);
    }


    public String VerificaCpf(Usuario usuario){
        String[] columns = {"id", "cpf"};
        try(Cursor c = db.query("usuario", columns, null, null, null, null, null)){
            if (c.moveToFirst()){
                do {
                    if (usuario.getCpf().equals(c.getString(c.getColumnIndex("cpf")))){
                        usuario.setId(c.getInt(c.getColumnIndex("id")));
                        update(usuario);
                        return usuario.getNome()+", o cpf já existia e os dados foram atualizados, ID "+usuario.getId();
                    }

                }while ( (c.moveToNext()));
            }
        }

        save(usuario);
        return usuario.getNome()+", foi inserido com sucesso com o ID "+usuario.getId();
    }

    public void update (Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("cpf", usuario.getCpf());
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());
        db.update(
                "usuario",
                values,
                "cpf" + "=?",
                new String[]{String.valueOf(usuario.getCpf())}
        );
    }


}

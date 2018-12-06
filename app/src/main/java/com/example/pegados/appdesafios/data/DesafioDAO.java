package com.example.pegados.appdesafios.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DesafioDAO {

    private static DesafioDAO instance;
    private SQLiteDatabase db;

    public static DesafioDAO getInstance(Context context){
        if (instance == null){
            instance = new DesafioDAO(context.getApplicationContext());
        }
        return instance;
    }

    private DesafioDAO(Context context){
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }


    public void save(Desafio desafio){

        ContentValues values = new ContentValues();
        values.put("idUsuario", desafio.getIdUsuario());
        values.put("titulo", desafio.getTitulo());
        values.put("questao", desafio.getQuestao());
        values.put("resposta", desafio.getResposta());
        values.put("valorTentativa", desafio.getValorTentativa());
        values.put("valorPremio", desafio.getValorPremio());

        long id = db.insert("desafio", null, values);
        desafio.setId((int)id);
    }


    public List<Desafio> list(){
        String[] columns = {
                "id",
                "idUsuario",
                "titulo",
                "questao",
                "resposta",
                "valorTentativa",
                "valorPremio"
        };

        List<Desafio> desafios = new ArrayList<>();

        try(Cursor c = db.query("desafio", columns, null, null, null, null, "titulo")){
            if (c.moveToFirst()){
                do {
                    Desafio d = DesafioDAO.fromCursor(c);
                    desafios.add(d);
                }while ( (c.moveToNext()));
            }
        }

        return desafios;


    }


    private static Desafio fromCursor(Cursor c){
        int id = c.getInt(c.getColumnIndex("id"));
        int idUsuario = c.getInt(c.getColumnIndex("idUsuario"));
        String titulo = c.getString(c.getColumnIndex("titulo"));
        String questao = c.getString(c.getColumnIndex("questao"));
        String resposta = c.getString(c.getColumnIndex("resposta"));
        double valorTentativa = c.getDouble(c.getColumnIndex("valorTentativa"));
        double valorPremio = c.getDouble(c.getColumnIndex("valorPremio"));

        return new Desafio(id, idUsuario, titulo, questao, resposta, valorTentativa, valorPremio);
    }

}

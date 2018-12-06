package com.example.pegados.appdesafios.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pegados.appdesafios.R;
import com.example.pegados.appdesafios.data.Desafio;
import com.example.pegados.appdesafios.data.DesafioDAO;

public class EditDesafio extends AppCompatActivity{


    private EditText edt_titulo;
    private EditText edt_questao;
    private EditText edt_resposta;
    private EditText edt_valorTentativa;
    private EditText edt_valorPremio;

    private DesafioDAO desafioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_desafio);

        edt_titulo = findViewById(R.id.edt_titulo);
        edt_questao = findViewById(R.id.edt_questao);
        edt_resposta = findViewById(R.id.edt_resposta);
        edt_valorTentativa = findViewById(R.id.valor_tentativa);
        edt_valorPremio = findViewById(R.id.valor_premio);

        desafioDAO = DesafioDAO.getInstance(this);

    }

    public void cadastrarDesafio(View view) {
        double valorTentativa = 0;
        double valorPremio = 0;

        String titulo = edt_titulo.getText().toString();
        String questao = edt_questao.getText().toString();
        String resposta = edt_resposta.getText().toString();

        if (!edt_valorTentativa.getText().toString().equals("")){
            valorTentativa = Double.parseDouble(edt_valorTentativa.getText().toString());

        } if (!edt_valorPremio.getText().toString().equals("")){
            valorPremio = Double.parseDouble(edt_valorPremio.getText().toString());
        }


        if (titulo.equals("")){
            edt_titulo.setError("Este campo precisa ser preenchido");
            return;
        }
        if (questao.equals("")){
            edt_questao.setError("Este campo precisa ser preenchido");
            return;
        }
        if (resposta.equals("")){
            edt_resposta.setError("Este campo precisa ser preenchido");
        }

        if (valorTentativa < 0){
            valorTentativa = 0;
        }
        if (valorPremio < 0){
            valorPremio = 0;
        }


        int idUsuario = 0;

        if (getIntent() != null){

            idUsuario = getIntent().getIntExtra("idUsuario", 0);
        }


        Desafio desafio = new Desafio(idUsuario, titulo, questao, resposta, valorTentativa, valorPremio);

        desafioDAO.save(desafio);

        String msg = "Desafio cadastrado com o ID "+ desafio.getId() +" e ID do usuário "+desafio.getIdUsuario();
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        setResult(100);
        finish();
    }

    public void cancelar(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}

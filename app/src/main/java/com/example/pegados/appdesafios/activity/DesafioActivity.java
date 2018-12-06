package com.example.pegados.appdesafios.activity;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pegados.appdesafios.R;
import com.example.pegados.appdesafios.data.Desafio;
import com.example.pegados.appdesafios.dialog.RespostaDialog;
import com.example.pegados.appdesafios.fragments.QuestaoFragment;
import com.example.pegados.appdesafios.fragments.RespostaFragment;
import com.example.pegados.appdesafios.service.TimeService;

public class DesafioActivity extends AppCompatActivity implements RespostaFragment.VerificaResposta {

    private QuestaoFragment questaoFragment;
    private RespostaFragment respostaFragment;

    private Intent intent;
    private TimeService service;

    private TextView questao;
    private EditText resposta;
    private Desafio desafioCorrente;

    Intent intentDesafio = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafio);

        intentDesafio = getIntent();

        // vou carregar dois fragments
        // Um para a parte da questão

        // e outro para a parte da resposta

        questaoFragment = (QuestaoFragment) getSupportFragmentManager().findFragmentById(R.id.frag_questao);

        respostaFragment = (RespostaFragment) getSupportFragmentManager().findFragmentById(R.id.frag_resposta);


        desafioCorrente = (Desafio) intentDesafio.getSerializableExtra("desafio");

        questao = (TextView) questaoFragment.getView().findViewById(R.id.txt_questao);
        resposta = (EditText) respostaFragment.getView().findViewById(R.id.edit_resposta);

        questao.setText(desafioCorrente.getQuestao());
        resposta.setHint(String.format("A resposta tem %d caracteres", desafioCorrente.getResposta().length()));
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Iniciando o service
        intent = new Intent(getApplicationContext(), TimeService.class);
        startService(intent);
        TimeServiceConnection conn = new TimeServiceConnection();
        boolean conexao = bindService(intent, conn, 0);

        if(conexao){
            Toast.makeText(getApplicationContext(), "A Conexão com o Service foi estabelecida", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "A Conexão com o Service não foi estabelecida", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void verificarResposta() {

        new AlertDialog.Builder(this).setTitle("Enviar Resposta")
                .setMessage("Você tem certeza que deseja ")
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (desafioCorrente.getResposta().equals(resposta.getText().toString())){
                            Toast.makeText(getApplicationContext(), "VOCÊ ACERTOU", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getApplicationContext(), "VOCÊ ERROU", Toast.LENGTH_SHORT).show();
                        }


                        Toast.makeText(getApplicationContext(), String.format("O desafio foi respondido em %s segundos", service.getSeconds()), Toast.LENGTH_SHORT).show();

                        if (intent != null){
                            stopService(intent);
                        }
                    }
                }).setNegativeButton("não", null)
        .show();

    }


    // Criando um innerClass para o Service

    private class TimeServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {

            TimeService.TimeServiceBind binder = (TimeService.TimeServiceBind) iBinder;
            DesafioActivity.this.service = binder.getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intent != null){
            stopService(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (intent != null){
            stopService(intent);
        }
    }
}

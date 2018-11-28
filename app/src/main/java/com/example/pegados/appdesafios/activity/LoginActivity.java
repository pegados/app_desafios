package com.example.pegados.appdesafios.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pegados.appdesafios.R;
import com.example.pegados.appdesafios.data.UsuarioDAO;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;

    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.email_login);
        edtSenha = findViewById(R.id.senha_login);

        usuarioDAO = UsuarioDAO.getInstance(this);

    }


    public void entrar(View view) {
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();

        if (email.equals("")){
            edtEmail.setError("Este campo precisa ser preenchido");
            return;
        }
        if (senha.equals("")){
            edtEmail.setError("Este campo precisa ser preenchido");
            return;
        }

        if (usuarioDAO.loginOK(edtEmail.getText().toString(), edtSenha.getText().toString())){
            Toast.makeText(this, "Logou", Toast.LENGTH_SHORT).show();

            // Aqui o usuário autenticado irá ter acesso a lista de Desafios. ListDesafios



        }else{
            Toast.makeText(this, "Email ou senha errado(s)",Toast.LENGTH_SHORT).show();
        }
    }


    public void cadastrar(View view) {

        Intent intent = new Intent(this, CadastroActivity.class);
        startActivityForResult(intent, 1);


    }
}

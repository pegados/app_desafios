package com.example.pegados.appdesafios.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
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
            edtSenha.setError("Este campo precisa ser preenchido");
            return;
        }

        if (usuarioDAO.loginOK(edtEmail.getText().toString(), edtSenha.getText().toString())){
            Toast.makeText(this, "Logou", Toast.LENGTH_SHORT).show();

            // Aqui o usuário autenticado irá ter acesso a lista de Desafios. ListDesafios
            // Pegar o id do Usuário


        }else{
            Toast.makeText(this, "Email ou senha errado(s)",Toast.LENGTH_LONG).show();
        }
    }


    public void cadastrar(View view) {

        Intent intent = new Intent(this, CadastroActivity.class);
        startActivityForResult(intent, 1);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            Toast.makeText(this, data.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
            edtEmail.setText(data.getStringExtra("email"));
            edtSenha.setText(data.getStringExtra("senha"));
        }

    }
}

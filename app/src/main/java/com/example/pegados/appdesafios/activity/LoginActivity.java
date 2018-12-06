package com.example.pegados.appdesafios.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pegados.appdesafios.R;
import com.example.pegados.appdesafios.data.UsuarioDAO;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;

    private UsuarioDAO usuarioDAO;
    private int idUsuario = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Toolbar toolbar = findViewById(R.id.my_toolbar);
        //setSupportActionBar(toolbar);

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

        if (usuarioDAO.loginOK(edtEmail.getText().toString(), edtSenha.getText().toString()) > 0){
            Toast.makeText(this, "Logou", Toast.LENGTH_SHORT).show();
            idUsuario = usuarioDAO.loginOK(edtEmail.getText().toString(), edtSenha.getText().toString());
            // Aqui o usuário autenticado irá ter acesso a lista de Desafios. ListDesafios
            // Pegar o id do Usuário

            Intent intent = new Intent(this, ListDesafios.class);
            intent.putExtra("id", idUsuario);
            startActivityForResult(intent, 2);

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
            idUsuario = data.getIntExtra("id", 0);
        }

    }
}

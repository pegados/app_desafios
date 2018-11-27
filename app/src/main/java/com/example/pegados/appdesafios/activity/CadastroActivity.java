package com.example.pegados.appdesafios.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pegados.appdesafios.R;
import com.example.pegados.appdesafios.data.Usuario;
import com.example.pegados.appdesafios.data.UsuarioDAO;

public class CadastroActivity extends AppCompatActivity {


    private EditText edtNome;
    private EditText edtCpf;
    private EditText edtEmail;
    private Usuario usuario;

    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = findViewById(R.id.editNome);
        edtCpf = findViewById(R.id.editCpf);
        edtEmail = findViewById(R.id.editEmail);


        usuarioDAO = usuarioDAO.getInstance(this);


    }
        public void process(View view) {
            String cpf = edtCpf.getText().toString();
            String nome = edtNome.getText().toString();
            String email = edtEmail.getText().toString();

            String msg;


            // verificar se o usuário já existe no banco.

            Usuario usuario = new Usuario(cpf, nome, email);
            usuarioDAO.save(usuario);
            msg = "Usuario gravado com ID = " + usuario.getId();

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }
}

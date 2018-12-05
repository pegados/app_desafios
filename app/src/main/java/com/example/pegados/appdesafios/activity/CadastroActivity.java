package com.example.pegados.appdesafios.activity;

import android.content.Intent;
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
    private EditText edtSenha;
    //private Usuario usuario;

    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = findViewById(R.id.editNome);
        edtCpf = findViewById(R.id.editCpf);
        edtEmail = findViewById(R.id.editEmail);
        edtSenha = findViewById(R.id.editSenha);


        usuarioDAO = usuarioDAO.getInstance(this);


    }
        public void process(View view) {
            String cpf = edtCpf.getText().toString();
            String nome = edtNome.getText().toString();
            String email = edtEmail.getText().toString();
            String senha = edtSenha.getText().toString();

            if (nome.equals("")){
                edtNome.setError("Este campo precisa ser preenchido");
                return;
            }

            if (email.equals("")){
                edtEmail.setError("Este campo precisa ser preenchido");
                return;
            }

            if (cpf.equals("")){
                edtCpf.setError("O CPF precisa ser preenchido");
                return;
            }
            if (senha.equals("")){
                edtSenha.setError("Este campo precisa ser preenchido");
                return;
            }



            // verificação de requisitos
            // senha
            if (senha.length() < 6){
                edtSenha.setError("A senha precisa ter ao menos 6 caracteres");
                return;
            }

            // cpf

            if (cpf.length() != 11){
                edtCpf.setError("CPF inválido. Precisa ter ao 11 dígitos");
                return;
            }

            // email
            if (email.split("@").length < 2){
                edtEmail.setError("Email inválido");
                return;
            }


            String msg;


            // verificar se o usuário já existe no banco.

            Usuario usuario = new Usuario(cpf, nome, email, senha);


            //usuarioDAO.save(usuario);
            msg = usuarioDAO.VerificaCpf(usuario);

            Intent data = new Intent();
            data.putExtra("email", usuario.getEmail());
            data.putExtra("senha", usuario.getSenha());
            data.putExtra("msg", msg);
            setResult(RESULT_OK, data);
            finish();
        }
}

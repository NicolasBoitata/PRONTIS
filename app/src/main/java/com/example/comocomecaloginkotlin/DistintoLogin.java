package com.example.comocomecaloginkotlin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class DistintoLogin extends AppCompatActivity {

//    Validação no formato do e-mail

    private EditText edtSenha, edtEmail ;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distinto_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);

        edtEmail.requestFocus();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=edtEmail.getText().toString();
                String senha=edtSenha.getText().toString();

                if(email.isEmpty() || senha.isEmpty()){
                    mensagem("Preencha os campos");
                } else if(!verificarEmail(email)){
                    mensagem("E-mail invalido");
                } else if(validaEmailSenha(email,senha)){

                    Intent intent = new Intent(getApplicationContext(), TelaSecundario.class);
                    intent.putExtra("enviaEmail",email);
                    startActivity(intent);
                }else {
                    mensagem( "Login Incorreto!");
                }
            }
        });
    }


    private boolean validaEmailSenha(String email, String senha) {
        boolean sucesso = false;

        if(email.equals("Login@dominio.com") && senha.equals("123")){
            sucesso = true;
        }else if (email.equals("Adm@dominio.com") && senha.equals("adm")){
            sucesso = true;
        }

        return sucesso;
    }

    private boolean verificarEmail(String email) {
        Pattern patterns = Patterns.EMAIL_ADDRESS;
        return patterns.matcher(email).matches();
    }

    private void mensagem(String info){
        Toast.makeText(DistintoLogin.this, info, Toast.LENGTH_LONG).show();
        edtEmail.requestFocus();
    }
}
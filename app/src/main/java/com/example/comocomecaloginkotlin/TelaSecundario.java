package com.example.comocomecaloginkotlin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class TelaSecundario extends AppCompatActivity {

//    Validação no formato do e-mail

    Button btnAdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_secundario);

        btnAdm = findViewById(R.id.btnAdm);

        Bundle recuperaEmail = getIntent().getExtras();
        String email="";

        if(recuperaEmail != null){
            email = recuperaEmail.getString("enviaEmail");
        }

        setTitle("Usuário Logado: " + email );

        if(email.equals("Adm@dominio.com")) {
            btnAdm.setEnabled(true);
        }else{
            btnAdm.setEnabled(false);
        }

    }

    public void voltar(View view) { finish(); }
}
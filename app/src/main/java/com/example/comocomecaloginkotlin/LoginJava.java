package com.example.comocomecaloginkotlin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import org.checkerframework.checker.nullness.qual.NonNull;

//https://www.youtube.com/shorts/L1Xpaozbtpg

public class LoginJava extends AppCompatActivity {

    private TextView text_tela_cadastro;
    private EditText edit_senha, edit_email ;
    private Button btn_entrar;
    private ProgressBar progressBar;
    String[] mensagens = {"preencha todos os campos", "Login Efetuado com Sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_java);

//        getSupportActionBar().hide();
        IniciarComponentes();

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginJava.this,FormCadastro.class);
                startActivity(intent);
            }
        });

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    AutenticarUsuario(v);
                }
            }
        });
    }

    private void AutenticarUsuario(View view) {

        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();


        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity();
                        }
                    }, 500);
                }else {
                    String erro;

                    try {
                        throw task.getException();

                    }catch (Exception e){
                        erro = "Erro ao logar Usuário";
                    }
                        Snackbar snackbar = Snackbar.make(view,erro, Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();
                }
            }
        });
    }

    private void MainActivity(){
        Intent intent = new Intent(LoginJava.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void IniciarComponentes() {
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        btn_entrar = findViewById(R.id.btn_entrar);
        progressBar = findViewById(R.id.progressbar);
    }
}

        // Verifica se a ActionBar existe e a esconde
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();


//        IniciarComponentes();

//        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginJava.this, FormCadastro.class);
//                startActivity(intent);
//            }
//        });
//    }
//}
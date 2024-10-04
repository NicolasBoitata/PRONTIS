package com.example.comocomecaloginkotlin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

public class FormLogin : AppCompatActivity() {

    private lateinit var textTelaCadastro: TextView
    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var btEntrar: Button
    private lateinit var progressBar: ProgressBar
    private val mensagens = arrayOf("preencha todos os campos", "Login Efetuado com Sucesso")
//    var MAIN_THREAD_HANDLER: Handler? = null
//    private var Handler MAIN_THREAD_HANDLER = null;
//
//    private lateinit var googleSignInClient: GoogleSignInAccount

    val handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login)

        supportActionBar?.hide()
        iniciarComponentes()

        textTelaCadastro.setOnClickListener {
//            fun onClick(v: View?) {

                val intent = Intent(this, FormCadastro::class.java)
                startActivity(intent)
//            }
        }

        btEntrar.setOnClickListener { v ->
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.WHITE)
                snackbar.setTextColor(Color.BLACK)
                snackbar.show()
            } else {
                autenticarUsuario(v)
            }
        }
    }

    private fun autenticarUsuario(view: View) {
        val email = editEmail.text.toString()
        val senha = editSenha.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    progressBar.visibility = View.VISIBLE

                    Handler(Looper.getMainLooper()).postDelayed({
                        telaPrincipal()
                    }, 3000)
                } else {
                    val erro = try {
                        throw task.exception!!
                    } catch (e: Exception) {
                        "Erro ao logar usuário"
                    }

                    val snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.WHITE)
                    snackbar.setTextColor(Color.BLACK)
                    snackbar.show()
                }
            }
    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (usuarioAtual != null) {
//            TelaPrincipal();
//        }
//    }

        private fun telaPrincipal() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun iniciarComponentes() {
        textTelaCadastro = findViewById(R.id.text_tela_cadastro)
        editEmail = findViewById(R.id.edit_email)
        editSenha = findViewById(R.id.edit_senha)
        btEntrar = findViewById(R.id.bt_entrar)
        progressBar = findViewById(R.id.progressbar)
    }


}

//package com.example.comocomecaloginkotlin;
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.android.material.snackbar.Snackbar;
//
//public class FormLogin extends AppCompatActivity {
//    private TextView text_tela_cadastro;
//    private EditText edit_nome,edit_email,edit_senha;
//    private Button bt_entrar;
//    private ProgressBar progressBar;
//    String[] mensagens = {"preencha todos os campos", "Login Efetuado com Sucesso"};
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form_login);
//
//        getSupportActionBar().hide();
//        IniciarComponentes();
//
//        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(FormLogin.this, FormCadastro.class);
//                startActivity(intent);
//            }
//        });
//
//        bt_entrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String email = edit_email.getText().toString();
//                String senha = edit_senha.getText().toString();
//
//                if (email.isEmpty() || senha.isEmpty()) {
//                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
//                    snackbar.setBackgroundTint(Color.WHITE);
//                    snackbar.setTextColor(Color.BLACK);
//                    snackbar.show();
//                } else {
//                    AutenticarUsuario(v);
//                }
//            }
//        });
//    }
//
//    private void AutenticarUsuario(View view){
//
//        String email = edit_email.getText().toString();
//        String senha = edit_senha.getText().toString();
//
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AutheResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//
//                if(task.isSuccessful()) {
//                    progressBar.setVisibility(View.VISIBLE);
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            TelaPrincipal();
//                        }
//                    }, 3000);
//                }else {
//                    String erro;
//
//                    try {
//                        throw task.getException();
//                    }catch (Exception e){
//                        erro = "Erro ao logar usuário";
//                    }
//                    Snackbar snackbar = Snackbar.make(view,erro, Snackbar.LENGTH_SHORT);
//                    snackbar.setBackgroundTint(Color.WHITE);
//                    snackbar.setTextColor(Color.BLACK);
//                    snackbar.show();
//                }
//            }
//        });
//    }
//
//    private void TelaPrincipal(){
//        Intent intent = new Intent(FormLogin.this,TelaPrincipal.class);
//        startActivity(intent);
//        finish();
//    }
//
//    private void IniciarComponentes(){
//        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
//        edit_email = findViewById(R.id.edit_email);
//        edit_senha = findViewById(R.id.edit_senha);
//        bt_entrar = findViewById(R.id.bt_entrar);
//        progressBar = findViewById(R.id.progressbar);
//    }
//}

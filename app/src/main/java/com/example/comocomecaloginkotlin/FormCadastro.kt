package com.example.comocomecaloginkotlin;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap;

//import java.util.logging.Handler;

public class FormCadastro : AppCompatActivity() {
    private lateinit var editNome: EditText
    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var btCadastrar: Button
    private val mensagens = arrayOf("preencha todos os campos", "Cadastro Realizado com Sucesso")
    private lateinit var usuarioID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cadastro)

        supportActionBar?.hide()
        iniciarComponentes()

        btCadastrar.setOnClickListener { v ->
            val nome = editNome.text.toString()
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.WHITE)
                snackbar.setTextColor(Color.BLACK)
                snackbar.show()
            } else {
                cadastrarUsuario(v)
            }
        }
    }

    private fun cadastrarUsuario(view: View) {
        val email = editEmail.text.toString()
        val senha = editSenha.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    salvarDadosUsuario()
                    val snackbar = Snackbar.make(view, mensagens[1], Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.WHITE)
                    snackbar.setTextColor(Color.BLACK)
                    snackbar.show()
                } else {
                    val erro: String = try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        "Digite uma senha com no mínimo 6 caracteres"
                    } catch (e: FirebaseAuthUserCollisionException) {
                        "Esta conta já foi cadastrada"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        "E-mail inválido"
                    } catch (e: Exception) {
                        "Erro ao cadastrar usuário"
                    }

                    val snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.WHITE)
                    snackbar.setTextColor(Color.BLACK)
                    snackbar.show()
                }
            }
    }

    private fun salvarDadosUsuario() {
        val nome = editNome.text.toString()
        val db = FirebaseFirestore.getInstance()

        val usuarios = hashMapOf<String, Any>(
            "nome" to nome
        )

        usuarioID = FirebaseAuth.getInstance().currentUser!!.uid

        val documentReference = db.collection("Usuarios").document(usuarioID)
        documentReference.set(usuarios)
            .addOnSuccessListener {
                Log.d("db", "Sucesso ao salvar os dados")
            }
            .addOnFailureListener { e ->
                Log.d("db_erro", "Erro ao salvar os dados: ${e.message}")
            }
    }

    private fun iniciarComponentes() {
        editNome = findViewById(R.id.edit_nome)
        editSenha = findViewById(R.id.edit_senha)
        editEmail = findViewById(R.id.edit_email)
        btCadastrar = findViewById(R.id.bt_cadastrar)
    }
}

//
//    private TextView text_tela_cadastro;
//    private EditText edit_nome,edit_email,edit_senha;
//    private Button bt_entrar;
//    private ProgressBar progressBar;
//    String[] mensagens = {"preencha todos os campos", "Login Efetuado com Sucesso"};
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


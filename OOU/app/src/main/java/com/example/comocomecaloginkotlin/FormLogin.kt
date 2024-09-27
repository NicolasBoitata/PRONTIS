package com.example.comocomecaloginkotlin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
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
    private lateinit var editNome: EditText
    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var btEntrar: Button
    private lateinit var progressBar: ProgressBar
    private val mensagens = arrayOf("preencha todos os campos", "Login Efetuado com Sucesso")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login)

        supportActionBar?.hide()
        iniciarComponentes()

        textTelaCadastro.setOnClickListener {
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
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

                    Handler().postDelayed({
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

    private fun telaPrincipal() {
        val intent = Intent(
            this, TelaPrincipal::class.java)
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
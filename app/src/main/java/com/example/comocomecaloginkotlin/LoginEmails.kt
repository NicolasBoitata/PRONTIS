package com.example.comocomecaloginkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginEmails : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_emails)

        auth = FirebaseAuth.getInstance()

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Verificar o domínio do email
                            classifyUserByEmailDomain(email)
                        } else {
                            Toast.makeText(this, "Autenticação falhou.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun classifyUserByEmailDomain(email: String) {
        val domain = email.substringAfter("@")

        if (domain == "merenda.com") {
            // Direcionar para a tela de Admin
            Toast.makeText(this, "Bem-vindo, Administração!", Toast.LENGTH_SHORT).show()
            // startActivity(Intent(this, AdminActivity::class.java))
            val intent = Intent(
                applicationContext,
                MainActivity2::class.java
            )
            startActivity(intent)
        } else {
            // Direcionar para a tela de Usuário Comum
            Toast.makeText(this, "Bem-vindo, Aluno!", Toast.LENGTH_SHORT).show()
            // startActivity(Intent(this, UserActivity::class.java))
            val intent = Intent(
                applicationContext,
                MainActivity::class.java
            )
            startActivity(intent)
        }
    }
}
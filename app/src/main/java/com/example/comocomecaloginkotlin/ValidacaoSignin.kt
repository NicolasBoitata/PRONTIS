package com.example.comocomecaloginkotlin

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class ValidacaoSignin : AppCompatActivity() {

    private lateinit var textTelaCadastro: TextView
    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var btn_enter: Button
    private val mensagens = arrayOf("preencha todos os campos", "Login Efetuado com Sucesso")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validacao_signin)

//        supportActionBar?.hide()
//        iniciarComponentes()


//        btn_enter.setOnClickListener { v ->
//            val email = editEmail.text.toString()
//            val senha = editSenha.text.toString()
//
//            if (email.isEmpty() || senha.isEmpty()) {
//                val snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT)
//                snackbar.setBackgroundTint(Color.WHITE)
//                snackbar.setTextColor(Color.BLACK)
//                snackbar.show()
//            } else {
//                entrar(v)
//            }
//        }
    }

    private fun entrar(context: Context, email: String, pass: String) {

        val auth = FirebaseAuth.getInstance()

        if (email.isNotEmpty() && pass.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        if (user != null && !user.isEmailVerified) {
                            AlertDialog.Builder(context)
                                .setTitle("Erro")
                                .setMessage("O email não foi verificado.")
                                .setPositiveButton("OK", null)
                                .show()
                            return@addOnCompleteListener
                        }

                        val intent = Intent(context, MainActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(intent)
                    } else {
                        task.exception?.let {
                            Toast.makeText(
                                context,
                                "Erro ao entrar: ${it.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        } else {
            Toast.makeText(context, "Email e senha são obrigatórios.", Toast.LENGTH_SHORT).show()
        }
    }
}

//    private fun iniciarComponentes() {
//        editEmail = findViewById(R.id.edit_email)
//        editSenha = findViewById(R.id.edit_senha)
//        btn_enter = findViewById(R.id.btn_enter)
//    }
//}

//const entrar = () => {
//        if (email !== && pass !== '') {
//            auth()
//                .signInWithEmailAndPassword(email, pass)
//                .then(() => {
//                    if (!auth().currentUser.emailVerified) {
//                        Alert.alert('Erго');
//                            return;
//                    }
//                    navigation.dispatch(
//                        CommonActions.reset({
//                            index: 0,
//                            routes: [{name: 'Home'}],
//                        }),
//                    );
//                })
//        }
//    }
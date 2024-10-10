package com.example.comocomecaloginkotlin

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ValidacaoActivity : AppCompatActivity() {

    fun cadastrar(context: Context, nome: String, email: String, pass: String, confirPass: String) {
        val auth = FirebaseAuth.getInstance()

        if (nome.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirPass.isNotEmpty()) {
            if (pass == confirPass) {
                auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            user?.sendEmailVerification()
                                ?.addOnCompleteListener { verificationTask ->
                                    if (verificationTask.isSuccessful) {
                                        AlertDialog.Builder(context)
                                            .setTitle("Informação")
                                            .setMessage("Foi enviado um email para: $email para verificação.")
                                            .setPositiveButton("OK") { _, _ ->
                                                val intent = Intent(context, MainActivity::class.java)
                                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                context.startActivity(intent)
                                            }
                                            .show()
                                    } else {
                                        Toast.makeText(context, "Falha ao enviar email de verificação.", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        } else {
                            task.exception?.let {
                                Toast.makeText(context, "Erro ao cadastrar: ${it.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            } else {
                Toast.makeText(context, "As senhas não coincidem.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Todos os campos são obrigatórios.", Toast.LENGTH_SHORT).show()
        }
    }


}

//    fun cadastrar(nome: String, email: String, pass: String, confirPass: String) {
//        val auth = FirebaseAuth.getInstance()
//
//        if (nome.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirPass.isNotEmpty()) {
//            auth.createUserWithEmailAndPassword(email, pass)
//                .addOnCompleteListener { task ->
//
//                    if (task.isSuccessful) {
//                        val user = auth.currentUser
//                        user?.sendEmailVerification()
//                            ?.addOnCompleteListener { verificationTask ->
//                                if (verificationTask.isSuccessful) {
//                                    AlertDialog.Builder(this)
//                                        .setTitle("Informação")
//                                        .setMessage("Foi enviado um email para: $email para verificação.")
//                                        .setPositiveButton("OK") { _, _ ->
//                                            val intent = Intent(this, MainActivity::class.java)
//                                            intent.flags =
//                                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                            startActivity(intent)
//                                        }
//                                        .show()
//                                }
//                            }
//                    }



//    const cadastar = () => {
//        if (nome !== "" && email !== "" && pass !== "" && confirPass !== "") {
//            auth()
//                .createUserWithEmailAndPassword(email, pass)
//                .then(() => {
//                    let userf auth().currentUser;
//                    userF
//                        .sendEmailVerification()
//                        .then(() => {
//                            Alert.alert(
//                                'Informação',
//                                'Foi enviado um email para: '+ email + 'para verificação.',
//                            );
//                            navigation.dispatch(
//                                CommonActions.reset({
//                                    index: 0,
//                                    routes: [[name: 'SignIn'}],
//                        }),
//                    );
//                })
//            .catch((e) => {
//                console.log('SignUp, cadastar: + e);
//            });
//        })

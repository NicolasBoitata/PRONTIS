package com.example.comocomecaloginkotlin;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;

public class TelaPrincipal : AppCompatActivity() {

    @Override
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_principal)

        supportActionBar?.hide()
//        supportActionBar!!.hide()

    }
}
package com.example.trabalhopaulinho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btIrParaCadastroCliente = findViewById(R.id.btIrParaCadastroCliente);

        btIrParaCadastroCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroClienteActivity.class);
                startActivity(intent);
            }
        });

        Button btIrParaCadastroItem = findViewById(R.id.btnIrParaCadastroItem);

        btIrParaCadastroItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroItemActivity.class);
                startActivity(intent);
            }
        });

        Button btIrParaLancamentoPedido = findViewById(R.id.btIrParaLancamentoPedido);

        btIrParaLancamentoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LancamentoPedidoActivity.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
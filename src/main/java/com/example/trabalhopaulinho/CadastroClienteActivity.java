package com.example.trabalhopaulinho;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.trabalhopaulinho.databinding.ActivityCadastroClienteBinding;

public class CadastroClienteActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextCPF;
    private Button btnCadastrarCliente;
    private TextView textViewPedido;
    private int codigoPedido = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        editTextNome = findViewById(R.id.editTextNome);
        editTextCPF = findViewById(R.id.editTextCPF);
        btnCadastrarCliente = findViewById(R.id.btnCadastrarCliente);
        textViewPedido = findViewById(R.id.textViewPedido);

        btnCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarCliente();
            }
        });
        Button btnVoltarMain = findViewById(R.id.btnVoltarMain);
        btnVoltarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroClienteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void cadastrarCliente() {
        String nome = editTextNome.getText().toString().trim();
        String cpf = editTextCPF.getText().toString().trim();

        if (TextUtils.isEmpty(nome) || TextUtils.isEmpty(cpf)) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String codigoDoPedido = "P" + codigoPedido;
        codigoPedido++;

        textViewPedido.setText("Código do Cliente: " + codigoDoPedido);

        editTextNome.setText("");
        editTextCPF.setText("");

        Toast.makeText(this, "Pedido cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

    }
}
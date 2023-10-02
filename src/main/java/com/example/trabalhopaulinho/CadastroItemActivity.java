package com.example.trabalhopaulinho;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class CadastroItemActivity extends FragmentActivity {

    private EditText editTextCodigo;
    private EditText editTextDescricao;
    private EditText editTextValorUnitario;
    private Button btnAdicionarItem;
    private TextView textViewPedido;
    private int codigoPedido = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item);

        editTextCodigo = findViewById(R.id.editTextCodigo);
        editTextDescricao = findViewById(R.id.editTextDescricao);
        editTextValorUnitario = findViewById(R.id.editTextValorUnitario);
        btnAdicionarItem = findViewById(R.id.btnAdicionarItem);
        textViewPedido = findViewById(R.id.textViewPedido);


        btnAdicionarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarItem();
            }
        });

        Button btnVoltarMain = findViewById(R.id.btnVoltarMain);
        btnVoltarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroItemActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void adicionarItem() {
        String codigo = editTextCodigo.getText().toString().trim();
        String descricao = editTextDescricao.getText().toString().trim();
        String valorUnitarioStr = editTextValorUnitario.getText().toString().trim();

        if (codigo.isEmpty() || descricao.isEmpty() || valorUnitarioStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double valorUnitario = Double.parseDouble(valorUnitarioStr);
        if (valorUnitario <= 0) {
            Toast.makeText(this, "O valor unitário deve ser maior que zero", Toast.LENGTH_SHORT).show();
            return;
        }

        String codigoDoPedido = "P" + codigoPedido;
        codigoPedido++;

        textViewPedido.setText("Número do Pedido: " + codigoDoPedido);

        editTextCodigo.setText("");
        editTextDescricao.setText("");
        editTextValorUnitario.setText("");

        Toast.makeText(this, "Item cadastrado com sucesso", Toast.LENGTH_SHORT).show();
    }
}
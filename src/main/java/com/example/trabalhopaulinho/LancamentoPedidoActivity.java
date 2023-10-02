package com.example.trabalhopaulinho;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.trabalhopaulinho.databinding.ActivityLancamentoPedidoBinding;

import java.util.ArrayList;
import java.util.List;

public class LancamentoPedidoActivity extends AppCompatActivity {

    private EditText editTextCliente;
    private Spinner spinnerItem;
    private EditText editTextQuantidade;
    private EditText editTextValorUnitario;
    private Button buttonAdicionarItem;
    private TextView textViewListaItens;
    private TextView textViewTotal;
    private RadioGroup radioGroupPagamento;
    private EditText editTextParcelas;
    private Button buttonConcluirPedido;
    private double valorTotalComDescontoAcrecimo = 0.0;
    private List<String> itensAdicionados = new ArrayList<>();
    private double valorTotal = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_pedido);

        editTextCliente = findViewById(R.id.editTextCliente);
        spinnerItem = findViewById(R.id.spinnerItem);
        editTextQuantidade = findViewById(R.id.editTextQuantidade);
        editTextValorUnitario = findViewById(R.id.editTextValorUnitario);
        buttonAdicionarItem = findViewById(R.id.buttonAdicionarItem);
        textViewListaItens = findViewById(R.id.textViewListaItens);
        textViewTotal = findViewById(R.id.textViewTotal);
        radioGroupPagamento = findViewById(R.id.radioGroupPagamento);
        editTextParcelas = findViewById(R.id.editTextParcelas);
        buttonConcluirPedido = findViewById(R.id.buttonConcluirPedido);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getItensDeVenda());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItem.setAdapter(adapter);

        buttonAdicionarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarItemAoPedido();
            }
        });

        radioGroupPagamento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonAPrazo) {
                    editTextParcelas.setVisibility(View.VISIBLE);
                } else {
                    editTextParcelas.setVisibility(View.GONE);
                }
            }
        });

        buttonConcluirPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concluirPedido();
            }
        });

        Button btnVoltarMain = findViewById(R.id.btnVoltarMain);
        btnVoltarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LancamentoPedidoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private List<String> getItensDeVenda() {
        List<String> itens = new ArrayList<>();
        itens.add("Item 1");
        itens.add("Item 2");
        itens.add("Item 3");
        return itens;
    }

    private void adicionarItemAoPedido() {
        String cliente = editTextCliente.getText().toString().trim();
        String item = spinnerItem.getSelectedItem().toString();
        String quantidadeStr = editTextQuantidade.getText().toString().trim();
        String valorUnitarioStr = editTextValorUnitario.getText().toString().trim();

        if (cliente.isEmpty() || quantidadeStr.isEmpty() || valorUnitarioStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double quantidade = Double.parseDouble(quantidadeStr);
        double valorUnitario = Double.parseDouble(valorUnitarioStr);
        double subtotal = quantidade * valorUnitario;

        if (quantidade <= 0 || valorUnitario <= 0) {
            Toast.makeText(this, "Quantidade e valor unitário devem ser maiores que zero", Toast.LENGTH_SHORT).show();
            return;
        }

        itensAdicionados.add(item + " - Quantidade: " + quantidade + " - Valor Unitário: R$ " + valorUnitario + " - Subtotal: R$ " + subtotal);
        valorTotal += subtotal;

        atualizarListaItens();
        atualizarValorTotal();

        editTextQuantidade.setText("");
        editTextValorUnitario.setText("");
    }

    private void atualizarListaItens() {
        StringBuilder listaItens = new StringBuilder();
        for (String item : itensAdicionados) {
            listaItens.append(item).append("\n");
        }
        textViewListaItens.setText(listaItens.toString());
    }

    private void atualizarValorTotal() {
        //textViewTotal.setText("Total: R$ " + valorTotal);
        if (radioGroupPagamento.getCheckedRadioButtonId() == R.id.radioButtonAVista) {

            valorTotalComDescontoAcrecimo = valorTotal - (valorTotal * 0.05);
        } else {
            valorTotalComDescontoAcrecimo = valorTotal + (valorTotal * 0.05);
        }
        textViewTotal.setText("Total: R$ " + valorTotalComDescontoAcrecimo);

    }

    private void concluirPedido() {

        if (radioGroupPagamento.getCheckedRadioButtonId() == R.id.radioButtonAVista) {
            valorTotalComDescontoAcrecimo = valorTotal - (valorTotal * 0.05);
        } else {
            valorTotalComDescontoAcrecimo = valorTotal + (valorTotal * 0.05);
        }

        String mensagem;
        if (radioGroupPagamento.getCheckedRadioButtonId() == R.id.radioButtonAVista) {
            mensagem = "Pedido à vista cadastrado com sucesso!";
        } else {
            mensagem = "Pedido a prazo cadastrado com sucesso!";
        }

        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();

        editTextCliente.setText("");
        spinnerItem.setSelection(0);
        editTextQuantidade.setText("");
        editTextValorUnitario.setText("");
        radioGroupPagamento.clearCheck();
        editTextParcelas.setText("");
        itensAdicionados.clear();
        valorTotal = 0.0;
        atualizarListaItens();
        atualizarValorTotal();
    }
}

package com.example.luisfelipe.trb1_dcc196_luisfelipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AtividadeCadastroParticipante extends AppCompatActivity {
    private EditText nomeParticipante;
    private EditText emailParticipante;
    private Button adicionarNovoParticipante;
    private Button voltar;
    private Button concluir;
    private ListView listaRecemCadastrados;
    private ArrayList<Participante> recemCadastrados = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_cadastro_participante);
        nomeParticipante = (EditText)findViewById(R.id.nomeCadastro);
        emailParticipante = (EditText)findViewById(R.id.emailCadastro);
        adicionarNovoParticipante = (Button)findViewById(R.id.adicionarParticipante);
        voltar = (Button)findViewById(R.id.cancelarCadastro);
        concluir = (Button) findViewById(R.id.concluirCadastro);
        listaRecemCadastrados = (ListView) findViewById(R.id.listaRecemCadastrados);

        adicionarNovoParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Participante p = new Participante(nomeParticipante.getText().toString(),emailParticipante.getText().toString());
                nomeParticipante.setText("");
                emailParticipante.setText("");
                recemCadastrados.add(p);
                atualizarLista();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        concluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultado = new Intent();
                resultado.putExtra("recemCadastrados",recemCadastrados);
                setResult(RESULT_OK,resultado);
                finish();
            }
        });

    }
    private void atualizarLista(){
        ArrayList<String> itens = new ArrayList();
        for(Participante p : recemCadastrados){
            itens.add(p.getNome()+ "\t\t"+p.getEmail());
        }
        ArrayAdapter<String> ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itens);
        listaRecemCadastrados.setAdapter(ad);

    }
}

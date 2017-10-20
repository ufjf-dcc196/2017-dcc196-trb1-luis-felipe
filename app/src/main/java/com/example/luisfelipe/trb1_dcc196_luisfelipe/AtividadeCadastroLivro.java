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

public class AtividadeCadastroLivro extends AppCompatActivity {
    private EditText tituloLivro;
    private EditText editoraLivro;
    private EditText anoLivro;
    private Button adicionarNovoLivro;
    private Button cancelar;
    private Button concluir;
    private ListView listaRecemCadastrados;
    private ArrayList<Livro> recemCadastrados = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_cadastro_livro);
        tituloLivro = (EditText)findViewById(R.id.titulo);
        editoraLivro = (EditText)findViewById(R.id.editora);
        anoLivro = (EditText)findViewById(R.id.ano);
        adicionarNovoLivro = (Button)findViewById(R.id.adicionarLivro);
        cancelar = (Button)findViewById(R.id.cancelarCadastro);
        concluir = (Button) findViewById(R.id.concluirCadastro);

        listaRecemCadastrados = (ListView) findViewById(R.id.listaRecemCadastrados);

        adicionarNovoLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Livro l = new Livro(tituloLivro.getText().toString(),editoraLivro.getText().toString(),anoLivro.getText().toString());
                tituloLivro.setText("");
                editoraLivro.setText("");
                anoLivro.setText("");
                recemCadastrados.add(l);
                atualizarLista();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
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
        for(Livro l :recemCadastrados){
            itens.add(l.getTitulo()+"     "+l.getEditora()+"    "+l.getAno());
        }
        ArrayAdapter<String> ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itens);
        listaRecemCadastrados.setAdapter(ad);

    }

}

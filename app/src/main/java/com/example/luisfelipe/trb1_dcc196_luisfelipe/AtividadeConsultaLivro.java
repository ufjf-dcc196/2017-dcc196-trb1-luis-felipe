package com.example.luisfelipe.trb1_dcc196_luisfelipe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AtividadeConsultaLivro extends AppCompatActivity {
    private EditText tituloLivro;
    private EditText editoraLivro;
    private EditText anoLivro;
    private ListView listaReservas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_consulta_livro);

        tituloLivro = (EditText)findViewById(R.id.titulo);
        editoraLivro = (EditText)findViewById(R.id.editora);
        anoLivro = (EditText)findViewById(R.id.ano);
        listaReservas = (ListView)findViewById(R.id.reservados);

        Livro l = (Livro) getIntent().getSerializableExtra("livro");

        tituloLivro.setText(l.getTitulo());
        editoraLivro.setText(l.getEditora());
        anoLivro.setText(l.getAno());


        tituloLivro.setEnabled(false);
        editoraLivro.setEnabled(false);
        anoLivro.setEnabled(false);

        ArrayList<String> itens = new ArrayList();
        for(Participante p : l.getParticipantesReserva()){
            itens.add(p.getNome());
        }
        ArrayAdapter<String> ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itens);
        listaReservas.setAdapter(ad);
    }
}

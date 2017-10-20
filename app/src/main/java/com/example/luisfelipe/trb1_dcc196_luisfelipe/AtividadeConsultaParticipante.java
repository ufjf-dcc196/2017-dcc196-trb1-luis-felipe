package com.example.luisfelipe.trb1_dcc196_luisfelipe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AtividadeConsultaParticipante extends AppCompatActivity {
    private EditText nome;
    private EditText email;
    private EditText entrada;
    private EditText saida;
    private Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_consulta_participante);
        nome = (EditText) findViewById(R.id.nome);
        email = (EditText) findViewById(R.id.email);
        entrada = (EditText) findViewById(R.id.horarioEntrada);
        saida = (EditText) findViewById(R.id.horarioSaida);
        voltar = (Button) findViewById(R.id.voltar);

        Participante p = (Participante) getIntent().getSerializableExtra("participante");

        nome.setText(p.getNome());
        email.setText(p.getEmail());
        entrada.setText(p.getDataEntrada());
        saida.setText(p.getDataSaida());

        nome.setEnabled(false);
        email.setEnabled(false);
        entrada.setEnabled(false);
        saida.setEnabled(false);

        nome.setTextColor(Color.BLACK);
        email.setTextColor(Color.BLACK);
        entrada.setTextColor(Color.BLACK);
        saida.setTextColor(Color.BLACK);


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}

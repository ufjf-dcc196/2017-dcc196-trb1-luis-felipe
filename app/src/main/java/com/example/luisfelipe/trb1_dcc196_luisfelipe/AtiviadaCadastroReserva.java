package com.example.luisfelipe.trb1_dcc196_luisfelipe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AtiviadaCadastroReserva extends AppCompatActivity {
    private ArrayList<Participante> participantes;
    private ArrayList<Livro> livros = new ArrayList();
    private Spinner spLivros;
    private Spinner spParticipantes;
    private Button adicionarReserva;
    private TextView aviso;
    private Button voltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativiadade_cadastro_reserva);
        spParticipantes = (Spinner) findViewById(R.id.spParticipantes);
        spLivros = (Spinner) findViewById(R.id.spLivros);
        adicionarReserva = (Button) findViewById(R.id.adicionarReserva);
        voltar = (Button) findViewById(R.id.voltar);
        aviso = (TextView) findViewById(R.id.aviso);

        participantes = (ArrayList<Participante>) getIntent().getSerializableExtra("participantes");
        if(participantes.size()==0){
            adicionarReserva.setEnabled(false);
            aviso.setTextColor(Color.RED);
            aviso.setText("Não há nenhum participante presente");
        }
        livros=AtividadePrincipal.livros;
        carregaAdaptes();
        adicionarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int idLivro= spLivros.getSelectedItemPosition();
               int idParticipante =spParticipantes.getSelectedItemPosition();
                Livro l = livros.get(idLivro);
                Participante p = participantes.get(idParticipante);
                l.adicionarNovaReserva(p);
                Toast.makeText(getApplicationContext(),"Reserva adicionada com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void carregaAdaptes(){
        ArrayList<String> itensLivros = new ArrayList();
        for(Livro l : livros){
            itensLivros.add(l.getTitulo());
        }
        ArrayAdapter<String> ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, itensLivros);
        spLivros.setAdapter(ad);

        ArrayList<String> itensParticipantes = new ArrayList();
        for(Participante p: participantes){
            if(p.getDataEntrada()!=null&&p.getDataSaida()==null)
            itensParticipantes.add(p.getNome());
        }
        ArrayAdapter<String> ad2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, itensParticipantes);
        spParticipantes.setAdapter(ad2);
    }
}

package com.example.luisfelipe.trb1_dcc196_luisfelipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AtividadePrincipal extends AppCompatActivity {

    private SimpleDateFormat formatoData;
    private Date data;
    private Calendar calendario;

    private ArrayList<Participante> participantes = new ArrayList();
    public  static ArrayList<Livro> livros = new ArrayList();
    private ListView listaParticipantes;
    private ListView listaLivros;
    private Button cadastroParticipante;
    private Button cadastroLivro;
    private Button cadastroReserva;


    public static final int CADASTRA_PARTICIPANTE = 1;
    public static final int CADASTRA_LIVRO = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_principal);

        formatoData = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");


        listaParticipantes=(ListView) findViewById(R.id.listaParticipantes);
        listaLivros=(ListView) findViewById(R.id.listaLivros);
        cadastroParticipante=(Button) findViewById(R.id.cadastroParticipante);
        cadastroLivro = (Button) findViewById(R.id.cadastrarNovoLivro);
        cadastroReserva = (Button) findViewById(R.id.cadastrarReserva);
        carregaDadosIniciais();
        atualizarListaParticipantes();
        atualizarListaLivros();
        Ouvinte ouvinte = new Ouvinte();
        cadastroParticipante.setOnClickListener(ouvinte);
        cadastroLivro.setOnClickListener(ouvinte);
        cadastroReserva.setOnClickListener(ouvinte);

        listaParticipantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Participante p = participantes.get(i);
                if(p.getDataEntrada()==null){
                    p.setDataEntrada(getData());
                } else if(p.getDataSaida()==null){
                    p.setDataSaida(getData());
                } else{
                    p.setDataSaida(null);
                    p.setDataEntrada(null);
                }
                return  true;
            }
        });
        listaParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent novaAtividade= new Intent(AtividadePrincipal.this,AtividadeConsultaParticipante.class);
                novaAtividade.putExtra("participante",participantes.get(i));
                startActivity(novaAtividade);
            }
        });

        listaLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent novaAtividade= new Intent(AtividadePrincipal.this,AtividadeConsultaLivro.class);
                novaAtividade.putExtra("livro",livros.get(i));
                startActivity(novaAtividade);
            }
        });

    }
    private void atualizarListaParticipantes(){
        ArrayList<String> itens = new ArrayList();
        for(Participante p : participantes){
            itens.add(p.getNome());
        }
        ArrayAdapter<String> ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itens);
        listaParticipantes.setAdapter(ad);

    }
    private void atualizarListaLivros(){
        ArrayList<String> itens = new ArrayList();
        for(Livro l : livros){
            itens.add(l.getTitulo());
        }
        ArrayAdapter<String> ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itens);
        listaLivros.setAdapter(ad);

    }

    private String getData(){
        data = new Date();
        calendario = Calendar.getInstance();
        calendario.setTime(data);
        Date data_atual = calendario.getTime();
        String dataCompleta = formatoData.format(data_atual);
        return  dataCompleta;
    }

    private void carregaDadosIniciais(){
        int numeroParticipantesIniciais=10;
        Participante [] p = new Participante[numeroParticipantesIniciais];
        for(int i=0;i<numeroParticipantesIniciais;i++){
            p[i]=new Participante("nome"+i,"email"+i+"@ufjf.com.br");
        }
        for(Participante pAux : p){
            participantes.add(pAux);
        }
        Random random= new Random();
        int numeroLivrosIniciais=10;
        Livro [] l = new Livro[numeroLivrosIniciais];
        for(int i=0;i<numeroLivrosIniciais;i++) {
            l[i]=new Livro("livro"+i,"editora"+i,""+(1000+random.nextInt(1000)));
        }
        for(Livro lAux: l){
            livros.add(lAux);
        }

    }

    private class Ouvinte implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent novaAtiviadade;
            switch (view.getId()){
                case R.id.cadastroParticipante:
                    novaAtiviadade = new Intent(AtividadePrincipal.this,AtividadeCadastroParticipante.class);
                    startActivityForResult(novaAtiviadade,CADASTRA_PARTICIPANTE);
                break;
                case R.id.cadastrarNovoLivro:
                    novaAtiviadade = new Intent(AtividadePrincipal.this,AtividadeCadastroLivro.class);
                    startActivityForResult(novaAtiviadade,CADASTRA_LIVRO);
                break;
                case R.id.cadastrarReserva:
                    novaAtiviadade = new Intent(AtividadePrincipal.this,AtiviadaCadastroReserva.class);
                    ArrayList<Participante> participantesPresentes= new ArrayList<>();
                    for(Participante p: participantes){
                        if(p.getDataEntrada()!=null&&p.getDataSaida()==null)
                            participantesPresentes.add(p);
                    }
                    novaAtiviadade.putExtra("participantes",participantesPresentes);
                    novaAtiviadade.putExtra("livros",livros);
                    startActivity(novaAtiviadade);
                break;
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CADASTRA_PARTICIPANTE&&resultCode==RESULT_OK&&data!=null){
            ArrayList<Participante> recemAdicionados = (ArrayList<Participante>) data.getSerializableExtra("recemCadastrados");
            for(Participante p: recemAdicionados){
                participantes.add(p);
            }
            atualizarListaParticipantes();
        }

        if(requestCode==CADASTRA_LIVRO&&resultCode==RESULT_OK&&data!=null){
            ArrayList<Livro> recemAdicionados = (ArrayList<Livro>) data.getSerializableExtra("recemCadastrados");
            for(Livro l: recemAdicionados){
                livros.add(l);
            }
            atualizarListaLivros();
        }
    }
}

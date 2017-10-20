package com.example.luisfelipe.trb1_dcc196_luisfelipe;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by luisfelipe on 18/10/17.
 */

public class Livro implements Serializable{
    private String titulo;
    private String editora;
    private String ano;
    private ArrayList<Participante> participantesReserva;

    public Livro(String titulo, String editora, String ano) {
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
        participantesReserva =  new ArrayList();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditora() {
        return editora;
    }

    public String getAno() {
        return ano;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
    public void adicionarNovaReserva(Participante p){
        participantesReserva.add(p);
    }
    public ArrayList<Participante> getParticipantesReserva() {
        return participantesReserva;
    }

    public void setParticipantesReserva(ArrayList<Participante> participantesReserva) {
        this.participantesReserva = participantesReserva;
    }
}
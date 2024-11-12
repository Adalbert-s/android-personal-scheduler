package com.example.appagenda.model;

import java.util.ArrayList;

public class Agenda {
    private final ArrayList<Compromisso> compromissos;

    // Construtor
    public Agenda() {
        compromissos = new ArrayList<>();
    }

    // Método para adicionar um compromisso
    public void adicionarCompromisso(Compromisso compromisso) {
        compromissos.add(compromisso);
    }

    // Método para remover um compromisso
    public void removerCompromisso(Compromisso compromisso) {
        compromissos.remove(compromisso);
    }

    // Método para listar todos os compromissos
    public ArrayList<Compromisso> getCompromissos() {
        return compromissos;
    }

    // Método para obter um compromisso específico por índice
    public Compromisso getCompromisso(int index) {
        if (index >= 0 && index < compromissos.size()) {
            return compromissos.get(index);
        }
        return null;
    }
}

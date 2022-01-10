package com.george.batalhanaval.domain;

public class Jogador {
    private String nome;
    private int pontos;
    private int quantidadeJogadas;
    private boolean human;

    public Jogador(String nome, boolean human) {
        this.nome = nome;
        this.human = human;
        this.pontos = 0;
        this.quantidadeJogadas = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void pontuar() {
        this.pontos++;
    }

    public int getQuantidadeJogadas() {
        return quantidadeJogadas;
    }

    public void setQuantidadeJogadas(int quantidadeJogadas) {
        this.quantidadeJogadas = quantidadeJogadas;
    }

    public boolean isHuman() {
        return human;
    }

    public void setHuman(boolean human) {
        this.human = human;
    }
}


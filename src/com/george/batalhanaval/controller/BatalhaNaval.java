package com.george.batalhanaval.controller;

import com.george.batalhanaval.domain.Jogador;
import com.george.batalhanaval.domain.Tabuleiro;
import com.george.batalhanaval.view.Symbols;

public class BatalhaNaval {
   private Jogador jogador;
    private Jogador cpu;

    private Tabuleiro tabuleiroJogador;
    private Tabuleiro tabuleiroCPU;

    public boolean verificarJogada(Jogador jogador, Tabuleiro tabuleiroOponente, int linha, int coluna) {

        if (
                tabuleiroOponente.getTabuleiro()[linha][coluna] == Symbols._SIM_NAVIO_POSICIONADO
                || tabuleiroOponente.getTabuleiro()[linha][coluna] == Symbols._SIM_TIRO_AGUA_NAVIO_POSICIONADO
                || tabuleiroOponente.getTabuleiro()[linha][coluna] == Symbols._SIM_TIRO_CERTEIRO_NAVIO_POSICIONADO
        ) {jogador.pontuar();
        return true;}
        return false;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Jogador getCpu() {
        return cpu;
    }

    public void setCpu(Jogador cpu) {
        this.cpu = cpu;
    }

    public Tabuleiro getTabuleiroJogador() {
        return tabuleiroJogador;
    }

    public void setTabuleiroJogador(Tabuleiro tabuleiroJogador) {
        this.tabuleiroJogador = tabuleiroJogador;
    }

    public Tabuleiro getTabuleiroCPU() {
        return tabuleiroCPU;
    }

    public void setTabuleiroCPU(Tabuleiro tabuleiroCPU) {
        this.tabuleiroCPU = tabuleiroCPU;
    }
}


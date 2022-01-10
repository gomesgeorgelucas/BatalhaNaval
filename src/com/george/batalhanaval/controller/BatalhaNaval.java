package com.george.batalhanaval.controller;

import com.george.batalhanaval.domain.Jogador;
import com.george.batalhanaval.domain.Tabuleiro;
import com.george.batalhanaval.enums.SimbolosEnum;

public class BatalhaNaval {
   private Jogador jogador;
    private Jogador cpu;

    private Tabuleiro tabuleiroJogador;
    private Tabuleiro tabuleiroCPU;

    public void validarJogada(Jogador jogador, Tabuleiro tabuleiroJogador, Tabuleiro tabuleiroOponente, int linha, int coluna) {

        if (tabuleiroOponente.getTabuleiro()[linha][coluna] == SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo() ||
                tabuleiroOponente.getTabuleiro()[linha][coluna] == SimbolosEnum._SIM_TIRO_AGUA_NAVIO_POSICIONADO.getSimbolo()) {
            tabuleiroOponente.getTabuleiro()[linha][coluna] = SimbolosEnum._SIM_AGUA.getSimbolo();
            if (tabuleiroJogador.getTabuleiro()[linha][coluna] == SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo()) {
                tabuleiroJogador.getTabuleiro()[linha][coluna] = SimbolosEnum._SIM_TIRO_CERTEIRO_NAVIO_POSICIONADO.getSimbolo();
            } else if (tabuleiroJogador.getTabuleiro()[linha][coluna] == SimbolosEnum._SIM_AGUA.getSimbolo()) {
                tabuleiroJogador.getTabuleiro()[linha][coluna] = SimbolosEnum._SIM_TIRO_CERTEIRO.getSimbolo();
            }
            jogador.pontuar();
        } else {
            if (tabuleiroJogador.getTabuleiro()[linha][coluna] == SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo()) {
                tabuleiroJogador.getTabuleiro()[linha][coluna] = SimbolosEnum._SIM_TIRO_AGUA_NAVIO_POSICIONADO.getSimbolo();
            } else if (tabuleiroJogador.getTabuleiro()[linha][coluna] == SimbolosEnum._SIM_AGUA.getSimbolo()) {
                tabuleiroJogador.getTabuleiro()[linha][coluna] = SimbolosEnum._SIM_TIRO_NA_AGUA.getSimbolo();
            }
        }
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


package com.george.batalhanaval.controller;

import com.george.batalhanaval.domain.Jogador;
import com.george.batalhanaval.domain.Tabuleiro;
import com.george.batalhanaval.enums.SimbolosEnum;

import java.util.Scanner;

public class BatalhaNavalController {
    private Scanner scanner;
    private boolean debug;

    private Jogador jogador;
    private Jogador cpu;

    private Tabuleiro tabuleiroJogador;
    private Tabuleiro tabuleiroCPU;

    public BatalhaNavalController(boolean debug) {
        this.debug = debug;
        this.scanner = new Scanner(System.in);
        this.jogador = new Jogador("JOGADOR", true);
        this.cpu = new Jogador("CPU", false);

        this.tabuleiroJogador =  new Tabuleiro(jogador, scanner, debug);
        this.tabuleiroCPU = new Tabuleiro(cpu, scanner, debug);
    }

    public void executar() {
        boolean turnoCpu = false;
        boolean jogoAtivo = true;

        while (jogoAtivo) {
            tabuleiroJogador.desenhar(this.jogador);
            if (this.debug) {
                tabuleiroCPU.desenhar(this.cpu);
            }

            jogar(turnoCpu ? this. cpu : this.jogador);
            System.out.println("Pontos JOGADOR: " + this.jogador.getPontos());
            System.out.println("Pontos CPU: " + this.cpu.getPontos());

            if (this.jogador.getPontos() == 10 || this.jogador.getPontos() == 10 ) {
                jogoAtivo = !jogoAtivo;
            }

            turnoCpu = !turnoCpu;
        }

        finalizar();
    }

    private void jogar(Jogador jogador) {

        int linha = 0;
        int coluna = 0;
        String letra = "";

        if (!jogador.isHuman()) {
            linha = (int) Math.floor(Math.random() * 10);
            coluna = (int) Math.floor(Math.random() * 10);

            while (tabuleiroCPU.getTabuleiro()[linha][coluna] != SimbolosEnum._SIM_AGUA.getSimbolo()
                    && tabuleiroCPU.getTabuleiro()[linha][coluna] != SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo()) {
                linha = (int) Math.floor(Math.random() * 10);
                coluna = (int) Math.floor(Math.random() * 10);
            }

            System.out.println("Coordenadas ataque CPU: " + tabuleiroCPU.get_INDICES_LINHAS()[linha] + "-" + coluna);

            validarJogada(jogador, tabuleiroCPU, tabuleiroJogador, linha, coluna);

        } else {
            System.out.println("Coordenadas do ataque: ");
            System.out.print("Escolha a linha (A-" + tabuleiroJogador.get_INDICES_LINHAS()[tabuleiroJogador.get_INDICES_LINHAS().length - 1] + "): ");
            letra = scanner.next().toUpperCase();
            for (int k = 0; k < tabuleiroJogador.get_INDICES_LINHAS().length; k++) {
                if (tabuleiroJogador.get_INDICES_LINHAS()[k] == letra.charAt(0)) {
                    linha = k;
                    break;
                }
            }
            System.out.print("Escolha a coluna (0-" + (tabuleiroJogador.get_COLUNAS() - 1) + "): ");
            coluna = scanner.nextInt();

            //Verifica Jogada
            validarJogada(jogador, tabuleiroJogador, tabuleiroCPU, linha, coluna);
        }
    }

    private void validarJogada(Jogador jogador, Tabuleiro tabuleiroJogador, Tabuleiro tabuleiroOponente, int linha, int coluna) {

        switch (tabuleiroOponente.getTabuleiro()[linha][coluna]) {
            default:
                break;
        }

        if (tabuleiroOponente.getTabuleiro()[linha][coluna] == SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo()) {
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

    private void finalizar() {
        System.out.println("O vencedor foi: " + ((jogador.getPontos() == 10) ? "JOGADOR" : "CPU"));
    }
}


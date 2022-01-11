package com.george.batalhanaval.view;

import com.george.batalhanaval.domain.Jogador;
import com.george.batalhanaval.domain.Tabuleiro;


import java.util.Scanner;

public class TabuleiroView {

    Tabuleiro tabuleiro;
    Jogador jogador;

    private final char[] _INDICES_LINHAS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};



    public TabuleiroView(Jogador jogador, Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.jogador = jogador;
        limpar();
        preencher();
    }

    public void desenhar(Jogador jogador) {
        System.out.printf("---------------------------------------------%n");
        System.out.printf("                      %s                     %n", jogador.getNome());
        System.out.printf("---------------------------------------------%n");
        System.out.printf("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |%n");
        System.out.printf("---------------------------------------------%n");

        for (int i = 0; i < tabuleiro.get_LINHAS(); i++) {
            System.out.printf("| %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s |%n",
                    _INDICES_LINHAS[i],
                    tabuleiro.getTabuleiro()[i][0],
                    tabuleiro.getTabuleiro()[i][1],
                    tabuleiro.getTabuleiro()[i][2],
                    tabuleiro.getTabuleiro()[i][3],
                    tabuleiro.getTabuleiro()[i][4],
                    tabuleiro.getTabuleiro()[i][5],
                    tabuleiro.getTabuleiro()[i][6],
                    tabuleiro.getTabuleiro()[i][7],
                    tabuleiro.getTabuleiro()[i][8],
                    tabuleiro.getTabuleiro()[i][9]);
            System.out.printf("---------------------------------------------%n");
        }
    }

    public void limpar() {
        for (int i = 0; i < tabuleiro.get_LINHAS(); i++) {
            for (int j = 0; j < tabuleiro.get_COLUNAS(); j++) {
                tabuleiro.getTabuleiro()[i][j] = Symbols._SIM_AGUA;
            }
        }
    }

    public void preencher() {
        int linha = 0;
        int coluna;
        String letra;
        boolean debug = true;
        int _QTD_NAVIOS = 10;

        if (!jogador.isHuman()) {
            for (int i = 0; i < tabuleiro.get_COLUNAS(); i++) {
                linha = (int) Math.floor(Math.random() * 10);
                coluna = (int) Math.floor(Math.random() * 10);
                while (tabuleiro.getTabuleiro()[linha][coluna] == Symbols._SIM_NAVIO_POSICIONADO) {
                    linha = (int) Math.floor(Math.random() * 10);
                    coluna = (int) Math.floor(Math.random() * 10);
                }
                tabuleiro.getTabuleiro()[linha][coluna] = Symbols._SIM_NAVIO_POSICIONADO;
            }
        } else {
            if (debug) {
                tabuleiro.getTabuleiro()[0][0] = Symbols._SIM_NAVIO_POSICIONADO;
                tabuleiro.getTabuleiro()[1][1] = Symbols._SIM_NAVIO_POSICIONADO;
                tabuleiro.getTabuleiro()[2][2] = Symbols._SIM_NAVIO_POSICIONADO;
                tabuleiro.getTabuleiro()[3][3] = Symbols._SIM_NAVIO_POSICIONADO;
                tabuleiro.getTabuleiro()[4][4] = Symbols._SIM_NAVIO_POSICIONADO;
                tabuleiro.getTabuleiro()[5][5] = Symbols._SIM_NAVIO_POSICIONADO;
                tabuleiro.getTabuleiro()[6][6] = Symbols._SIM_NAVIO_POSICIONADO;
                tabuleiro.getTabuleiro()[7][7] = Symbols._SIM_NAVIO_POSICIONADO;
                tabuleiro.getTabuleiro()[8][8] = Symbols._SIM_NAVIO_POSICIONADO;
                tabuleiro.getTabuleiro()[9][9] = Symbols._SIM_NAVIO_POSICIONADO;

            } else {
                desenhar(jogador);
                System.out.println("Posicione os seus " + _QTD_NAVIOS + " navios! (N)");
                for (int i = 0; i < _QTD_NAVIOS; i++) {
                    System.out.println("Navio no. " + (i + 1));
                    System.out.print("Escolha a linha (A-" + _INDICES_LINHAS[_INDICES_LINHAS.length - 1] + "): ");
                    letra = new Scanner(System.in).next().toUpperCase();
                    for (int k = 0; k < _INDICES_LINHAS.length; k++) {
                        if (_INDICES_LINHAS[k] == letra.charAt(0)) {
                            linha = k;
                            break;
                        }
                    }
                    System.out.print("Escolha a coluna (0-" + (tabuleiro.get_COLUNAS() - 1) + "): ");
                    coluna = new Scanner(System.in).nextInt();


                    tabuleiro.getTabuleiro()[linha][coluna] = Symbols._SIM_NAVIO_POSICIONADO;
                    desenhar(jogador);
                }
            }
        }

    }

    public void atualizaTabuleiro(Tabuleiro tabuleiroJogador, Tabuleiro tabuleiroOponente, int linha, int coluna, boolean hit) {
        if (hit) {
            if (tabuleiroOponente.getTabuleiro()[linha][coluna] == Symbols._SIM_NAVIO_POSICIONADO) {
                tabuleiroOponente.getTabuleiro()[linha][coluna] = Symbols._SIM_AGUA;
            } else if (tabuleiroOponente.getTabuleiro()[linha][coluna] == Symbols._SIM_TIRO_AGUA_NAVIO_POSICIONADO) {
                tabuleiroOponente.getTabuleiro()[linha][coluna] = Symbols._SIM_REMOVIDO_TIRO_AGUA_NAVIO_POSICIONADO;
            } else if (tabuleiroOponente.getTabuleiro()[linha][coluna] == Symbols._SIM_TIRO_CERTEIRO_NAVIO_POSICIONADO) {
                tabuleiroOponente.getTabuleiro()[linha][coluna] = Symbols._SIM_REMOVIDO_SIM_TIRO_CERTEIRO_NAVIO_POSICIONADO;
            }

            if (tabuleiroJogador.getTabuleiro()[linha][coluna] == Symbols._SIM_NAVIO_POSICIONADO) {
                tabuleiroJogador.getTabuleiro()[linha][coluna] = Symbols._SIM_TIRO_CERTEIRO_NAVIO_POSICIONADO;
            } else if (tabuleiroJogador.getTabuleiro()[linha][coluna] == Symbols._SIM_AGUA) {
                tabuleiroJogador.getTabuleiro()[linha][coluna] = Symbols._SIM_TIRO_CERTEIRO;
            }
        } else {
            if (tabuleiroJogador.getTabuleiro()[linha][coluna] == Symbols._SIM_NAVIO_POSICIONADO) {
                tabuleiroJogador.getTabuleiro()[linha][coluna] = Symbols._SIM_TIRO_AGUA_NAVIO_POSICIONADO;
            } else if (tabuleiroJogador.getTabuleiro()[linha][coluna] == Symbols._SIM_AGUA) {
                tabuleiroJogador.getTabuleiro()[linha][coluna] = Symbols._SIM_TIRO_NA_AGUA;
            }
        }
    }

    public char[] get_INDICES_LINHAS() {
        return _INDICES_LINHAS;
    }
}
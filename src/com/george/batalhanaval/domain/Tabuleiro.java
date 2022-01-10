package com.george.batalhanaval.domain;

import com.george.batalhanaval.enums.SimbolosEnum;

import java.util.Scanner;

public class Tabuleiro {

    private boolean debug = false;

    private final int _LINHAS = 10;
    private final int _COLUNAS = 10;
    private final char _INDICES_LINHAS[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private final char _INDICES_COLUNAS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final int _QTD_NAVIOS = 10;

    private char[][] tabuleiro;

    public Tabuleiro(Jogador jogador) {
        this.tabuleiro = new char[_LINHAS][_COLUNAS];
        limpar();
        preencher(jogador);
    }

    public void desenhar(Jogador jogador) {
        System.out.printf("---------------------------------------------%n");
        System.out.printf("                      %s                     %n", jogador.getNome());
        System.out.printf("---------------------------------------------%n");
        System.out.printf("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |%n");
        System.out.printf("---------------------------------------------%n");

        for (int i = 0; i < _LINHAS; i++) {
            System.out.printf("| %s | %s | %s | %s | %s | %s | %s | %s | %s | %s | %s |%n",
                    get_INDICES_LINHAS()[i],
                    getTabuleiro()[i][0],
                    getTabuleiro()[i][1],
                    getTabuleiro()[i][2],
                    getTabuleiro()[i][3],
                    getTabuleiro()[i][4],
                    getTabuleiro()[i][5],
                    getTabuleiro()[i][6],
                    getTabuleiro()[i][7],
                    getTabuleiro()[i][8],
                    getTabuleiro()[i][9]);
            System.out.printf("---------------------------------------------%n");
        }
    }

    public void preencher(Jogador jogador) {
        int linha = 0;
        int coluna = 0;
        String letra = "";

        if (!jogador.isHuman()) {
            for (int i = 0; i < _COLUNAS; i++) {
                linha = (int) Math.floor(Math.random() * 10);
                coluna = (int) Math.floor(Math.random() * 10);
                while (getTabuleiro()[linha][coluna] == SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo()) {
                    linha = (int) Math.floor(Math.random() * 10);
                    coluna = (int) Math.floor(Math.random() * 10);
                }
                getTabuleiro()[linha][coluna] = SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo();
            }
        } else {
            if (debug) {
                getTabuleiro()[0][0] = SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo();
                getTabuleiro()[1][1] = SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo();
                getTabuleiro()[2][2] = SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo();
                getTabuleiro()[3][3] = SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo();
                getTabuleiro()[4][4] = SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo();
                getTabuleiro()[5][5] = SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo();
                getTabuleiro()[6][6] = SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo();
                getTabuleiro()[7][7] = SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo();
                getTabuleiro()[8][8] = SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo();
                getTabuleiro()[9][9] = SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo();

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
                    System.out.print("Escolha a coluna (0-" + (_COLUNAS - 1) + "): ");
                    coluna = new Scanner(System.in).nextInt();


                    getTabuleiro()[linha][coluna] = SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo();
                    desenhar(jogador);
                }
            }
        }

    }

    public void limpar() {
        for (int i = 0; i < _LINHAS; i++) {
            for (int j = 0; j < _COLUNAS; j++) {
                this.tabuleiro[i][j] = SimbolosEnum._SIM_AGUA.getSimbolo();
            }
        }
    }

    public char[][] getTabuleiro() {
        return tabuleiro;
    }

    public char[] get_INDICES_LINHAS() {
        return this._INDICES_LINHAS;
    }

    public int get_COLUNAS() {
        return this._COLUNAS;
    }

}


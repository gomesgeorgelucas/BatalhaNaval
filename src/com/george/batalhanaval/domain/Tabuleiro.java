package com.george.batalhanaval.domain;

public class Tabuleiro {

    private final int _LINHAS = 10;
    private final int _COLUNAS = 10;

    private char[][] tabuleiro;

    public Tabuleiro() {
        this.tabuleiro = new char[_LINHAS][_COLUNAS];
    }

    public int get_LINHAS() {
        return _LINHAS;
    }

    public int get_COLUNAS() {
        return _COLUNAS;
    }

    public char[][] getTabuleiro() {
        return tabuleiro;
    }
}
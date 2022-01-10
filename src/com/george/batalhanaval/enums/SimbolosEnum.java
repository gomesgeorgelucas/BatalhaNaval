package com.george.batalhanaval.enums;

public enum SimbolosEnum {
    _SIM_NAVIO_POSICIONADO('N'),
    _SIM_TIRO_CERTEIRO('*'),
    _SIM_TIRO_NA_AGUA('-'),
    _SIM_TIRO_CERTEIRO_NAVIO_POSICIONADO('X'),
    _SIM_TIRO_AGUA_NAVIO_POSICIONADO('n'),
    _SIM_AGUA(' ');
    private char simbolo;

    SimbolosEnum(char simbolo) {
        this.simbolo = simbolo;
    }

    public char getSimbolo() {
        return simbolo;
    }
}

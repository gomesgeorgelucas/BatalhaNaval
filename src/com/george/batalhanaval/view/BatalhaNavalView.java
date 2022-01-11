package com.george.batalhanaval.view;

import com.george.batalhanaval.controller.BatalhaNaval;
import com.george.batalhanaval.domain.Jogador;
import com.george.batalhanaval.domain.Tabuleiro;

import java.util.Scanner;

public class BatalhaNavalView {

    BatalhaNaval batalhaNaval = new BatalhaNaval();
    TabuleiroView tabuleiroViewJogador;
    TabuleiroView tabuleiroViewCPU;

    public BatalhaNavalView() {
        String name = "";
        do {
            System.out.println("Qual o seu nome?");
            try {
                name = new Scanner(System.in).nextLine();
                if (name.isEmpty() || name.isBlank() || name == null) {
                    System.out.println("Entrada inválida!");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida!");
            }
        } while (name.isEmpty() || name.isBlank() || name == null);

        batalhaNaval.setJogador(new Jogador(name, true));
        batalhaNaval.setCpu(new Jogador("CPU", false));
        batalhaNaval.setTabuleiroJogador(new Tabuleiro());
        batalhaNaval.setTabuleiroCPU(new Tabuleiro());
        tabuleiroViewJogador = new TabuleiroView(batalhaNaval.getJogador(), batalhaNaval.getTabuleiroJogador());
        tabuleiroViewCPU = new TabuleiroView(batalhaNaval.getCpu(), batalhaNaval.getTabuleiroCPU());
    }

    private void jogar(Jogador jogador) {

        int linha = -1;
        int coluna = -1;
        String letra = "";

        if (!jogador.isHuman()) {
            do {
                linha = (int) Math.floor(Math.random() * 10);
                coluna = (int) Math.floor(Math.random() * 10);
            } while (batalhaNaval.getTabuleiroCPU().getTabuleiro()[linha][coluna] == Symbols._SIM_TIRO_NA_AGUA
                    || batalhaNaval.getTabuleiroCPU().getTabuleiro()[linha][coluna] == Symbols._SIM_TIRO_CERTEIRO
                    || batalhaNaval.getTabuleiroCPU().getTabuleiro()[linha][coluna] == Symbols._SIM_TIRO_CERTEIRO_NAVIO_POSICIONADO
                    || batalhaNaval.getTabuleiroCPU().getTabuleiro()[linha][coluna] == Symbols._SIM_TIRO_AGUA_NAVIO_POSICIONADO
                    || batalhaNaval.getTabuleiroCPU().getTabuleiro()[linha][coluna] == Symbols._SIM_REMOVIDO_TIRO_AGUA_NAVIO_POSICIONADO
                    || batalhaNaval.getTabuleiroCPU().getTabuleiro()[linha][coluna] == Symbols._SIM_REMOVIDO_SIM_TIRO_CERTEIRO_NAVIO_POSICIONADO
            );

            System.out.println("Coordenadas ataque CPU: " + tabuleiroViewCPU.get_INDICES_LINHAS()[linha] + "-" + coluna);


            tabuleiroViewCPU.atualizaTabuleiro(
                    batalhaNaval.getTabuleiroCPU(),
                    batalhaNaval.getTabuleiroJogador(),
                    linha,
                    coluna,
                    batalhaNaval.verificarJogada(jogador, batalhaNaval.getTabuleiroJogador(), linha, coluna));


        } else {
            System.out.println("Coordenadas do ataque: ");

            do {
                try {
                    System.out.print("Escolha a linha (A-" + tabuleiroViewJogador.get_INDICES_LINHAS()[tabuleiroViewJogador.get_INDICES_LINHAS().length - 1] + "): ");
                    letra = new Scanner(System.in).next().toUpperCase();

                    if (
                            letra == null
                                    || letra.toCharArray().length > 1
                                    || letra.isBlank()
                                    || letra.isEmpty()
                                    || !(new String(TabuleiroView._INDICES_LINHAS).contains(letra))) {
                        System.out.println("Entrada inválida!");
                    }

                } catch (Exception e) {
                    System.out.println("Entrada invalida!");
                }
            } while (
                    letra == null
                            || letra.toCharArray().length > 1
                            || letra.isBlank()
                            || letra.isEmpty()
                            || !(new String(TabuleiroView._INDICES_LINHAS).contains(letra)));

            for (int k = 0; k < tabuleiroViewJogador.get_INDICES_LINHAS().length; k++) {
                if (tabuleiroViewJogador.get_INDICES_LINHAS()[k] == letra.charAt(0)) {
                    linha = k;
                    break;
                }
            }

            do {
                try {
                    System.out.print("Escolha a coluna (0-" + (batalhaNaval.getTabuleiroJogador().get_COLUNAS() - 1) + "): ");
                    coluna = new Scanner(System.in).nextInt();

                    if (coluna < 0 || coluna > 9) {
                        System.out.println("Entrada inválida!");
                    }
                } catch (Exception e) {
                    System.out.println("Entrada invalida!");
                }

            } while (coluna < 0 || coluna > 9);

            tabuleiroViewCPU.atualizaTabuleiro(
                    batalhaNaval.getTabuleiroJogador(),
                    batalhaNaval.getTabuleiroCPU(),
                    linha,
                    coluna,
                    batalhaNaval.verificarJogada(jogador, batalhaNaval.getTabuleiroCPU(), linha, coluna));
        }
    }

    public void show() {
        boolean debug = false;
        boolean turnoCpu = false;
        boolean jogoAtivo = true;

        while (jogoAtivo) {
            tabuleiroViewJogador.desenhar(batalhaNaval.getJogador());
            if (debug) {
                tabuleiroViewCPU.desenhar(batalhaNaval.getCpu());
            }

            jogar(turnoCpu ? batalhaNaval.getCpu() : batalhaNaval.getJogador());
            System.out.println("Pontos JOGADOR: " + batalhaNaval.getJogador().getPontos());
            System.out.println("Pontos CPU: " + batalhaNaval.getCpu().getPontos());

            if (batalhaNaval.getCpu().getPontos() == 10 || batalhaNaval.getJogador().getPontos() == 10 ) {
                jogoAtivo = !jogoAtivo;
            }

            turnoCpu = !turnoCpu;
        }
        finalizar();
    }


    private void finalizar() {
        System.out.println("O vencedor foi: " + ((batalhaNaval.getJogador().getPontos() == 10) ? "JOGADOR" : "CPU"));
        tabuleiroViewJogador.desenhar(batalhaNaval.getJogador());
        tabuleiroViewCPU.desenhar(batalhaNaval.getCpu());
    }

}

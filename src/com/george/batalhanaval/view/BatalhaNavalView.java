package com.george.batalhanaval.view;

import com.george.batalhanaval.controller.BatalhaNaval;
import com.george.batalhanaval.domain.Jogador;
import com.george.batalhanaval.domain.Tabuleiro;
import com.george.batalhanaval.enums.SimbolosEnum;

import java.util.Scanner;

public class BatalhaNavalView {
    private boolean debug = true;

    BatalhaNaval batalhaNaval = new BatalhaNaval();
    TabuleiroView tabuleiroViewJogador;
    TabuleiroView tabuleiroViewCPU;

    public BatalhaNavalView() {
        System.out.println("Qual o seu nome?");
        String name = new Scanner(System.in).nextLine();
        batalhaNaval.setJogador(new Jogador(name, true));
        batalhaNaval.setCpu(new Jogador("CPU", false));
        batalhaNaval.setTabuleiroJogador(new Tabuleiro());
        batalhaNaval.setTabuleiroCPU(new Tabuleiro());
        tabuleiroViewJogador = new TabuleiroView(batalhaNaval.getJogador(), batalhaNaval.getTabuleiroJogador());
        tabuleiroViewCPU = new TabuleiroView(batalhaNaval.getCpu(), batalhaNaval.getTabuleiroCPU());
    }

    private void jogar(Jogador jogador) {

        int linha = 0;
        int coluna = 0;
        String letra = "";

        if (!jogador.isHuman()) {
            linha = (int) Math.floor(Math.random() * 10);
            coluna = (int) Math.floor(Math.random() * 10);

            while (batalhaNaval.getTabuleiroCPU().getTabuleiro()[linha][coluna] != SimbolosEnum._SIM_AGUA.getSimbolo()
                    && batalhaNaval.getTabuleiroCPU().getTabuleiro()[linha][coluna] != SimbolosEnum._SIM_NAVIO_POSICIONADO.getSimbolo()) {
                linha = (int) Math.floor(Math.random() * 10);
                coluna = (int) Math.floor(Math.random() * 10);
            }

            System.out.println("Coordenadas ataque CPU: " + tabuleiroViewCPU.get_INDICES_LINHAS()[linha] + "-" + coluna);


            tabuleiroViewCPU.atualizaTabuleiro(
                    jogador,
                    batalhaNaval.getTabuleiroCPU(),
                    batalhaNaval.getTabuleiroJogador(),
                    linha,
                    coluna,
                    batalhaNaval.verificarJogada(jogador, batalhaNaval.getTabuleiroCPU(), batalhaNaval.getTabuleiroJogador(), linha, coluna));


        } else {
            System.out.println("Coordenadas do ataque: ");
            System.out.print("Escolha a linha (A-" + tabuleiroViewJogador.get_INDICES_LINHAS()[tabuleiroViewJogador.get_INDICES_LINHAS().length - 1] + "): ");
            letra = new Scanner(System.in).next().toUpperCase();
            for (int k = 0; k < tabuleiroViewJogador.get_INDICES_LINHAS().length; k++) {
                if (tabuleiroViewJogador.get_INDICES_LINHAS()[k] == letra.charAt(0)) {
                    linha = k;
                    break;
                }
            }
            System.out.print("Escolha a coluna (0-" + (batalhaNaval.getTabuleiroJogador().get_COLUNAS() - 1) + "): ");
            coluna = new Scanner(System.in).nextInt();

            tabuleiroViewCPU.atualizaTabuleiro(
                    jogador,
                    batalhaNaval.getTabuleiroJogador(),
                    batalhaNaval.getTabuleiroCPU(),
                    linha,
                    coluna,
                    batalhaNaval.verificarJogada(jogador, batalhaNaval.getTabuleiroJogador(), batalhaNaval.getTabuleiroCPU(), linha, coluna));
        }
    }

    public void show() {
        boolean turnoCpu = false;
        boolean jogoAtivo = true;

        while (jogoAtivo) {
            tabuleiroViewJogador.desenhar(batalhaNaval.getJogador());
            if (this.debug) {
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

package com.george.batalhanaval.view;

import javax.xml.catalog.Catalog;
import java.util.Scanner;

public class MenuView {
    boolean run = true;

    private void showHeader() {
        ScreenUtil.printTextLine("", 100, true, '=');
        ScreenUtil.printTextLine("######     #    #######    #    #       #     #    #       #     #    #    #     #    #    #      ", 100, true, ' ');
        ScreenUtil.printTextLine("#     #   # #      #      # #   #       #     #   # #      ##    #   # #   #     #   # #   #      ", 100, true, ' ');
        ScreenUtil.printTextLine("#     #  #   #     #     #   #  #       #     #  #   #     # #   #  #   #  #     #  #   #  #      ", 100, true, ' ');
        ScreenUtil.printTextLine("######  #     #    #    #     # #       ####### #     #    #  #  # #     # #     # #     # #      ", 100, true, ' ');
        ScreenUtil.printTextLine("#     # #######    #    ####### #       #     # #######    #   # # #######  #   #  ####### #      ", 100, true, ' ');
        ScreenUtil.printTextLine("#     # #     #    #    #     # #       #     # #     #    #    ## #     #   # #   #     # #      ", 100, true, ' ');
        ScreenUtil.printTextLine("######  #     #    #    #     # ####### #     # #     #    #     # #     #    #    #     # #######", 100, true, ' ');
        ScreenUtil.printTextLine("", 100, true, '=');
    }

    private void loadView() {
        showHeader();
        ScreenUtil.printTextLine("", 100, true, '=');
        ScreenUtil.printTextLine("J: Jogar", 100, true);
        ScreenUtil.printTextLine("X: Sair", 100, true);
        ScreenUtil.printTextLine("", 100, true, '=');
        ScreenUtil.printTextLine("#: ", 100);
    }

    public void show() {
        loadView();
        String option;
        do {
            option = new Scanner(System.in).next();
            switch (option.toUpperCase()) {
                case "J":
                    new BatalhaNavalView().show();
                    break;
                case "X":
                    stop();
                    break;
                default:
                    ScreenUtil.printTextLine("Opção inválida", 100);
                    ScreenUtil.printTextLine("#: ", 100);
                    break;
            }
            loadView();
        } while (run);
    }

    private void stop() {
        this.run = false;
    }
}

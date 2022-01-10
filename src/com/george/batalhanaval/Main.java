package com.george.batalhanaval;

import com.george.batalhanaval.controller.BatalhaNavalController;

public class Main {

    public static final boolean _DEBUG = false;

    public static void main(String[] args) {
        BatalhaNavalController batalhaNaval = new BatalhaNavalController(_DEBUG);
        batalhaNaval.executar();
    }
}

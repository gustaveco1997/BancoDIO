package services;

import java.util.Random;

public class GeraNumeroConta {
    public static String gerar() {
        int numeroConta = new Random()
                .ints(1, 1000, 9000)
                .findFirst()
                .orElse(0);
        return String.valueOf(numeroConta);
    }
}

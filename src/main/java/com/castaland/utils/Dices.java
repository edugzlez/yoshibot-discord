package com.castaland.utils;

public class Dices {

    public static int throwDice() {
        return Primes.randInt(1, 6);
    }

    public static int[] throwDices(int num) {
        int[] result = new int[num];
        for(int i = 0; i<num; i++) {
            result[i] = throwDice();
        }
        return result;
    }
}
package com.castaland.utils;

public class Dices {

    public static short throwDice() {
        return (short) Primes.randInt(1, 6);
    }

    public static short[] throwDices(int num) {
        short[] result = new short[num];
        for(int i = 0; i<num; i++) {
            result[i] = throwDice();
        }
        return result;
    }
}
package com.castaland.utils;

import java.math.BigInteger;
import java.util.Random;

public class Primes {

    public static BigInteger randPrime(int bytes) {
        return BigInteger.probablePrime(bytes, new Random());
    }

    public static BigInteger randPrime() {
        return BigInteger.probablePrime(randInt(2, 32), new Random());
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}

package com.castaland.utils;

import java.util.HashMap;
import java.util.Map;

public class Stats {
    public static double getMean(double[] numberList) {
        double total = 0;
        for (double d: numberList) {
            total += d;
        }
        return total / (numberList.length);
    }

    public static double getMode(double[] numberList) {
        HashMap<Double,Double> freqs = new HashMap<Double,Double>();
        for (double d: numberList) {
            Double freq = freqs.get(d);
            freqs.put(d, (freq == null ? 1 : freq + 1));
        }
        double mode = 0;
        double maxFreq = 0;
        for (Map.Entry<Double,Double> entry : freqs.entrySet()) {
            double freq = entry.getValue();
            if (freq > maxFreq) {
                maxFreq = freq;
                mode = entry.getKey();
            }
        }
        return mode;
    }


}


package com.vaani.dsa.algo.numerical;

import com.google.common.math.IntMath;

import static com.google.common.math.IntMath.gcd;

public class LcmGcdOfArray {
    private static int lcm(int a, int b) {
        return a * (b / IntMath.gcd(a, b));
    }
    public static int lcm(int[] input) {
        int result = input[0];
        for (int i = 1; i < input.length; i++) {
            result = lcm(result, input[i]);
        }
        return result;
    }

    public static int gcd(int[] input) {
        int result = input[0];
        for (int i = 1; i < input.length; i++) {
            result = IntMath.gcd(result, input[i]);
        }
        return result;
    }
}

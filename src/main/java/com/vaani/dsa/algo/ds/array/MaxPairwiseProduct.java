package com.vaani.dsa.algo.ds.array;

import java.math.BigInteger;
import java.util.*;
import java.io.*;

import static java.lang.Integer.max;

public class MaxPairwiseProduct {
    static long getMaxPairwiseProductNaive(int[] numbers) {
        int max_product = 0;
        int n = numbers.length;

        for (int first = 0; first < n; ++first) {
            for (int second = first + 1; second < n; ++second) {
                max_product = max(max_product,
                        numbers[first] * numbers[second]);
            }
        }

        return max_product;
    }

    static BigInteger getMaxPairwiseProduct(int[] numbers) {
        int max = -1;
        int secondMax = -1;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > max) {
                secondMax = max;
                max = numbers[i];
            }else if (numbers[i] > secondMax){
                secondMax = numbers[i];
            }
        }

        return BigInteger.valueOf(max).multiply(BigInteger.valueOf(secondMax));
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                        InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}

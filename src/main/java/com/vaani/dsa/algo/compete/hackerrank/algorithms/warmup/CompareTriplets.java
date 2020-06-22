package com.vaani.dsa.algo.compete.hackerrank.algorithms.warmup;

import java.util.Arrays;
import java.util.List;

/**
 * Ref: https://www.hackerrank.com/challenges/compare-the-triplets/problem
 */
public class CompareTriplets {
    public static void main(String[] args) {

        // Organize the results
        List<Integer> aliceRates = Arrays.asList(17, 28, 30);
        List<Integer> bobRates = Arrays.asList(99, 16, 8);

        // Compare the values for Alice and Bob
        List<Integer> results = compareTriplets(aliceRates, bobRates);

        // Print out the results
        System.out.println(String.format("%d %d", results.get(0), results.get(1)));
    }

    private static List<Integer> compareTriplets(List<Integer> aliceRates, List<Integer> bobRates) {
        Integer[] resultArr = {0, 0};
        for (int index = 0; index < aliceRates.size(); index++) {
            Integer aliceRate = aliceRates.get(index);
            Integer bobRate = bobRates.get(index);

            if (aliceRate > bobRate) {
                resultArr[0]++;
            } else if (aliceRate < bobRate) {
                resultArr[1]++;
            }
        }
        return Arrays.asList(resultArr);
    }
}

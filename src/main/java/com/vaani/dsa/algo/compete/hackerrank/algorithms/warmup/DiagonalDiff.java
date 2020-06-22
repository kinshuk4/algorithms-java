package com.vaani.dsa.algo.compete.hackerrank.algorithms.warmup;

import org.junit.Assert;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.stream.Stream;

//https://www.hackerrank.com/challenges/diagonal-difference/problem
import static java.util.stream.Collectors.toList;

public class DiagonalDiff {
    public static int diagonalDifference(List<List<Integer>> matrix) {
        // Write your code here
        int diagonalSum = 0;
        int oppositeDiagonalSum = 0;
        int n = matrix.size();

        for (int i = 0; i < n; i++) {
            diagonalSum += matrix.get(i).get(i);
            oppositeDiagonalSum += matrix.get(i).get(n - i - 1);
        }

        return Math.abs(diagonalSum - oppositeDiagonalSum);
    }

    private static List<List<Integer>> readMatrix() throws IOException {
        List<List<Integer>> arr = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return arr;
    }

    public static void main(String[] args) {

        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(11, 2, 4));
        matrix.add(Arrays.asList(4, 5, 6));
        matrix.add(Arrays.asList(10, 8, -12));

        Assert.assertEquals(15, diagonalDifference(matrix));
    }
}

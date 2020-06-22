package com.vaani.dsa.algo.compete.hackerrank.algorithms.warmup;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**

 * Ref: Staircase exercise.
 */
public class Staircase {
    // Complete the staircase function below.
    static void staircase(int n) {
        for(int i = 0; i < n; i++){
            String hashes = Stream.generate(() -> "#").limit(i+1).collect(joining());
            String spaces = Stream.generate(() -> " ").limit(n - (i+1)).collect(joining());
            System.out.println(spaces + hashes);
        }

    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int height = in.nextInt();

        // Creates the formatting string
        StringBuilder buffer = new StringBuilder();
        buffer.append("%").append(height).append("s%n");

        // Prints the staircase
        for(int i = 0; i < height; i++) {
            String currentStep = IntStream.range(0, i + 1).mapToObj(x -> "#").collect(joining());
            System.out.printf(buffer.toString(), currentStep);
        }
    }
}

package com.vaani.dsa.algo.compete.hackerrank.algorithms.warmup;

import java.util.Arrays;
import java.util.stream.IntStream;

// https://www.hackerrank.com/challenges/mini-max-sum/problem
public class MiniMaxSum {
    static void miniMaxSum(int[] arr) {
        Arrays.sort(arr);
        long max = 0;
        for(int i = 1; i < 5; i++){
            max += arr[i];
        }
        //adding first 4 numbers to get min value.
        long min = 0;
        for(int i = 0; i < 4; i++){
            min += arr[i];
        }


//        int min = IntStream.range(0, arr.length - 1).map(i -> arr[i]).sum();
//        int max = IntStream.range(1, arr.length).map(i -> arr[i]).sum();
//        //Arrays.stream(arr).limit(4).sum();
        System.out.println("" + min + " " + max);
    }
}

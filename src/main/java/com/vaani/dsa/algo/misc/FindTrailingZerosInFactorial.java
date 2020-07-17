package com.vaani.dsa.algo.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, write a function that returns count of trailing zeroes in n!.
 * <p>
 * Examples:
 * Input: n = 5
 * Output: 1
 * Factorial of 5 is 120 which has one trailing 0.
 * <p>
 * Input: n = 20
 * Output: 4
 * Factorial of 20 is 2432902008176640000 which has
 * 4 trailing zeroes.
 * <p>
 * Input: n = 100
 * Output: 24
 * <p>
 * Reference: http://www.geeksforgeeks.org/count-trailing-zeroes-factorial-number/
 */
public class FindTrailingZerosInFactorial {
    //https://www.youtube.com/watch?v=3Hdmv_Ym8PI&feature=youtu.be
    public static int findTrailingZeros(int n) {
        int numberOfFives = 0;
        while (n != 0) {
            numberOfFives += n / 5;
            n /= 5;
        }
        return numberOfFives;
    }


    public static int findTrailingZeros2(int n) {
        int count = 0;
        for (int i = 5; n / i >= 1; i *= 5) {
            count += n / i;
        }
        return count;
    }

    int trailingZeroes(int n) {
        List<Integer> resultList = new ArrayList<>();
        int result = 0;
        //first count number of 5 in the number as number multiplied by (2,4,8) gives a trailing zero
        while (n != 0) {
            n = n / 5; //again divide as 25 will also create more trailing zero and so on
            resultList.add(n);
        }
        result = resultList.stream().reduce(0, Integer::sum);
        return result;
    }

    public static void main(String[] args) {
        int n = 100;
        System.out.println(findTrailingZeros2(100));
    }
}

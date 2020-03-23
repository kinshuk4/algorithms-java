package com.vaani.dsa.algo.numerical;

/**
 * https://www.lintcode.com/problem/digit-counts/description
 *
 * Input:
 * k = 1, n = 1
 * Output:
 * 1
 * Explanation:
 * In [0, 1], we found that 1 appeared once (1).
 *
 * Input:
 * k = 1, n = 12
 * Output:
 * 5
 * Explanation:
 * In [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], we found that one appeared five times (1, 10, 11, 12)(Note that there are two 1 in 11).
 */
public class CountNumberOccurenceInRange0ToN {
    // Counts the number of 2s in a number at d-th digit
    static int count2sinRangeAtDigit(int number, int d, int numeral) {
        int powerOf10 = (int) Math.pow(10, d);
        int nextPowerOf10 = powerOf10 * 10;
        int right = number % powerOf10;

        int roundDown = number - number % nextPowerOf10;
        int roundup = roundDown + nextPowerOf10;

        int digit = (number / powerOf10) % 10;

        // if the digit in spot digit is
        if (digit < 2) {
            return roundDown / 10;
        }

        if (digit == numeral) {
            return roundDown / 10 + right + 1;
        }
        return roundup / 10;
    }

    // Counts the number of '2' digits between 0 and n
    static int numberOf2sinRange(int numberRange, int numeral)
    {
        // Convert integer to String
        // to find its length
        String convert;
        convert = String.valueOf(numberRange);
        String s = convert;
        int len = s.length();

        // Traverse every digit and
        // count for every digit
        int count = 0;
        for (int digit = 0; digit < len; digit++)
        {
            count += count2sinRangeAtDigit(numberRange, digit, numeral);
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(numberOf2sinRange(17, 1));
    }
}

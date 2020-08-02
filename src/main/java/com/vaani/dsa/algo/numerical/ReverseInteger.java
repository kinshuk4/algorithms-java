package com.vaani.dsa.algo.numerical;

/**
 * https://leetcode.com/problems/reverse-integer/
 * 7. Reverse Integer
 * Easy
 * <p>
 * Given a 32-bit signed integer, reverse digits of an integer.
 * <p>
 * Example 1:
 * <p>
 * Input: 123
 * Output: 321
 * <p>
 * Example 2:
 * <p>
 * Input: -123
 * Output: -321
 * <p>
 * Example 3:
 * <p>
 * Input: 120
 * Output: 21
 * <p>
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 * https://www.youtube.com/watch?v=j3VMLDg7Tp4
 */

public class ReverseInteger {
    public int reverse(int x) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        boolean neg = false;
        if (x < 0) {
            neg = true;
        }

        String[] numbers = Integer.toString(Math.abs(x)).split("");

        StringBuffer bf = new StringBuffer();
        for (int i = numbers.length - 1; i >= 0; i--) {
            bf.append(numbers[i]);
        }

        String temp = bf.toString();
        int result = Integer.parseInt(temp);

        if (result >= 1073741824)
            return -1;
        else {
            return neg ? (0 - result) : result;
        }
    }

    public int reverse2(int x) {
        int result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }

        return result;
    }

    public int reverse3(int x) {
        long result = 0;
        while(x != 0){
            result = result*10 + x%10;
            x = x/10;
        }
        if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            return 0;
        else
            return (int)result;
    }
}

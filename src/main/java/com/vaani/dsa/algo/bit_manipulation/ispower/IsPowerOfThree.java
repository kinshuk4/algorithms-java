package com.vaani.dsa.algo.bit_manipulation.ispower;

/** https://leetcode.com/problems/power-of-three/
 * Check whether a number is power of 3.
 * Time complexity: O(log_9 N)
 */
public class IsPowerOfThree {

    public boolean isPowerOfThree(int num) {
        if (num == 0) {
            return false;
        }

        while (num % 9 == 0) {
            num /= 9;
        }

        return num == 1 || num == 3;
    }
}

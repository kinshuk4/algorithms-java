package com.vaani.dsa.algo.bit_manipulation;

/**
 * https://leetcode.com/problems/number-complement/
 * 476. Number Complement
 * Easy
 * Given a positive integer num, output its complement number. The complement strategy is to flip the bits of its binary representation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 * <p>
 * Example 2:
 * <p>
 * Input: num = 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 * <p>
 * Constraints:
 * <p>
 * The given integer num is guaranteed to fit within the range of a 32-bit signed integer.
 * num >= 1
 * You could assume no leading zero bit in the integer’s binary representation.
 * This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
 */
public class NumberComplement {
    public int findComplement(int num) {
        int mask = 1;
        while (mask > 0 && mask <= num) {
            num = num ^ mask;
            mask = mask << 1;
        }
        return num;
    }

    // use mod to get last bit - https://www.youtube.com/watch?v=oURSuMY4zSc
    public int findComplement2(int num) {
        int result = 0;
        int power = 1;
        while (num > 0) {
            int lastBit = num % 2;
            result += (lastBit ^ 1) * power;// flip the bit and multiply
            power <<= 1; // multiply by 2
            num >>= 1; // divide by 2
        }
        return result;
    }
}

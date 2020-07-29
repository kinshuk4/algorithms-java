package com.vaani.dsa.algo.bit_manipulation.ispower;

/**
 * https://leetcode.com/problems/power-of-two/
 * 231. Power of Two
 * Easy
 * Given an integer, write a function to determine if it is a power of two
 * Example 1:
 * <p>
 * Input: 1
 * Output: true
 * Explanation: 20 = 1
 * <p>
 * Example 2:
 * <p>
 * Input: 16
 * Output: true
 * Explanation: 24 = 16
 * <p>
 * Example 3:
 * <p>
 * Input: 218
 * Output: false
 */
public class IsPowerOfTwo {

	// best
	public boolean isPowerOfTwo(int n) {
		return (n > 0) && ((n & (n - 1)) == 0);
		// will not work for negative integers: (n != 0) && ((n & (n - 1)) == 0);
	}

	public boolean isPowerOfTwo1(int n) {
		if (n == 1)
			return true;
		if (n == 0)
			return false;
		while (n != 1) {
			if ((n & 1) != 0) {
				return false;
			}
			n = n >> 1;
		}
		return true;
	}

	public boolean isPowerOfTwoNormal(int n) {
		if (n <= 0)
			return false;
		while (n % 2 == 0) {
			n /= 2;
		}
		return n == 1 ? true : false;
	}
}

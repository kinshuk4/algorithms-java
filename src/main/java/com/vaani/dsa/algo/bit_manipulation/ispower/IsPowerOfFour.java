package com.vaani.dsa.algo.bit_manipulation.ispower;

/**
 * https://leetcode.com/problems/power-of-four/
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * Example:
 * Given num = 16, return true. Given num = 5, return false.
 * Follow up: Could you solve it without loops/recursion?
 */
public class IsPowerOfFour {
	public boolean isPowerOfFourBits(int num){
		return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;
		//0x55555555 is to get rid of those power of 2 but not power of 4
		//so that the single 1 bit always appears at the odd position
		// 0x55555555 is a hexametrical number，it is 1010101010101010101010101010101 in binary with a length of 32.
		// To make sure the 1 locates in the odd location.
	}

	public boolean isPowerOfFourUsingString(int num){
		// https://leetcode.com/problems/power-of-four/discuss/80457/Java-1-line-(cheating-for-the-purpose-of-not-using-loops)
		return Integer.toString(num, 4).matches("10*");
	}

	public boolean isPowerOfFourUsingBits2(int num){
		// https://leetcode.com/problems/power-of-four/discuss/80457/Java-1-line-(cheating-for-the-purpose-of-not-using-loops)
		return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
	}


	public boolean isPowerOfFourNumerical(int num) {
		if (num <= 0)
			return false;
		while (num != 1) {
			if (num % 4 != 0)
				return false;
			num = num / 4;
		}
		return true;
	}
}

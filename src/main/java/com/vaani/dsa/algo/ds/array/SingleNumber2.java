package com.vaani.dsa.algo.ds.array;

/** https://leetcode.com/problems/single-number-ii/
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * <p>
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <p>
 *  on 7/20/2014.
 */
public class SingleNumber2 {
    public static int singleNumber(int[] nums) {
        int[] bitVector = new int[32];
        int result = 0;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < nums.length; j++) {
                bitVector[i] += (nums[j] >> i) & 1;
            }
            result |= (bitVector[i] % 3) << i;
        }
        return result;
    }

    // Using above approach but without bitVector array
    /*
     I like to think about the number in 32 bits and just count how many 1s are there in each bit, and sum %= 3 will clear it once it reaches 3. After running for all the numbers for each bit, if we have a 1, then that 1 belongs to the single number, we can simply move it back to its spot by doing ans |= sum << i;

This has complexity of O(32n), which is essentially O(n) and very easy to think and implement. Plus, you get a general solution for any times of occurrence. Say all the numbers have 5 times, just do sum %= 5.
     */
    public int singleNumber3(int[] nums) {
        int res = 0;
        for(int i = 0; i < 32; i++){
            int sum = 0;
            for(int n: nums)
                if((n >> i & 1) == 1)
                    sum++;
            sum %= 3;
            res |= sum<<i;
        }
        return res;
    }

    public static int singleNumber2(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < nums.length; i++) {
            twos |= (nums[i] & ones);
            ones ^= nums[i];
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }

    public static void main(String[] args) {
        int[] A = {2, 2, 3, 2};
        System.out.println(singleNumber(A));
    }
}

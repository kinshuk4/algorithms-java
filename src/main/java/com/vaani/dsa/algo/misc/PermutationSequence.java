package com.vaani.dsa.algo.misc;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/permutation-sequence/
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * <p>
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * <p>
 * Note: Given n will be between 1 and 9 inclusive.
 */

public class PermutationSequence {
    PermutationSequence underTest;

    @Before
    public void before() {
        underTest = new PermutationSequence();
    }

    @After
    public void after() {
        underTest = null;
    }

    @Test
    public void testNormalCases() {
        Assert.assertEquals("213", underTest.getPermutation(3, 3));
        Assert.assertEquals("2314", underTest.getPermutation(4, 9));
    }

    public String getPermutation(int n, int k) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        int[] index = new int[n];
        int i = 0;
        //IMPORTANT: there are k - 1 permutations before
        k = k - 1;
        while (k > 0) {
            index[i] = k / getFactorial(n - 1);
            k = k % getFactorial(n - 1);
            n--;
            i++;
        }

        StringBuilder result = new StringBuilder();
        for (int value : index) {
            result.append(nums.get(value));
            nums.remove(value);
        }
        return result.toString();
    }

    public int getFactorial(int x) {
        int result = 1;
        for (int i = x; i > 1; i--) {
            result *= i;
        }
        return result;
    }
}

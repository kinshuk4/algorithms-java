package com.vaani.dsa.algo.technique.two_pointer;

/**
 * https://leetcode.com/problems/trapping-rain-water/
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * <p>
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * <p>
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater test = new TrappingRainWater();
        int[] A = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(test.trap(A));
    }

    // O(n) space and O(n) time
    public int trap(int[] A) {
        if (A.length <= 2) {
            return 0;
        }

        int n = A.length;

        //Find left most height for each bar
        int[] leftMax = new int[n];
        //Find right most height for each bar
        int[] rightMax = new int[n];


        leftMax[0] = 0;
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], A[i - 1]);
        }

        rightMax[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], A[i + 1]);
        }

        int water = 0;
        for (int i = 0; i < n; i++) {

            int waterAtIndexI = Math.min(leftMax[i], rightMax[i]) - A[i];
            if (waterAtIndexI > 0) {
                water += waterAtIndexI;
            }
        }
        return water;
    }
}

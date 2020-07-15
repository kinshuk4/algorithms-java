package com.vaani.dsa.algo.paradigm.dp;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 * <p>
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaxProductSubArray {

    // Returns the product of max product subarray. Assumes that the
    // given array always has a subarray with product more than 1
    public static int kadaneProduct(int[] arr) {

        int n = arr.length;

        // max positive product ending at the current position
        int maxEndingHere = 1;

        // min negative product ending at the current position
        int minEndingHere = 1;

        // Initialize overall max product
        int maxSoFar = 1;

        // Traverse through the array.
        // Following values are maintained after the ith iteration:
        // maxEndingHere is always 1 or some positive product ending with arr[i]
        // minEndingHere is always 1 or some negative product ending with arr[i]
        for (int i = 0; i < n; i++) {
            // If this element is positive, update maxEndingHere. Update
            // minEndingHere only if minEndingHere is negative
            if (arr[i] > 0) {
                maxEndingHere = maxEndingHere * arr[i];
                minEndingHere = Math.min(minEndingHere * arr[i], 1);
            }

            // If this element is 0, then the maximum product cannot
            // end here, make both maxEndingHere and minEndingHere 1
            // Assumption: Output is always greater than or equal to 1.
            else if (arr[i] == 0) {
                maxEndingHere = 1;
                minEndingHere = 1;
            }

            // If element is negative. This is tricky
            // maxEndingHere can either be 1 or positive.
            // minEndingHere can either be 1
            // or negative.
            // next minEndingHere will always be prev. maxEndingHere * arr[i]
            // next maxEndingHere will be 1 if prev minEndingHere is 1,
            // otherwise
            // next maxEndingHere will be prev minEndingHere * arr[i]
            else {
                int temp = maxEndingHere;
                maxEndingHere = Math.max(minEndingHere * arr[i], 1);
                minEndingHere = temp * arr[i];
            }

            // update maxSoFar, if needed
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    public static int kadaneProductModified(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;

        // max positive product ending at the current position
        int maxEndingHere = arr[0];

        // min negative product ending at the current position
        int minEndingHere = arr[0];

        // Initialize overall max product
        int maxSoFar = arr[0];

        for (int i = 1; i < n; i++) {
            int currValue = arr[i];
            int temp = maxEndingHere;

            maxEndingHere = Math.max(Math.max(maxEndingHere * currValue, minEndingHere * currValue), currValue);
            minEndingHere = Math.min(Math.min(temp * currValue, minEndingHere * currValue), currValue);

            // update maxSoFar, if needed
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;

    }

    // Driver Program to test above function
    public static void main(String[] args) {
        int[] arr = {1, -2, -3, 0, 7, -8, -2};

        System.out.println("Maximum Sub array product is "
                + kadaneProductModified(arr));

    }
}

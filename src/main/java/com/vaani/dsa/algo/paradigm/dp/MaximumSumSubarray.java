package com.vaani.dsa.algo.paradigm.dp;

/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

import org.junit.Assert;

public class MaximumSumSubarray {

    public static void main(String[] args) {
        int[] arr = {4, -1, 2, 1};
        int sol = 6;
        MaximumSumSubarray test = new MaximumSumSubarray();
        Assert.assertEquals(sol, test.maxSubarraySumKadane(arr));
        Assert.assertEquals(sol, test.maxSubarraySumKadaneModified(arr));
        Assert.assertEquals(sol, test.maxSubarraySumKadaneExtraSpace(arr));
        Assert.assertEquals(sol, test.maxSubArraySumRecursive(arr));
        Assert.assertEquals(sol, test.maxSubArraySumDP(arr));

        arr = new int[]{-1};
        sol = -1;
        Assert.assertEquals(0, test.maxSubarraySumKadane(arr));
        Assert.assertEquals(sol, test.maxSubarraySumKadaneModified(arr));
        Assert.assertEquals(sol, test.maxSubarraySumKadaneExtraSpace(arr));
        Assert.assertEquals(sol, test.maxSubArraySumRecursive(arr));
        Assert.assertEquals(sol, test.maxSubArraySumDP(arr));

        arr = new int[]{-2, -1};
        sol = -1;
        Assert.assertEquals(0, test.maxSubarraySumKadane(arr));
        Assert.assertEquals(sol, test.maxSubarraySumKadaneModified(arr));
        Assert.assertEquals(sol, test.maxSubarraySumKadaneExtraSpace(arr));
        Assert.assertEquals(sol, test.maxSubArraySumRecursive(arr));
        Assert.assertEquals(sol, test.maxSubArraySumDP(arr));
    }

    public int maxSubarraySumKadane(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;
        for (int value : arr) {
            maxEndingHere = maxEndingHere + value;

            maxEndingHere = Math.max(maxEndingHere, 0);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    // handles -ve numbers
    public int maxSubarraySumKadaneModified(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + arr[i], arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    public int maxSubarraySumKadaneExtraSpace(int[] A) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.

        int length = A.length;
        int[] maxSoFar = new int[length];

        int max = A[0];
        maxSoFar[0] = A[0];

        for (int i = 1; i < length; i++) {

            if (maxSoFar[i - 1] > 0) {
                maxSoFar[i] = maxSoFar[i - 1] + A[i];
            } else {
                maxSoFar[i] = A[i];
            }

            if (maxSoFar[i] > max) {
                max = maxSoFar[i];
            }

        }

        return max;
    }

    //Solution2
    public int maxSubArraySumRecursive(int[] A) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.

        return maxSubArraySumRecursiveHelper(0, A.length - 1, A);
    }

    public int maxSubArraySumRecursiveHelper(int start, int end, int[] A) {
        if (start > end) {
            return Integer.MIN_VALUE;
        }
        if (start == end) {
            return A[start];
        }

        int mid = (start + end) / 2;
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;

        //count contiguously
        for (int i = mid - 1, curSum = 0; i >= 0; i--) {
            curSum += A[i];
            leftMax = Math.max(leftMax, curSum);
        }

        for (int i = mid + 1, curSum = 0; i <= end; i++) {
            curSum += A[i];
            rightMax = Math.max(rightMax, curSum);
        }

        int midMax = A[mid] + Math.max(leftMax, 0) + Math.max(rightMax, 0);

        return Math.max(Math.max(maxSubArraySumRecursiveHelper(start, mid - 1, A), maxSubArraySumRecursiveHelper(mid + 1, end, A)), midMax);
    }

    public int maxSubArraySumDP(int[] a) {
        int[] solution = new int[a.length + 1];
        solution[0] = 0;

        int max = a[0];
        for (int j = 1; j < solution.length; j++) {
            solution[j] = Math.max(solution[j - 1] + a[j - 1], a[j - 1]);
            max = Math.max(max, solution[j]);
        }

        return max;
    }
}


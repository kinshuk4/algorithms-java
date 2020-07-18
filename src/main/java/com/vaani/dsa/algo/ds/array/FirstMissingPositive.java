package com.vaani.dsa.algo.ds.array;
import static com.vaani.dsa.ds.utils.generic.ArrayUtils.swap;

// @formatter:off
/**
 * https://leetcode.com/problems/first-missing-positive/
 * Given an unsorted integer array, find the first missing positive integer.
 * <p>
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * <p>
 * Your algorithm should run in O(n) time and uses constant space.
 */

// @formatter:on
public class FirstMissingPositive {

    public static void main(String[] args) {
        int[] A = {0};
        System.out.println(firstMissingPositive(A));
    }

    public static int firstMissingPositive(int[] A) {
        int i = 0;
        while (i < A.length) {
            if (A[i] >= 1 && A[i] != i + 1 && A[i] < A.length && A[i] != A[A[i] - 1])
                swap(A, i, A[i] - 1);
            else
                i++;
        }
        for (i = 0; i < A.length; i++) {
            if (A[i] != i + 1) return i + 1;
        }
        return A.length + 1;
    }


    public int missingPositive2(int[] A) {
        int misMatchPos = -1;
        for (int i = 0; i < A.length; ++i) {
            if (A[i] < 0 || A[i] >= A.length) {
                misMatchPos = i;
            }
        }
        if (misMatchPos == -1) {
            return A.length;
        }

        int pos = 0;
        while (pos < A.length) {
            if (A[pos] >= 0 &&
                    A[pos] < A.length &&
                    A[pos] != pos &&
                    pos != misMatchPos) {

                if (A[pos] == misMatchPos) {
                    A[misMatchPos] = A[pos];
                    misMatchPos = pos;
                } else {
                    int idx = A[pos];
                    A[misMatchPos] = A[pos];
                    A[pos] = A[idx];
                    A[idx] = A[misMatchPos];
                }
            } else {
                ++pos;
            }
        }

        int i = 0;
        for (; i < A.length; ++i) {
            if (A[i] != i) {
                break;
            }
        }
        return i;
    }

    //https://www.youtube.com/watch?v=2QugZILS_Q8
    public int firstMissingPositive3(int[] nums) {
        int n = nums.length;
        boolean oneExist = false;

        // check if ne exists - mainly to make sure number exists between 1 and n.
        // Any number greater than n+1 will be replaced with 1
        for (int o : nums) {
            if (o == 1) {
                oneExist = true;
            }
        }
        if (!oneExist) {
            return 1;
        }

        //making sure we will never see a number in the
        //array apart from 1...n
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int v = Math.abs(nums[i]);


            //simply invalidating an index v and it's content because we found a value v
            if (v == n) { // value is equal to n => means we have to change value with index 0
                // as array cannot have index more than n-1
                nums[0] = -1 * Math.abs(nums[0]);
            } else {
                nums[v] = -1 * Math.abs(nums[v]);
            }
        }

        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) return i;
        }

        if (nums[0] > 0) return n;
        return n + 1;
    }


}

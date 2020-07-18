package com.vaani.dsa.algo.ds.array.nsum;

//https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
public class TwoSum2 {
    public static int[] twoSumOnSortedArr(int[] numbers, int target){
        int[] result = new int[2];
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                result[0] = i;
                result[1] = j;
                return result;
            }
        }
        return result;
    }
}

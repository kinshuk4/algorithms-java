package com.vaani.dsa.algo.paradigm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 * Given a set of distinct integers, S, return all possible subsets.
 * <p>
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If S = [1,2,3], a solution is:
 * <p>
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */

public class Subsets {
    public List<List<Integer>> subsets1(int[] S) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        Arrays.sort(S);
        List<List<Integer>> results = new ArrayList<>();
        results.add(new ArrayList<>());
        int index = 0;
        while (index < S.length) {
            int cur = S[index];
            //copy to current to get rid of concurrentModification exception
            List<List<Integer>> current = new ArrayList<>(results);
            for (List<Integer> arr : current) {
                List<Integer> newList = new ArrayList<>(arr);
                newList.add(cur);
                results.add(newList);
            }
            index++;
        }
        return results;
    }


    public List<List<Integer>> subsets2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int subsetSize = (int) Math.pow(2, n);
        List<List<Integer>> results = new ArrayList<>();

        for (int i = 0; i < subsetSize; i++) {
            List<Integer> single = new ArrayList<>();
            for(int j = 0; j<n; j++){
                // Check if jth bit in the index i is set
                if((i & (1<<j)) > 0){
                    single.add(nums[j]);
                }
            }
            results.add(single);
        }

        return results;
    }
}

package com.vaani.dsa.algo.paradigm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/combination-sum-ii/
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
Each number in C may only be used once in the combination.
Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
*/

public class CombinationSum2 {

    @SuppressWarnings("Duplicates")
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        List<List<Integer>> result = new ArrayList<>();
        if (candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        helper(candidates, new ArrayList<Integer>(), 0, target, result);
        return result;
    }

    public void helper(int[] candidates, List<Integer> cur, int currIndex, int target, List<List<Integer>> result) {
        if (target == 0) {
            result.add(cur); // In case of multi threaded environment, we should do `return new ArrayList<>(curr);
        } else if (target > 0) {
            for (int i = currIndex; i < candidates.length; i++) {
                if (i > currIndex && candidates[i] == candidates[i - 1]) {
                    continue;
                }
                List<Integer> temp = new ArrayList<Integer>(cur);
                temp.add(candidates[i]);
                helper(candidates, temp, i + 1, target - candidates[i], result);
            }
        }
    }
}

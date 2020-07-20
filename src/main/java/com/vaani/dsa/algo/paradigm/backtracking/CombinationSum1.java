package com.vaani.dsa.algo.paradigm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/combination-sum/
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.
Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7,
A solution set is:
[7]
[2, 2, 3]
*/

public class CombinationSum1 {
    @SuppressWarnings("Duplicates")
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates.length == 0) return result;
        Arrays.sort(candidates);
        helper(candidates, new ArrayList<>(), 0, target, result);
        return result;
    }

    private void helper(int[] candidates, List<Integer> currList, int currIndex, int target, List<List<Integer>> result) {
        if (target == 0) {
            result.add(currList);
        } else if (target > 0) {
            for (int i = currIndex; i < candidates.length; i++) {
                ArrayList<Integer> temp = new ArrayList<>(currList);
                temp.add(candidates[i]);
                helper(candidates, temp, i, target - candidates[i], result);
            }
        }
    }

    // Not difference between the 2 - in this I am adding the value to currList and removing it, but in above
    // we are just creating an new temp list with with currList and calling the function
    private void helper2(int[] candidates, List<Integer> currList, int currIndex, int target, List<List<Integer>> result) {
        if (target == 0) {
            result.add(currList);
        } else if (target > 0) {
            for (int i = currIndex; i < candidates.length; i++) {
                currList.add(candidates[i]);
                helper(candidates, currList, i, target - candidates[i], result);
                currList.remove(currList.size() - 1); // remove the last element
            }
        }
    }
}

package com.vaani.dsa.algo.paradigm.backtracking;

import java.util.*;

/**
 * https://leetcode.com/problems/combination-sum-iii/
 * 216. Combination Sum III
 * <p>
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * <p>
 * Note:
 * <p>
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 * <p>
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum3 {
    public static void main(String[] args) {
        CombinationSum3 underTest = new CombinationSum3();
        List<List<Integer>> res = underTest.combinationSum3(8, 36);
        for (List<Integer> l : res) {
            for (int i = 0; i < l.size(); i++) {
                System.out.print(l.get(i) + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        dfs(ans, temp, 1, k, n);
        return ans;
    }

    public void dfs(List<List<Integer>> res, List<Integer> list, int begin, int k, int n) {
        if (k == 1) {
            if (n <= 9 && n >= 1) {
                list.add(n);
                List<Integer> temp = new ArrayList<>(list);
                res.add(temp);
                list.remove(list.size() - 1);
            }
            return;
        }

        for (int i = begin; i <= 9; i++) {
            if (n - i >= i + 1) {
                list.add(i);
                dfs(res, list, i + 1, k - 1, n - i);
                list.remove(list.size() - 1);
            } else {
                return;
            }
        }
    }


}

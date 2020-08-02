package com.vaani.dsa.algo.paradigm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/
 * 77. Combinations
 * Medium
 * <p>
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * <p>
 * Example:
 * <p>
 * Input: n = 4, k = 2
 * Output:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */

public class Combinations {
    static class UsingDFS1 {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<>();

            if (n <= 0 || n < k) {
                return result;
            }

            List<Integer> item = new ArrayList<>();
            dfs(n, k, 1, item, result); // because it need to begin from 1

            return result;
        }

        private void dfs(int n, int k, int start, List<Integer> curr, List<List<Integer>> result) {
            if (curr.size() == k) {
                result.add(new ArrayList<>(curr));
                return;
            }

            for (int i = start; i <= n; i++) {
                curr.add(i);
                dfs(n, k, i + 1, curr, result);
                curr.remove(curr.size() - 1);
            }
        }
    }

    static class UsingDFS2 {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<>();
            if (n <= 0 || n < k) {
                return result;
            }

            for (int i = 1; i <= n; i++) {
                ArrayList<Integer> init = new ArrayList<>();
                init.add(i);
                helper(init, n, k, result);
            }

            return result;
        }

        public void helper(ArrayList<Integer> curr, int n, int k, List<List<Integer>> result) {
            if (curr.size() == k) {
                result.add(curr);
                return;
            }
            int curMax = curr.get(curr.size() - 1);
            while (curMax < n) {
                ArrayList<Integer> res = new ArrayList<>(curr);
                res.add(++curMax);
                helper(res, n, k, result);
            }

        }
    }

}

package com.vaani.dsa.algo.paradigm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.vaani.dsa.ds.utils.generic.ArrayUtils.swap;

/**
 * https://leetcode.com/problems/permutations/
 * Given a collection of numbers, return all possible permutations.
 * <p>
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 */

public class PermutationsArray {
    public static void main(String[] args) throws Exception {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = permute2(nums);
        result.stream().forEach(System.out::println);
//        printAllPermutations(nums.length, nums);
    }

    public List<List<Integer>> permute1(int[] nums) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        return helper(nums, nums.length - 1);
    }

    public List<List<Integer>> helper(int[] num, int index) {
        List<List<Integer>> s = new ArrayList<>();
        if (index == 0) {
            List<Integer> init = new ArrayList<>();
            init.add(num[0]);
            s.add(init);
            return s;
        }

        for (List<Integer> cur : helper(num, index - 1)) {
            for (int i = 0; i <= cur.size(); i++) {
                ArrayList<Integer> temp = new ArrayList<>(cur);
                temp.add(i, num[index]);
                s.add(temp);
            }
        }
        return s;
    }

    public static List<List<Integer>> permute2(int[] nums) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        List<List<Integer>> result = new ArrayList<>();
        heapPermutation(nums.length, nums, result);
        return result;
    }

    public static void heapPermutation(int n, int[] elements, List<List<Integer>> result) {
        if (n == 1) {
            result.add(Arrays.stream(elements).boxed().collect(Collectors.toList()));
        } else {
            for (int i = 0; i < n - 1; i++) {
                heapPermutation(n - 1, elements, result);
                if (n % 2 == 0) {
                    swap(elements, i, n - 1);
                } else {
                    swap(elements, 0, n - 1);
                }
            }
            heapPermutation(n - 1, elements, result);
        }
    }


}

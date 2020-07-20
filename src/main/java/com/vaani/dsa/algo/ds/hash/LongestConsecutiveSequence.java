package com.vaani.dsa.algo.ds.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * <p>
 * Your algorithm should run in O(n) complexity.
 */

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] num = {100, 4, 200, 1, 3, 2};
        LongestConsecutiveSequence test = new LongestConsecutiveSequence();
        System.out.println(test.longestConsecutive(num));
    }

    public int longestConsecutive1(int[] num) {
        int length = num.length;

        //Key: value, Value: index
        HashMap<Integer, Integer> numMap = new HashMap<>();
        boolean[] visited = new boolean[length];

        for (int i = 0; i < length; i++) {
            numMap.put(num[i], i);
        }

        int maxLength = 0;
        for (int cur : numMap.keySet()) {
            int index = numMap.get(cur);
            if (visited[index]) {
                continue;
            }
            int curLength = 1;
            int left = cur - 1;
            int right = cur + 1;
            while (numMap.containsKey(left)) {
                int leftIndex = numMap.get(left);
                visited[leftIndex] = true;
                left--;
                curLength++;
            }
            while (numMap.containsKey(right)) {
                int rightIndex = numMap.get(right);
                visited[rightIndex] = true;
                right++;
                curLength++;
            }

            if (curLength > maxLength) {
                maxLength = curLength;
            }
        }

        return maxLength;
    }


    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

        int maxLen = 0;

        for (int number : nums) {
            int len = 1;

            // we should either take number-1 or number+1
            // as otherwise we will unnecessarily remove the number
            int up = number + 1;
            while (set.contains(up)) {
                set.remove(up);
                up++;
                len++;
            }

            int down = number - 1;
            while (set.contains(down)) {
                set.remove(down);
                down--;
                len++;
            }
            maxLen = Math.max(len, maxLen);
        }
        return maxLen;
    }
}

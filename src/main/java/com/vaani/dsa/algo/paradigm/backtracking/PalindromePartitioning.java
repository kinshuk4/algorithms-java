package com.vaani.dsa.algo.paradigm.backtracking;

import com.vaani.dsa.ds.utils.simple.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* https://leetcode.com/problems/palindrome-partitioning/
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
*/

public class PalindromePartitioning {
    public ArrayList<ArrayList<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<ArrayList<String>>();
        }

        int length = s.length();

        boolean[][] isPal = new boolean[length][length];

        for (int i = 0; i < length; i++) {
            isPal[i][i] = true;
        }

        for (int i = length - 2; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                isPal[i][i + 1] = true;
            }
            for (int j = i + 2; j < length; j++) {
                isPal[i][j] = isPal[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
            }

        }

        return partitionHelper(s, 0, isPal);
    }

    //recursively backtrack the palindrome list from startindex to the end
    public ArrayList<ArrayList<String>> partitionHelper(String s, int index, boolean[][] isPal) {

        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        if (index == s.length()) {
            result.add(new ArrayList<String>());
            return result;
        }

        for (int i = index; i < s.length(); i++) {
            if (isPal[index][i]) {
                for (ArrayList<String> temp : partitionHelper(s, i + 1, isPal)) {
                    temp.add(0, s.substring(index, i + 1));
                    result.add(temp);
                }
            }
        }

        return result;

    }

    public List<List<String>> partitionDFS(String s) {
        List<List<String>> result = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return result;
        }

        List<String> partition = new ArrayList<>();//track each possible partition
        addPalindrome(s, 0, partition, result);

        return result;
    }

    private void addPalindrome(String s, int start, List<String> partition, List<List<String>> result) {
        //stop condition
        if (start == s.length()) {
            List<String> temp = new ArrayList<>(partition);
            result.add(temp);
            return;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            String str = s.substring(start, i);
            if (StringUtil.isPalindrome(str)) {// if this half is palindrome, check if remaining part is palindrome
                partition.add(str);
                addPalindrome(s, i, partition, result);
                partition.remove(partition.size() - 1);
            }
        }
    }

    // improves by checking hashmap
    public List<List<String>> partitionDFS2(String s) {
        List<List<String>> result = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return result;
        }

        List<String> partition = new ArrayList<>();//track each possible partition
        addPalindrome2(s, 0, partition, result, new HashMap<>());

        return result;
    }

    private void addPalindrome2(String s, int start, List<String> partition, List<List<String>> result, Map<String, Boolean> map) {
        //stop condition
        if (start == s.length()) {
            List<String> temp = new ArrayList<>(partition);
            result.add(temp);
            return;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            String str = s.substring(start, i);
            if (isPalindrome(str, map)) {// if this half is palindrome, check if remaining part is palindrome
                partition.add(str);
                addPalindrome2(s, i, partition, result, map);
                partition.remove(partition.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str, Map<String, Boolean> map) {
        if (map.containsKey(str)) {
            return map.get(str);
        }
        boolean isPal = StringUtil.isPalindrome(str);
        map.put(str, isPal);
        return isPal;
    }

    public List<String> partitionDP(String s) {

        List<String> result = new ArrayList<>();

        if (s == null)
            return result;

        if (s.length() <= 1) {
            result.add(s);
            return result;
        }

        int length = s.length();

        int[][] table = new int[length][length];

        // l is length, i is index of left boundary, j is index of right boundary
        for (int l = 1; l <= length; l++) {
            for (int i = 0; i <= length - l; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (l == 1 || l == 2) {
                        table[i][j] = 1;
                    } else {
                        table[i][j] = table[i + 1][j - 1];
                    }
                    if (table[i][j] == 1) {
                        result.add(s.substring(i, j + 1));
                    }
                } else {
                    table[i][j] = 0;
                }
            }
        }

        return result;
    }


    public List<List<String>> partitionDFS_DP(String s) {
        List<List<String>> result = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
            }
        }
        helper(s, 0, new ArrayList<>(), dp, result);
        return result;
    }

    private void helper(String s, int pos, List<String> path, boolean[][] dp, List<List<String>> result) {
        if (pos == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = pos; i < s.length(); i++) {
            if (dp[pos][i]) {
                path.add(s.substring(pos, i + 1));
                helper(s, i + 1, new ArrayList<>(), dp, result);
                path.remove(path.size() - 1);
            }
        }
    }


}

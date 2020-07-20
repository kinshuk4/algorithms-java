package com.vaani.dsa.algo.paradigm.dp;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break/
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */

public class WordBreak {
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || dict.isEmpty()) {
            return false;
        }
        boolean[] chars = new boolean[s.length() + 1];
        chars[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (chars[j] && dict.contains(s.substring(j, i))) {
                    chars[i] = true;
                }
            }
        }
        return chars[s.length()];
    }


    public boolean wordBreakRecursiveMemo(String s, Set<String> dictionary) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        //TODO: CANNOT PASS THE LAST TEST CASE
        if (s.length() == 0) {
            return true;
        } else {
            int i;
            HashSet<String> currentWords = new HashSet<>();
            for (i = 1; i <= s.length(); i++) {
                String current = s.substring(0, i);
                if (dictionary.contains(current)) {
                    currentWords.add(current);
                }
            }
            for (String temp : currentWords) {
                String left = s.substring(temp.length());
                if (dictionary.contains(left)) {
                    return true;
                } else {
                    return wordBreakRecursiveMemo(left, dictionary);
                }
            }
            return false;
        }
    }

    public boolean wordBreakDictList(String s, List<String> dictList) {
        Set<String> dictionary = new HashSet<>(dictList);

        return wordBreakRecursiveMemo(s, dictionary);
    }

    // very basic dfs - https://www.geeksforgeeks.org/word-break-problem-dp-32/
    public boolean wordBreakRecursive2(String s, Set<String> dictionary) {
        if (s.length() == 0) {
            return true;
        }

        for (int i = 1; i <= s.length(); i++) {
            String current = s.substring(0, i);
            String rest = s.substring(i);

            if (dictionary.contains(current) && wordBreakRecursive2(rest, dictionary)) {
                return true;
            }
        }
        return false;

    }

    public boolean wordBreakRecursiveMemo2(String s, Set<String> dictionary) {
        return wordBreakRecursiveMemo2Helper(s, dictionary, new HashMap<>());
    }

    // will still not help much as recursion seems to be top down
    public boolean wordBreakRecursiveMemo2Helper(String s, Set<String> dictionary, Map<String, Boolean> map) {
        if (s.length() == 0) {
            return true;
        }

        if (map.containsKey(s)) {
            return map.get(s);
        }
        for (int i = 1; i <= s.length(); i++) {
            String current = s.substring(0, i);
            String rest = s.substring(i);

            if (dictionary.contains(current) && wordBreakRecursiveMemo2Helper(rest, dictionary, map)) {
                map.put(rest, true);
                return true;
            }
        }
        return false;
    }

    public boolean wordBreakDP(String s, Set<String> dictionary) {
        int n = s.length();
        //dp[i] is true if substring(0,i) is true
        // also dp[i] depends on dp[i-1], dp[i-2],... dp[0].
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        // for s = code, dict = {c, od, e, x}
        // len = 1 => i = 0 => dp[0] && dict.contains(s.substring(0, 1) => dp[0] && dict.contains(c) =>true => dp[0] = true; break
        // len = 2 => i = 0 => dp[0] && dict.contains(s.substring(0, 2) => dp[0] && dict.contains(co) => false => continue
        // len = 2 => i = 1 => dp[1] && dict.contains(s.substring(1, 2) => dp[1] && dict.contains(o) => false  => dp[2] = false; done
        // len = 3 => i = 0 => dp[0] && dict.contains(s.substring(0, 3) => dp[0] && dict.contains(cod) => false => continue
        // len = 3 => i = 1 => dp[1] && dict.contains(s.substring(1, 3) => dp[1] && dict.contains(od) => true => break => dp[3] = true
        // len = 4 => i = 0 => dp[0] && dict.contains(s.substring(0, 4) => dp[0] && dict.contains(code) => false => continue
        // len = 4 => i = 1 => dp[1] && dict.contains(s.substring(1, 4) => dp[0] && dict.contains(ode) => false => continue
        // len = 4 => i = 2 => dp[2] && dict.contains(s.substring(2, 4) => dp[0] && dict.contains(de) => false => continue
        // len = 4 => i = 3 => dp[2] && dict.contains(s.substring(3, 4) => dp[0] && dict.contains(e) => true => dp[4] = true
        for (int len = 1; len <= n; len++) {

            for (int i = 0; i < len; i++) {
                if (dp[i] && dictionary.contains(s.substring(i, len))) {
                    dp[len] = true;
                    break;
                }
            }
        }
        return dp[n];
    }


}


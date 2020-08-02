package com.vaani.dsa.algo.paradigm.dp;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break-ii/
 * 140. Word Break II
 * Hard
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * <p>
 * Example 2:
 * <p>
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * <p>
 * Example 3:
 * <p>
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */


public class WordBreak2 {
    //TLE - time limit exceed
    static class UsingRecursion {
        public List<String> wordBreak(String s, List<String> wordDict) {
            Set<String> dict = new HashSet<>(wordDict);
            List<String> result = new ArrayList<>();
            if (s == null || s.equals("")) {
                return result;
            }
            helper(s, new StringBuilder(), result, 0, dict);
            return result;
        }

        public void helper(String s, StringBuilder sb, List<String> result, int curIndex, Set<String> dict) {
            if (curIndex == s.length()) {
                result.add(sb.toString());
            } else if (curIndex < s.length()) {
                for (int i = curIndex; i <= s.length(); i++) {
                    if (dict.contains(s.substring(curIndex, i))) {
                        StringBuilder cur = new StringBuilder(sb);
                        if (cur.length() == 0) {
                            cur.append(" ");
                        }
                        cur.append(s, curIndex, i); // same as cur.append(s.substring(curIndex, i));
                        helper(s, cur, result, i, dict);
                    }
                }
            }
        }

    }

    static class UsingMemoizedDFS {
        public List<String> wordBreak(String s, List<String> wordDict) {
            return DFS(s, new HashSet<>(wordDict), new HashMap<String, List<String>>());
        }

        // DFS function returns an array including all substrings derived from s.
        private List<String> DFS(String s, Set<String> wordDict, HashMap<String, List<String>> map) {
            if (map.containsKey(s)) {
                return map.get(s);
            }

            List<String> result = new LinkedList<>();
            if (s.length() == 0) {
                result.add("");
                return result;
            }
            // will be a big problem if dictionary is too big
            for (String word : wordDict) {
                if (s.startsWith(word)) {
                    List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
                    for (String sub : sublist) {
                        result.add(word + (sub.isEmpty() ? "" : " ") + sub);
                    }
                }
            }
            map.put(s, result);
            return result;
        }

    }

    static class UsingMemoizedDFS2 {
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        public List<String> wordBreak(String s, List<String> wordDict) {
            return wordBreak(s, new HashSet<>(wordDict));
        }

        public List<String> wordBreak(String s, Set<String> wordDict) {
            List<String> result = new ArrayList<>();
            if (s == null || s.length() == 0) {
                return result;
            }
            if (map.containsKey(s)) {
                return map.get(s);
            }
            if (wordDict.contains(s)) {
                result.add(s);
            }
            for (int i = 1; i < s.length(); i++) {
                String t = s.substring(i);
                if (wordDict.contains(t)) {
                    List<String> temp = wordBreak(s.substring(0, i), wordDict);
                    if (temp.size() != 0) {
                        for (int j = 0; j < temp.size(); j++) {
                            result.add(temp.get(j) + " " + t);
                        }
                    }
                }
            }
            map.put(s, result);
            return result;
        }
    }

    // word break from the back
    static class UsingMemoizedDFS3 {
        HashMap<Integer, List<String>> map;

        public List<String> wordBreak(String s, List<String> wordDict) {
            map = new HashMap<>();
            return wordBreakHelper(s, s.length(), wordDict);
        }

        // DFS function returns an array including all substrings derived from s.
        private List<String> wordBreakHelper(String input, int end, List<String> wordDict) {
            List<String> result = new ArrayList<>();

            // We have traversed the input and are done with breaking up the word
            if (end == 0) {
                return new ArrayList<>(Arrays.asList(""));
            }

            // Using dynamic programming for optimisation
            // where the same words will have to broken down again
            if (map.containsKey(end)) {
                return map.get(end);
            }

            // Keeping the end at the end of the input
            // start counter moves along the input letters
            for (int start = 0; start < end; start++) {
                String sub = input.substring(start, end);
                if (wordDict.contains(sub)) {
                    // Once the last word in the input is found in the dictionary,
                    // we repeat the wordbreak
                    List<String> tmpList = wordBreakHelper(input, start, wordDict);
                    // and append the 'sub' at the end of every phrase in tmpList
                    for (String tmpStr : tmpList) {
                        result.add(tmpStr.length() == 0 ? sub : tmpStr + " " + sub);
                    }
                }
            }
            map.put(end, result);
            return result;
        }

    }


    public List<String> wordBreakDP(String s, Set<String> dict) {
        List<String> result = new ArrayList<>();
        if (s == null || dict.size() <= 0) {
            return result;
        }
        int length = s.length();
        // seg[i, j] means substring t start from i to j can be segmented into dictionary words
        boolean[][] seg = new boolean[length][length + 1];
        seg[0][0] = true;
        for (int i = 1; i <= length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dict.contains(s.substring(j, i))) {
                    seg[j][i] = true;
                } else {
                    for (int k = j; k <= i - 1; k++) {
                        if (seg[j][k] && seg[k][i]) {
                            seg[j][i] = true;
                            break;
                        }
                    }
                }
            }
        }
        if (!seg[0][length]) {
            return result;
        }

        dfs(s, seg, 0, result, new StringBuilder(), dict);

        return result;
    }

    private void dfs(String s, boolean[][] seg, int start, List<String> result, StringBuilder sb, Set<String> dict) {
        if (start == s.length()) {
            result.add(sb.toString());
        } else if (start < s.length()) {
            for (int end = start; end <= s.length(); end++) {
                if (seg[start][end]) {
                    String next = s.substring(start, end);
                    if (!dict.contains(next)) continue;
                    StringBuilder temp = new StringBuilder(sb);
                    if (temp.length() != 0) temp.append(" ");
                    temp.append(next);
                    dfs(s, seg, end, result, temp, dict);
                }
            }
        }
    }
}

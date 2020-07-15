package com.vaani.dsa.algo.technique.sliding_window;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * <p>
 * Note:
 * If there is no such window in S that covers all characters in T, return the emtpy string "".
 * <p>
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */


public class MinimumWindowSubstring {

    public static void main(String[] args) {
        MinimumWindowSubstring test = new MinimumWindowSubstring();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(test.minWindow1(s, t));
    }

    //O(N) - two pointer approach
    public String minWindow1(String s, String t) {
        if (s == null || t == null) return "";
        int length1 = s.length();
        int length2 = t.length();

        int[] tMap = new int[256];
        for (char c : t.toCharArray()) {
            tMap[c]++;
        }

        int[] sMap = new int[256];

        int min = Integer.MAX_VALUE;
        String minWindow = "";
        int count = 0;
        for (int begin = 0, end = 0; end < length1; end++) {
            if (tMap[s.charAt(end)] == 0) {
                continue;
            }

            sMap[s.charAt(end)]++;
            if (sMap[s.charAt(end)] <= tMap[s.charAt(end)]) count++;

            if (count == length2) {
                while (tMap[s.charAt(begin)] == 0 || sMap[s.charAt(begin)] > tMap[s.charAt(begin)]) {
                    if (sMap[s.charAt(begin)] > tMap[s.charAt(begin)]) sMap[s.charAt(begin)]--;
                    begin++;
                }
                if (end - begin + 1 < min) {
                    min = end - begin + 1;
                    minWindow = s.substring(begin, end + 1);
                }
            }

        }
        return minWindow;
    }

    //Too Slow
    public String minWindow2(String S, String T) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (S == null || T == null) return "";
        int length1 = S.length();
        int length2 = T.length();
        if (length1 < length2) return "";

        int min = Integer.MAX_VALUE;
        String minWindow = "";

        HashSet<Character> set = new HashSet<Character>();
        for (char c : T.toCharArray()) {
            set.add(c);
        }

        for (int i = 0; i + length2 <= length1; i++) {
            HashSet<Character> temp = new HashSet<Character>(set);
            int k = i;
            while (!temp.isEmpty()) {
                char c = S.charAt(k);
                if (temp.contains(c)) {
                    temp.remove(c);
                }
                k++;
                if (k >= length1) break;
            }
            if (k - i < min) {
                min = k - i;
                minWindow = S.substring(i, k);
            }
        }
        return minWindow;
    }


    /*
     * Two pointers: O(2n) ~ O(n)
     * Reference: http://leetcode.com/2010/11/finding-minimum-window-in-s-which.html
     * */
    public String minWindow3(String S, String T) {
        if (T.length() > S.length()) return "";
        int[] needToFind = new int[256];
        int[] hasFind = new int[256];

        for (int i = 0; i < T.length(); i++) {
            needToFind[T.charAt(i)]++;
        }

        int count = 0;
        int min = Integer.MAX_VALUE;
        int minStart = 0, minEnd = 0;
        for (int start = 0, end = 0; end < S.length(); end++) {
            if (needToFind[S.charAt(end)] == 0) continue;
            hasFind[S.charAt(end)]++;
            if (hasFind[S.charAt(end)] <= needToFind[S.charAt(end)])
                count++;

            if (count == T.length()) {
                while (hasFind[S.charAt(start)] == 0
                        || hasFind[S.charAt(start)] > needToFind[S.charAt(start)]) {
                    if (hasFind[S.charAt(start)] > needToFind[S.charAt(start)])
                        hasFind[S.charAt(start)]--;
                    start++;
                }

                if (end - start + 1 < min) {
                    minStart = start;
                    minEnd = end;
                    min = end - start + 1;
                }
            }
        }
        return min == Integer.MAX_VALUE ? "" : S.substring(minStart, minEnd + 1);
    }

    // using hashmap
    public String minWindow4(String s, String t) {
        HashMap<Character, Integer> goal = new HashMap<>();
        int goalSize = t.length();
        int minLen = Integer.MAX_VALUE;
        String result = "";

        //target dictionary
        for(int i=0; i<t.length(); i++){
            goal.put(t.charAt(i), goal.getOrDefault(t.charAt(i), 0)+1);
        }

        int start=0;
        int total=0;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int end=0; end<s.length(); end++){
            char c = s.charAt(end);
            if(!goal.containsKey(c)){
                continue;
            }

            //if c is a target character in the goal, and count is < goal, increase the total
            int count = map.getOrDefault(c, 0);
            if(count<goal.get(c)){
                total++;
            }

            map.put(c, count+1);

            //when total reaches the goal, trim from left until no more chars can be trimmed.
            if(total==goalSize){
                while(!goal.containsKey(s.charAt(start)) || map.get(s.charAt(start))>goal.get(s.charAt(start))){
                    char pc = s.charAt(start);
                    if(goal.containsKey(pc) && map.get(pc)>goal.get(pc)){
                        map.put(pc, map.get(pc)-1);
                    }

                    start++;
                }

                if(minLen>end-start+1){
                    minLen = end-start+1;
                    result = s.substring(start, end+1);
                }
            }
        }

        return result;
    }

}





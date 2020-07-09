package com.vaani.dsa.algo.string;

import java.util.*;
import java.util.Arrays;
import java.util.HashMap;

/** https://leetcode.com/problems/group-anagrams/
Given an array of strings, return all groups of strings that are anagrams.
Note: All inputs will be in lower-case.
*/

public class GroupOfAnagramFromList {
    public List<String> anagrams(String[] strs) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.

        List<String> results = new ArrayList<>();
        HashMap<String, ArrayList<String>> anagramsMap = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String cur = strs[i];
            char[] temp = cur.toCharArray();
            Arrays.sort(temp);
            String key = new String(temp);

            anagramsMap.putIfAbsent(key, new ArrayList<>());
            anagramsMap.get(key).add(cur);
        }

        for (ArrayList<String> s : anagramsMap.values()) {
            if (s.size() > 1) {
                results.addAll(s);
            }
        }
        return results;
    }
}

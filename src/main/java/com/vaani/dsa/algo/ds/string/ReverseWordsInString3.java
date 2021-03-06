package com.vaani.dsa.algo.ds.string;

import java.util.ArrayList;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string-iii/
 * 557. Reverse Words in a String III
 * Easy
 * <p>
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 * <p>
 * Example 1:
 * <p>
 * Input: "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * <p>
 * Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 */
public class ReverseWordsInString3 {
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (String word : words) {
            res.append(new StringBuilder(word).reverse().toString() + " ");
        }
        return res.toString().trim();
    }

    static class Solution2 {
        public String reverseWords(String s) {
            String[] words = split(s);
            StringBuilder res = new StringBuilder();
            for (String word : words) {
                res.append(reverse(word)).append(" ");
            }
            return res.toString().trim();
        }

        public String[] split(String s) {
            ArrayList<String> words = new ArrayList<>();
            StringBuilder word = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    words.add(word.toString());
                    word = new StringBuilder();
                } else
                    word.append(s.charAt(i));
            }
            words.add(word.toString());
            return words.toArray(new String[words.size()]);
        }

        public String reverse(String s) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < s.length(); i++)
                res.insert(0, s.charAt(i));
            return res.toString();
        }
    }

    static class Solution3 {
        public String reverseWords(String s) {
            final StringBuilder result = new StringBuilder();
            final StringBuilder word = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != ' ') {
                    word.append(s.charAt(i));
                } else {
                    result.append(word.reverse());
                    result.append(" ");
                    word.setLength(0);
                }
            }
            result.append(word.reverse());
            return result.toString();
        }
    }

    static class Solution4 {
        public String reverseWords(String s) {
            final StringBuilder result = new StringBuilder();
            final StringBuilder word = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != ' ') {
                    word.insert(0, s.charAt(i)); // no need to reverse
                } else {
                    result.append(word);
                    result.append(" ");
                    word.setLength(0);
                }
            }
            // last word
            result.append(word);
            return result.toString();
        }
    }
}

package com.vaani.dsa.algo.ds.string;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 * 680. Valid Palindrome II
 * Easy
 * Given a non-empty string s, you may delete at most
 * one character. Judge whether you can make it a palindrome.
 *
 * <p>Example 1: Input: "aba" Output: True Example 2: Input: "abca" Output: True Explanation: You
 * could delete the character 'c'. Note: The string will only contain lowercase characters a-z. The
 * maximum length of the string is 50000.
 * https://www.youtube.com/watch?v=L_74qbyPHXE
 */
public class ValidPalindrome2 {

    public static void main(String[] args) throws Exception {
        System.out.println(new ValidPalindrome2.UsingFor().validPalindrome("aaaaaab"));
    }

    static class UsingFor {
        public boolean validPalindrome(String s) {
            for (int i = 0, j = s.length() - 1; i < j; ) {
                if (s.charAt(i) == s.charAt(j)) {
                    i++;
                    j--;
                } else {
                    return isPalindrome(s.substring(i, j)) || isPalindrome(s.substring(i + 1, j + 1));
                }
            }
            return true;
        }

        private boolean isPalindrome(String s) {
            for (int i = 0, j = s.length() - 1; i < j; ) {
                if (s.charAt(i) == s.charAt(j)) {
                    i++;
                    j--;
                } else return false;
            }
            return true;
        }
    }

    // cleaner and not creating substrings
    static class UsingWhile {
        public boolean validPalindrome(String s) {
            int i = 0;
            int j = s.length() - 1;
            while (i < j) {
                if (s.charAt(i) != s.charAt(j)) {
                    return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
                }
                i++;
                j--;
            }
            return true;
        }

        private boolean isPalindrome(String s, int i, int j) {
            while (i < j)
                if (s.charAt(i++) != s.charAt(j--)) {
                    return false;
                }

            return true;
        }
    }

    // not very clean but using mutable char array - but slower than the string solution
    // so immutabel string code is faster
    static class UsingWhileCharArray {
        public boolean validPalindrome(String s) {
            int i = 0;
            int j = s.length() - 1;
            char[] chars = s.toCharArray();
            while (i < j) {
                if (chars[i] != chars[j]) {
                    return isPalindrome(chars, i + 1, j) || isPalindrome(chars, i, j - 1);
                }
                i++;
                j--;
            }
            return true;
        }

        private boolean isPalindrome(char[] chars, int i, int j) {
            while (i < j)
                if (chars[i++] != chars[j--]) {
                    return false;
                }

            return true;
        }
    }


}

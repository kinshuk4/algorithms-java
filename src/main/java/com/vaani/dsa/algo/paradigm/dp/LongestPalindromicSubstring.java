package com.vaani.dsa.algo.paradigm.dp;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 * <p>
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome1(String s) {
        int length = s.length();
        if (length == 0 || length == 1) {
            return s;
        }
        if (length == 2 && s.charAt(0) == s.charAt(1)) {
            return s;
        }
        int max = 0;
        int start = 0;
        int end = 1;
        boolean[][] isPal = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            isPal[i][i] = true;
        }
        for (int i = 0; i < length - 1; i++) {
            isPal[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            if (max < 2 && isPal[i][i + 1]) {
                max = 2;
                start = i;
                end = i + 2;
            }
        }

        for (int i = length - 3; i >= 0; i--) {
            for (int j = i + 2; j < length; j++) {
                isPal[i][j] = isPal[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                if (isPal[i][j] && (j - i >= max)) {
                    max = j - i;
                    start = i;
                    end = j + 1;
                }
            }
        }
        return s.substring(start, end);

    }

    public String longestPalindromeRecursive(String s) {
        if (s == null || s.length() == 0) return s;
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            String s1 = expand(s, i, i);
            if (s1.length() > longest.length()) longest = s1;
            String s2 = expand(s, i, i + 1);
            if (s2.length() > longest.length()) longest = s2;
        }
        return longest;
    }

    public String expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    public String longestPalindrome2(String str) {
        int n = str.length();
        if (n <= 1){
            return str;
        }

        // table[i][j] will be false if
        // substring str[i..j] is not palindrome.
        // Else table[i][j] will be true
        boolean[][] dp = new boolean[n][n];

        // All substrings of length 1 are palindromes
        int maxLength = 1;
        for (int i = 0; i < n; ++i){
            dp[i][i] = true;
        }


        // check for sub-string of length 2.
        int start = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // Check for lengths greater than 2.
        // k is length of substring
        for (int k = 3; k <= n; ++k) {

            // Fix the starting index
            for (int i = 0; i < n - k + 1; ++i) {
                // Get the ending index of substring from
                // starting index i and length k
                int j = i + k - 1;

                // checking for sub-string from ith index to
                // jth index iff str.charAt(i+1) to
                // str.charAt(j-1) is a palindrome
                if (dp[i + 1][j - 1]
                        && str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = true;

                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }

        // return length of LPS
        return str.substring(start, start + maxLength);
    }

    public static String longestPalindromeTwoPointer(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromMiddle(s, i, i);
            int len2 = expandFromMiddle(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > (end - start)) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expandFromMiddle(String s, int left, int right) {
        if (s == null || s.length() < 1) {
            return 0;
        }

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}

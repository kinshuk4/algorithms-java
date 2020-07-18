package com.vaani.dsa.algo.paradigm.dp;

/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/

public class RegularExpressionMatching {
    public static void main(String[] args) {
        RegularExpressionMatching test = new RegularExpressionMatching();
        //Regular test cases with only * and .
        System.out.println(test.isMatchRecursive2("aa", "a"));         //--> false
        System.out.println(test.isMatchRecursive2("aa", "aa"));        //--> true
        System.out.println(test.isMatchRecursive2("aa", "aaa"));       //--> false
        System.out.println(test.isMatchRecursive2("aa", "a*"));        //--> true
        System.out.println(test.isMatchRecursive2("aa", ".*"));        //--> true
        System.out.println(test.isMatchRecursive2("ab", ".*"));        //--> true
        System.out.println(test.isMatchRecursive2("aab", "c*a*b*"));   //--> true
        System.out.println(test.isMatchRecursive2("ab", ".*c"));       //--> false

        //More test cases with +
        System.out.println(test.isMatchRecursive2("abcdef", "a+c"));                 //--> false
        System.out.println(test.isMatchRecursive2("abcccccccdef", "abc+def"));       //--> true
        System.out.println(test.isMatchRecursive2("abdef", "abc+def"));              //--> false
    }

    public boolean isMatchRecursive(String s, String p) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (s == null) return p == null;
        return helper(s, p, 0, 0);
    }

    public boolean helper(String s, String p, int i, int j) {
        if (j == p.length()) return i == s.length();

        if (j == p.length() - 1 || p.charAt(j + 1) != '*') {
            if (i == s.length()) return false;
            return (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j)) && helper(s, p, ++i, ++j);
        }

        while (i < s.length() && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))) {
            if (helper(s, p, i++, j + 2)) return true;
        }
        return helper(s, p, i, j + 2);
    }

    public boolean isMatchRecursive2(String s, String p) {
        return isMatchHelper2(s, p, 0, 0);
    }

    public boolean isMatchHelper2(String s, String p, int i, int j) {
        if (j == p.length() - 1) return (i == s.length() - 1) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if (j == p.length()) return i == s.length();

        if (p.charAt(j + 1) != '*') {
            if (i == s.length()) return false;
            if (s.charAt(i) != p.charAt(j) && p.charAt(j) != '.') {
                return false;
            }

            if (p.charAt(j + 1) == '+') {
                while (i < s.length() && j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {
                    if (isMatchHelper2(s, p, i + 1, j + 2)) {
                        return true;
                    }
                    i++;
                }
                return false;
            } else {
                return isMatchHelper2(s, p, i + 1, j + 1);
            }
        }

        while (i < s.length() && j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {
            if (isMatchHelper2(s, p, i, j + 2)) {
                return true;
            }
            i++;
        }
        return isMatchHelper2(s, p, i, j + 2);
    }

    //https://leetcode.com/problems/regular-expression-matching/discuss/5651/Easy-DP-Java-Solution-with-detailed-Explanation
    public boolean isMatchDP(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // null string and pattern


        // dp[i][0] = false(which is default value of the boolean array) since empty pattern cannot match non-empty string
        // dp[0][i] matches string of length 0, i.e. "". Empty string can match each character in pattern where * exists and previous character matches
        // It should be #*#*#*#*..., or (#*)* if allow me to represent regex using regex
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }


        // Induction rule is very similar to edit distance, where we also consider from the end.
        // And it is based on what character in the pattern we meet.

        // 1. if p.charAt(j) == s.charAt(i), dp[i][j] = dp[i - 1][j - 1]
        //    ######a(i)
        //    ####a(j)
        // 2. if p.charAt(j) == '.', dp[i][j] = dp[i - 1][j - 1]
        // 	  #######a(i)
        //    ####.(j)
        // 3. if p.charAt(j) == '*':
        //    1. if p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i), then b* is counted as empty. dp[i][j] = dp[i][j - 2]
        //       #####a(i)
        //       ####b*(j)
        //    2.if p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i):
        //       ######a(i)
        //       ####.*(j)
        //
        // 	  	 #####a(i)
        //    	 ###a*(j)
        //      2.1 if p.charAt(j - 1) is counted as empty, then dp[i][j] = dp[i][j - 2]
        //      2.2 if counted as one, then dp[i][j] = dp[i - 1][j - 2]
        //      2.3 if counted as multiple, then dp[i][j] = dp[i - 1][j]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char currS = s.charAt(i);
                char currP = p.charAt(j);

                if (currP == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (currP == currS) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (currP == '*') {
                    char prevP = p.charAt(j - 1);
                    if (prevP != currS && prevP != '.') {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }
            }
        }
        return dp[m][n];
    }
}

package com.vaani.algo.paradigm.dp;

/*
Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.
*/

/*
f(i, j) = f(i - 1, j) + S[i] == T[j]? f(i - 1, j - 1) : 0 
Where f(i, j) is the number of distinct sub-sequence for T[0:j] in S[0:i]. 
*/

public class DistinctSubsequences {
    public int numDistinct(String S, String T) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (S == null || T == null || S.length() == 0 || S.length() < T.length()) {
            return 0;
        }
        if (S.length() == T.length()) {
            return S.equals(T) ? 1 : 0;
        }

        int[][] distinct = new int[S.length() + 1][T.length() + 1];
        for (int i = 0; i <= S.length(); i++) {
            distinct[i][0] = 1;
        }

        for (int i = 1; i <= S.length(); i++) {
            for (int j = 1; j <= T.length(); j++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    distinct[i][j] = distinct[i - 1][j - 1] + distinct[i - 1][j];
                } else {
                    distinct[i][j] = distinct[i - 1][j];
                }
            }

        }
        return distinct[S.length()][T.length()];
    }
}

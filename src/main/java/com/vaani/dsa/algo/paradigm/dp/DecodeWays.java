package com.vaani.dsa.algo.paradigm.dp;

/* https://leetcode.com/problems/decode-ways/
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
*/

/*
Count[i] = Count[i-1]  if S[i-1] is a valid char
       or   = Count[i-1]+ Count[i-2]  if S[i-1] and S[i-2] together is still a valid char.

*/
public class DecodeWays {
    public int numDecodingsDP(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (s == null || s.length() == 0) return 0;
        int length = s.length();
        int[] dp = new int[length + 1];
        dp[0] = 1; // there is only 1 way to decode empty string

        // if 0th value is 0, we cannot decode it
        if (s.charAt(0) != '0') {
            dp[1] = 1;
        } else {
            return 0;
        }


        for (int i = 2; i <= length; i++) {
            if (s.charAt(i - 1) != '0') {
                int value = Integer.parseInt(s.substring(i - 2, i));
                if (value > 10 && value <= 26) {
                    dp[i] = dp[i - 2] + dp[i - 1];
                } else {
                    dp[i] = dp[i - 1];
                }
            } else {
                if (s.charAt(i - 2) == '1' || s.charAt(i - 2) == '2') {
                    dp[i] = dp[i - 2];
                } else {
                    return 0;
                }
            }
        }
        return dp[length];

    }

    @SuppressWarnings("Duplicates")
    public static int numDecodingsDP2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // there is only 1 way to decode empty string

        // if 0th value is 0, we cannot decode it
        if (s.charAt(0) != '0') {
            dp[1] = 1;
        } else {
            return 0;
        }


        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));

            if (oneDigit >= 1) {
                dp[i] += dp[i - 1];
            }

            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }


        }
        return dp[n];
    }
}

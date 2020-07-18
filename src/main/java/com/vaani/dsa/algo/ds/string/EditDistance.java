package com.vaani.dsa.algo.ds.string;

/* https://leetcode.com/problems/edit-distance/
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
*/

/*
At the first glance, we might think this is a DFS problem, but if we see it is hard to find a quick DFS thought and the problem requires some optimal result (here is the minimum), DP is a good direction to consider.
Actually this is a classic DP problem:
The state is:   table[i][j]=minimum number steps convert word1[1:i] to word2[1:j] (here assume string starts from 1).

The optimal function is:  table[i+1][j+1] = min [table[i-1][j-1]+1 or 0 (+1 if word1[i+1]==word2[j+1], else +0),   table[i][j+1]+1, table[i+1][j]+1 ].

Initialization:
table[0][i] = i  i=1:|word1|          here 0 means "", any string convert to "" needs the length of string
table[j][0] = j  i=1:|word2|
table[0][0]=0    "" convert to  "" need 0 steps.
*/

public class EditDistance {
    public static int minDistanceBacktrack(String word1, String word2) {
        return minDistanceBacktrackHelper(word1, word2, word1.length(), word2.length());
    }

    private static int minDistanceBacktrackHelper(String word1, String word2, int m, int n) {
        // If first string is empty, the only option is to
        // insert all characters of second string into first
        if (m == 0)
            return n;

        // If second string is empty, the only option is to
        // remove all characters of first string
        if (n == 0)
            return m;

        // If last characters of two strings are same, nothing
        // much to do. Ignore last characters and get count for
        // remaining strings.
        if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
            return minDistanceBacktrackHelper(word1, word2, m - 1, n - 1);
        }

        // If last characters are not same, consider all three
        // operations on last character of first string, recursively
        // compute minimum cost for all three operations and take
        // minimum of three values.
        return 1 + Math.min(minDistanceBacktrackHelper(word1, word2, m, n - 1), // Insert ==> abc, bcd => abd => bc
                Math.min(minDistanceBacktrackHelper(word1, word2, m - 1, n), // Remove => abc, bcd => ab, bcd
                        minDistanceBacktrackHelper(word1, word2, m - 1, n - 1))); // Replace => abc,bcd => abc, bcc => ab, bc

    }

    public int minDistanceDP(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();

        if (length1 == 0 || length2 == 0) {
            return length1 > length2 ? length1 : length2;
        }
        // s1 as row and s2 as column
        int[][] dis = new int[length1 + 1][length2 + 1];

        // initial length 0 => all characters are equal to edit distance
        // s2 length = 0

        for (int i = 1; i <= length1; i++) {
            dis[i][0] = i;
        }
        for (int j = 1; j <= length2; j++) {
            dis[0][j] = j;
        }

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dis[i][j] = dis[i - 1][j - 1];
                } else {
                    // replace, delete and insert
                    dis[i][j] = min(dis[i - 1][j - 1] + 1, dis[i - 1][j] + 1, dis[i][j - 1] + 1);
                }
            }
        }
        return dis[length1][length2];
    }

    public static int min(int a, int b, int c) {
        int temp = Math.min(a, b);
        return Math.min(temp, c);
    }
}

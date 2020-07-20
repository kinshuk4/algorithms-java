package com.vaani.dsa.algo.paradigm.dp;

import java.util.Stack;
/* https://leetcode.com/problems/longest-valid-parentheses/
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

*/


public class LongestValidParentheses {
    public static void main(String[] args) {
        LongestValidParentheses test = new LongestValidParentheses();
        String s = "()(())";
        System.out.println(test.longestValidParentheses1(s));
    }

    public int longestValidParentheses(String s) {
        int maxLen = 0;

        //the position of the last ')'
        //so when left stack is empty(all matched), use this to calculate the length
        int last = -1;

        Stack<Integer> left = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left.push(i);
            } else {
                if (left.isEmpty()) {
                    last = i;
                } else {
                    left.pop();
                    if (left.isEmpty()) {
                        //all matched by now
                        maxLen = Math.max(maxLen, i - last);
                    } else {
                        maxLen = Math.max(maxLen, i - left.peek());
                    }
                }

            }
        }

        return maxLen;

    }


    /**
     * IStack solution - https://www.youtube.com/watch?v=r0-zx5ejdq0
     */
    // submitted
    public int longestValidParentheses1(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (ch.equals(')') && !stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                stack.pop();
                if (stack.isEmpty()) {
                    max = i + 1;
                } else {
                    max = Math.max(i - stack.peek(), max);
                }
            } else {
                stack.push(i);
            }
        }
        return max;
    }

    /**
     * DP solution
     * http://blog.csdn.net/abcbc/article/details/8826782
     * http://www.cnblogs.com/huntfor/p/3886111.html
     */
    public int longestValidParentheses2(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                int j = i + dp[i + 1] + 1;
                if (j < s.length() && s.charAt(j) == ')') {
                    dp[i] = dp[i + 1] + 2;
                    if (j + 1 < s.length()) dp[i] += dp[j + 1];
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}


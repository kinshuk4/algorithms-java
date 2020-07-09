package com.vaani.dsa.algo.paradigm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * <p>
 * on 8/23/2014.
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        GenerateParentheses test = new GenerateParentheses();
        for (String s : test.generateParenthesis1(2)) {
            System.out.println(s);
        }
    }

    public List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<String>();
        StringBuilder single = new StringBuilder();
        if (n == 0) return result;
        dfs1(n, n, single, result);
        return result;
    }

    public void dfs1(int left, int right, StringBuilder single, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(new String(single));
            return;
        }

        if (left > 0) {
            single.append('(');
            dfs1(left - 1, right, single, result);
            single.deleteCharAt(single.length() - 1);
        }

        if (left < right) {
            single.append(')');
            dfs1(left, right - 1, single, result);
            single.deleteCharAt(single.length() - 1);
        }
    }


    public List<String> generateParenthesis3(int n) {
        List<String> result = new ArrayList<String>();
        if (n == 0) {
            return result;
        }
        generate3(n, 0, "", result);
        return result;
    }


    public void generate3(int open, int close, String str, List<String> result) {
        if (close == 0 && open == 0) {
            result.add(str);
        }

        if (close > 0) {
            generate3(open, close - 1, str + ")", result);
        }
        if (open > 0) {
            generate3(open - 1, close + 1, str + "(", result);
        }
    }

    /*
     * Wrong solution:
     * Cannot generate (())(()) when n = 4
     * */
    public List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<String>();
        if (n == 0) return result;
        if (n == 1) {
            result.add("()");
            return result;
        }
        List<String> prevStrings = generateParenthesis2(n - 1);
        for (String s : prevStrings) {
            if (!result.contains("(" + s + ")")) result.add("(" + s + ")");
            if (!result.contains("()" + s)) result.add("()" + s);
            if (!result.contains(s + "()")) result.add(s + "()");
        }
        return result;
    }
}

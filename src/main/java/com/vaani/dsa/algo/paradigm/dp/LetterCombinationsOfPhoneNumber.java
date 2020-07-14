package com.vaani.dsa.algo.paradigm.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * Given a digit string, return all possible letter
 * combinations that the number could represent.
 *
 * <p>A mapping of digit to letters (just like on the telephone buttons) is given below. 1 2(abc)
 * 3(def) 4(ghi) 5(jkl) 6(mno) 7(pqrs) 8(tuv) 9(wxyz)
 *
 * <p>
 *
 * <p>Input:Digit string "23" Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]. Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you
 * want.
 */

public class LetterCombinationsOfPhoneNumber {
    private char[][] map = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};

    public static void main(String[] args) throws Exception {
        List<String> result = new LetterCombinationsOfPhoneNumber().letterCombinationsRecursive2("23");
        result.forEach(System.out::println);
    }


    public List<String> letterCombinationsRecursive1(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        char[] digitChars = digits.toCharArray();

        StringBuilder sb = new StringBuilder();
        letterCombinationRecursive1Helper(0, digitChars, sb, result);
        return result;
    }

    private void letterCombinationRecursive1Helper(int index, char[] digits, StringBuilder s, List<String> result) {
        if (index >= digits.length) {
            result.add(s.toString());
        } else {
            int curNum = digits[index] - '1' - 1;
            char[] digitString = map[curNum];
            for (int i = 0; i < digitString.length; i++) {
                s.append(digitString[i]);
                letterCombinationRecursive1Helper(index + 1, digits, s, result);
                //important
                s.deleteCharAt(s.length() - 1);
            }
        }
    }

    public List<String> letterCombinationsRecursive2(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] mapping = {
                "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        letterCombinationRecursive2Helper(0, digits, "", result, mapping);
        return result;
    }

    private void letterCombinationRecursive2Helper(int index, String digits, String current, List<String> result, String[] mapping) {
        if (index == digits.length()) {
            result.add(current);
            return;
        }

        // 23, index = 0 => digits.charAt = '2' - '0' = 2 => letters = abc
        String letters = mapping[digits.charAt(index) - '0'];

        for (int i = 0; i < letters.length(); i++) {
            letterCombinationRecursive2Helper(index + 1, digits, current + letters.charAt(i), result, mapping);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty() || digits.contains("1") || digits.contains("0")) {
            return result;
        }

        String[] mapping = {
                "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };
        result.add("");
        for (int i = digits.length() - 1; i >= 0; i--) {
            String str = mapping[Integer.parseInt(String.valueOf(digits.charAt(i)))];
            List<String> newList = new ArrayList<>();
            for (int j = 0, l = str.length(); j < l; j++) {
                for (String s : result) {
                    s = str.charAt(j) + s;
                    newList.add(s);
                }
            }
            result = newList;
        }
        return result;
    }
}

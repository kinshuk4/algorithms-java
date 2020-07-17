package com.vaani.dsa.algo.numerical;

/**
 * https://leetcode.com/problems/excel-sheet-column-number/
 * Given a column title as appear in an Excel sheet,
 * return its corresponding column number.
 * <p>
 * For example:
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * <p>
 * Example 1:
 * <p>
 * Input: "A"
 * Output: 1
 * <p>
 * Example 2:
 * <p>
 * Input: "AB"
 * Output: 28
 * <p>
 * Example 3:
 * <p>
 * Input: "ZY"
 * Output: 701
 */
public class ExcelSheetColumnNumber {
    String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) throws Exception {
        System.out.println(new ExcelSheetColumnNumber().titleToNumber("AAB"));
    }

    public int titleToNumber(String s) {
        int total = 0;
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            int pos = ALPHABET.indexOf(c) + 1;
            int pow = (int) Math.pow(26, j++);
            total += (pow * pos);
        }
        return total;
    }
}

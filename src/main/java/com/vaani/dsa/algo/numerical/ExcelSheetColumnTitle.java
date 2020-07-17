package com.vaani.dsa.algo.numerical;

/**
 * https://leetcode.com/problems/excel-sheet-column-title/
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * <p>
 * For example:
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * <p>
 * Example 1:
 * <p>
 * Input: 1
 * Output: "A"
 * <p>
 * Example 2:
 * <p>
 * Input: 28
 * Output: "AB"
 * <p>
 * Example 3:
 * <p>
 * Input: 701
 * Output: "ZY"
 */
public class ExcelSheetColumnTitle {
    /**
     * Reference: http://www.bubuko.com/infodetail-541678.html
     */

    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n != 0) {
            n--;
            result.insert(0, (char) ('A' + n % 26));
            n /= 26;
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(52));
    }

    private static final String CONST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String convertToTitle2(int n) {
        StringBuilder ans = new StringBuilder();
        while (n > 0) {
            int mod = n % 26;
            n /= 26;
            if (mod == 0) {
                ans.append('Z');
                n -= 1;
            } else {
                ans.append(CONST.charAt(mod - 1));
            }
        }
        return ans.reverse().toString();
    }
}

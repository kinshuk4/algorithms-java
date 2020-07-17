package com.vaani.dsa.algo.numerical.number;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/roman-to-integer/
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * <p>
 * Example 1:
 * <p>
 * Input: "III"
 * Output: 3
 * <p>
 * Example 2:
 * <p>
 * Input: "IV"
 * Output: 4
 */
public class RomanToInteger {
    public static void main(String[] args) {
        RomanToInteger test = new RomanToInteger();
        System.out.println(test.romanToInt("DCXXI"));
    }

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};
        //MCMLIV -> 1954
        int result = 0;
        int i = 0;
        int n = s.length();
        while (i < n) {
            char curr = s.charAt(i);
            if (i == n - 1) {
                result += map.get(curr);
                break;
            }
            char next = s.charAt(i + 1);
            if (map.get(curr) < map.get(next)) {
                result += map.get(next) - map.get(curr);
                i += 2;
            } else {
                result += map.get(curr);
                i++;
            }
        }
        return result;
    }
}

package com.vaani.dsa.algo.numerical.number;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/integer-to-roman/
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
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
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Example 1:
 * <p>
 * Input: 3
 * Output: "III"
 * <p>
 * Example 2:
 * <p>
 * Input: 4
 * Output: "IV"
 * <p>
 * Example 3:
 * <p>
 * Input: 9
 * Output: "IX"
 * <p>
 * Example 4:
 * <p>
 * Input: 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 */
public class IntegerToRoman {
    public static void main(String[] args) {
        int num = 1954;
        IntegerToRoman test = new IntegerToRoman();
        System.out.println(test.intToRoman(num));
    }

    public String intToRoman(int num) {
        Map<Integer, Character> map = new HashMap<Integer, Character>() {{
            put(1, 'I');
            put(5, 'V');
            put(10, 'X');
            put(50, 'L');
            put(100, 'C');
            put(500, 'D');
            put(1000, 'M');
        }};

        StringBuilder result = new StringBuilder();
        String number = String.valueOf(num);
        int n = number.length() - 1;
        for (int i = 0; i < number.length(); i++) {
            int digit = Character.getNumericValue(number.charAt(i));

            int base = (int) Math.pow(10, n);

            if (digit >= 1 && digit <= 3) {
                for (int j = 1; j <= digit; j++) {
                    result.append(map.get(base));
                }
            } else if (digit == 4) {
                result.append(map.get(base));
                result.append(map.get(base * 5));
            } else if (digit >= 5 && digit < 9) {
                result.append(map.get(base * 5));
                for (int j = 1; j <= digit - 5; j++) {
                    result.append(map.get(base));
                }
            } else if (digit == 9) {
                result.append(map.get(base));
                result.append(map.get(base * 10));
            }
            n--;
        }
        return result.toString();
    }

    public String intToRoman2(int num) {
        TreeMap<Integer, String> map = new TreeMap<>()
        {{
            put(1, "I");
            put(5, "V");
            put(10, "X");
            put(50, "L");
            put(100, "C");
            put(500, "D");
            put(1000, "M");
            put(4, "IV");
            put(9, "IX");
            put(40, "XL");
            put(90, "XC");
            put(400, "CD");
            put(900, "CM");
        }};


        StringBuilder sb = new StringBuilder();
        while(num>0){
            int closestNum = map.floorKey(num);
            sb.append(map.get(closestNum));
            num -= closestNum;
        }

        return sb.toString();
    }

    public String intToRoman3(int num) {
        int[] vedic = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] roman = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < vedic.length; i++){
            while(num >= vedic[i]){
                sb.append(roman[i]);
                num -= vedic[i];
            }
        }
        return sb.toString();
    }

}

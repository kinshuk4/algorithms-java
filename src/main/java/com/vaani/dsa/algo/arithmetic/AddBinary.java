package com.vaani.dsa.algo.arithmetic;

/**
 * https://leetcode.com/problems/add-binary/
 * Given two binary strings, return their sum (also a binary string).
 * <p>
 * Example 1:
 * <p>
 * Input: a = "11", b = "1"
 * Output: "100"
 * <p>
 * Example 2:
 * <p>
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
public class AddBinary {
    public static void main(String[] args) {
        String a = "1";
        String b = "111";
        AddBinary test = new AddBinary();
        System.out.println(test.addBinary(a, b));
    }

    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        a = new StringBuilder(a).reverse().toString();
        b = new StringBuilder(b).reverse().toString();

        int index = 0;
        int extra = 0;
        while (index < a.length() || index < b.length()) {
            int first = index < a.length() ? Character.getNumericValue(a.charAt(index)) : 0;
            int second = index < b.length() ? Character.getNumericValue(b.charAt(index)) : 0;
            int digit = (first + second + extra) % 2;
            extra = first + second + extra >= 2 ? 1 : 0;
            result.append(digit);
            index++;
        }

        if (extra == 1) {
            result.append(1);
        }

        return result.reverse().toString();
    }

    public String addBinary2(String a, String b) {
        if (a == null || a.equals("")) {
            return b;
        }
        if (b == null || b.equals("")) {
            return a;
        }

        int length1 = a.length() - 1;
        int length2 = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (length1 >= 0 || length2 >= 0 || carry != 0) {
            char a1, b1;
            if (length1 < 0) {
                a1 = '0';
            } else {
                a1 = a.charAt(length1);
            }
            if (length2 < 0) {
                b1 = '0';
            } else {
                b1 = b.charAt(length2);
            }

            int sum =  (a1 - '0' + b1 - '0' + carry);
            carry = sum / 2;
            sb.insert(0, sum % 2);
            length1--;
            length2--;
        }
        return sb.toString();
    }
}

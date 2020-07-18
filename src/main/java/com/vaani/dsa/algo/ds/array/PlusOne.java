package com.vaani.dsa.algo.ds.array;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list.
 * <p>
 * on 7/22/2014.
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }

        int n = digits.length;
        boolean allNine = true;
        for (int digit : digits) {
            if (digit != 9) {
                allNine = false;
                break;
            }
        }

        if (allNine) {
            int[] result = new int[n + 1];
            result[0] = 1;
            return result;
        } else {
            digits[n - 1] = digits[n - 1] + 1;
            int carry = 0;
            for (int i = n - 1; i >= 0; i--) {
                digits[i] += carry;
                carry = 0; // reset carry as we have already added to digit
                if (digits[i] >= 10) {
                    digits[i] -= 10;
                    carry = 1;
                }
            }
            return digits;
        }
    }


}

package com.vaani.dsa.algo.arithmetic;

/**
 * https://leetcode.com/problems/divide-two-integers/
 * Divide two integers without using multiplication, division and mod operator.
 * <p>
 * Reference:
 * http://www.lifeincode.net/programming/leetcode-divide-two-integers-java/
 * http://www.cnblogs.com/ganganloveu/p/4174062.html
 */
public class DivideTwoIntegers {
    public static void main(String[] args) {
        DivideTwoIntegers test = new DivideTwoIntegers();
        System.out.println(test.divide(-2147483648, 2));
    }

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }

        long p = Math.abs((long) dividend);
        long q = Math.abs((long) divisor);

        int quotient = 0;
        while (p >= q) {
            int count = 0;
            while (p >= (q << count)) {
                count++;
            }
            quotient += 1 << (count - 1);
            p -= q << (count - 1);
        }

        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            return -quotient;
        } else {
            return quotient;
        }
    }

    public int divide2(int dividend, int divisor) {
        boolean negative = false;
        //check if negative
        if (dividend == 0) {
            return 0;
        }
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            negative = true;
        }

        if (divisor == 1 || divisor == -1) {
            return negative ? (0 - Math.abs(dividend)) : Math.abs(dividend);
        }

        long dividend1 = Math.abs((long) dividend);
        long divisor1 = Math.abs((long) divisor);

        long remainder = dividend1;
        int result = 0;

        while (remainder >= divisor1) {
            for (int i = 1; i * divisor1 <= remainder; i = i * 2) {
                remainder = remainder - i * divisor1;
                result += i;
            }

        }

        return negative ? (0 - result) : result;

    }

    public int divide3(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        else if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        else if (divisor == 1) return dividend;
        boolean isNegative = ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0));
        dividend = (dividend < 0) ? -dividend : dividend;
        divisor = (divisor < 0) ? -divisor : divisor;
        int sum, result = 0;
        while (true) {
            int d1 = divisor;
            sum = 0;
            if (dividend - d1 == 0) {
                sum += 1;
                result += sum;
                break;
            }
            if (dividend - d1 < 0) break;
            while (dividend - (d1 << 1) > 0) {
                d1 <<= 1;
                sum += 1;
            }
            result += (1 << sum);
            dividend = dividend - d1;
        }
        return isNegative ? -result : result;
    }
}

package com.vaani.dsa.algo.numerical;

/* https://leetcode.com/problems/sqrtx/
Implement int sqrt(int x).

Compute and return the square root of x.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2

Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.

*/

public class SqrtX {
    public static void main(String[] args) throws Exception {
        System.out.println(new SqrtX().sqrtIterative2(Integer.MAX_VALUE));
    }

    public int sqrtIterative(int x) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (x <= 1) {
            return x;
        }
        int start = 0;
        int end = x;
        int mid;
        while (end >= start) {
            mid = (start + end) / 2;
            int div = x / mid;
            if (div < mid) {
                end = mid - 1;
            } else if (div == mid) {
                return mid;
            } else {
                start = mid + 1;
            }

        }
        return (start + end) / 2;
    }

    // submitted
    public int sqrtIterative2(int x) {
        int s = 0, e = x;
        long ans = 0L;
        while (s <= e) {
            long m = s + (e - s) / 2;
            long prod = m * m;
            if (prod <= x) {
                s = (int) (m + 1);
                ans = m;
            } else {
                e = (int) m - 1;
            }
        }
        return (int) ans;
    }
}

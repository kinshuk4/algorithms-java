package com.vaani.dsa.algo.numerical;

/** https://leetcode.com/problems/sum-of-two-integers/
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 */
public class SumOfTwoIntegersWithoutPlusMinus {
    public int getSum(int a, int b) {
        while(b!=0){
            int c = a&b;
            a=a^b;
            b=c<<1;
        }
        return a;
    }
}

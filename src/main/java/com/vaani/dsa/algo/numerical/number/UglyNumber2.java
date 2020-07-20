package com.vaani.dsa.algo.numerical.number;

import java.util.*;

/**
 * https://leetcode.com/problems/ugly-number-ii/
 * Write a program to find the n-th ugly number.
 * <p>
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * <p>
 * Example:
 * <p>
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * <p>
 * Note:
 * <p>
 * 1 is typically treated as an ugly number.
 * n does not exceed 1690.
 */
public class UglyNumber2 {
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }

        List<Integer> list = new ArrayList<>();
        list.add(1);

        int i = 0;
        int j = 0;
        int k = 0;

        while (list.size() < n) {
            int m2 = list.get(i) * 2;
            int m3 = list.get(j) * 3;
            int m5 = list.get(k) * 5;

            int min = Math.min(m2, Math.min(m3, m5));
            list.add(min);

            if (min == m2)
                i++;

            if (min == m3)
                j++;

            if (min == m5)
                k++;
        }

        return list.get(list.size() - 1);

    }

    // same as above
    public int nthUglyNumberOn(int n) {
        if(n<=0)
            return 0;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);

        int i=0;
        int j=0;
        int k=0;
        int m2 = list.get(i)*2;
        int m3 = list.get(j)*3;
        int m5 = list.get(k)*5;

        while(list.size()<n){

            int min = Math.min(m2, Math.min(m3, m5));
            list.add(min);

            if(min==m2){
                i++;
                m2 = list.get(i)*2;
            }

            if(min==m3){
                j++;
                m3 = list.get(j)*3;
            }

            if(min==m5){
                k++;
                m5 = list.get(k)*5;
            }
        }

        return list.get(list.size()-1);
    }

    // doesnt work as 2*3 and 3*2 generate 6 which are duplicate
    public int nthUglyNumberPQ(int n) {
        if (n <= 0) {
            return 0;
        }

        Queue<Integer> list = new PriorityQueue<>();
        list.add(1);

        int i = 0;
        int min = 1;
        while (i <= n) {
            min = list.poll();
            i++;
            list.add(min * 2);
            list.add(min * 3);
            list.add(min * 5);
        }

        return min;
    }

    // submitted
    public int nthUglyNumberTreeset(int n) {
        if (n <= 0) {
            return 0;
        }

        SortedSet<Long> next = new TreeSet<>();
        next.add(1L);

        // to avoid integer overflow
        long min = 1;
        int i = 0;
        while (i < n) {
            min = next.first();
            i++;
            next.add(min * 2);
            next.add(min * 3);
            next.add(min * 5);
            next.remove(min);

        }

        return (int)min;
    }
}

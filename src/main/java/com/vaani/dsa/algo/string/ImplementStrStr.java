package com.vaani.dsa.algo.string;

import com.vaani.dsa.algo.search.string.RabinKarp;

import java.math.BigInteger;
import java.util.Random;

/**
 * https://leetcode.com/problems/implement-strstr/
 * Implement strStr().
 * <p>
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * Example 1:
 * <p>
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * <p>
 * Example 2:
 * <p>
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * <p>
 * Clarification:
 * <p>
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * <p>
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
public class ImplementStrStr {
    public static void main(String[] args) throws Exception {
        System.out.println(new ImplementStrStr().strStr1("AABB", ""));
    }

    public int strStr1(String haystack, String needle) {
        if (haystack.isEmpty() && needle.isEmpty()) {
            return 0;
        }
        if (needle.isEmpty()) {
            return 0;
        }
        for (int i = 0, l = haystack.length(); i < l; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (isEqual(haystack, needle, i)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private boolean isEqual(String haystack, String needle, int i) {
        int hL = haystack.length();
        int nL = needle.length();
        int j = 0;
        while (i < hL && j < nL) {
            if (haystack.charAt(i) != needle.charAt(j)) return false;
            i++;
            j++;
        }
        return j >= nL;
    }


    public int strStr2(String haystack, String needle) {
        if (haystack.isEmpty() && needle.isEmpty()) {
            return 0;
        }
        if (needle.isEmpty()) {
            return 0;
        }

        if(needle.length() > haystack.length()){
            return -1;
        }

        RabinKarp rk = new RabinKarp(needle);
        return rk.search(haystack);
    }


//    public static void main(String[] args) {
//        ImplementstrStr test = new ImplementstrStr();
//        String haystack = "i love qiqi";
//        String needle = "qiqi";
//        System.out.println(test.strStr(haystack, needle));
//    }
//
//    public String strStr(String haystack, String needle) {
//        if (needle.isEmpty()) {return haystack;}
//        int[] next = new int[needle.length()];
//        buildNext(needle, next);
//        int i = 0, j = 0;
//
//        while (i < haystack.length()) {
//            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
//                i++;
//                j++;
//            } else {
//                j = next[j];
//            }
//            if (j == needle.length()) return haystack.substring(i - j);
//        }
//        return null;
//    }
//
//    private void buildNext(String needle, int[] next) {
//        int j = 0, k = -1;
//        next[0] = -1;
//        while (j < needle.length() - 1) {
//            if (k == -1 || needle.charAt(j) == needle.charAt(k)) {
//                next[++j] = ++k;
//            } else {
//                k = next[k];
//            }
//        }
//    }
}

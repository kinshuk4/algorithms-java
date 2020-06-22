package com.vaani.dsa.algo.compete.hackerrank.algorithms.implementation;

import org.junit.Assert;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/kangaroo/problem
 */
public class Kangaroo {

    static String kangaroo1(int x1, int v1, int x2, int v2) {
        if (v2 > v1) {
            return "NO";
        } else {
            boolean flag = false;
            while (x1 <= x2) {
                if (x1 == x2) {
                    flag = true;
                    break;
                } else {
                    x1 += v1;
                    x2 += v2;
                }
            }

            if (flag) {
                return "YES";
            }

        }

        return "NO";

    }

    // x1 + v1*X = x2 + v2*X => (v1-v2) * X = x2 - x1 => X = (x2-x1) / (v1 - v2)
    // X = - (x2 - x1) / (v2 - v1) => if X > 0 then true else false;
    // But they should meet at the integer point, so we have to see dx%dv == 0
    static String kangaroo(int x1, int v1, int x2, int v2) {
        int deltaV = (v2 - v1);
        int deltaX = (x2 - x1);
        if (deltaX == 0) {
            return "YES";
        } else if (deltaV >= 0) {
            return "NO";
        }

        if (deltaX % deltaV == 0) {
            return "YES";
        } else{
            return "NO";
        }

//        int meetingPoint = -deltaX / deltaV;

//        if (meetingPoint > 0) {
//            return "YES";
//        }
//        return "NO";
    }

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int x1 = in.nextInt();
//        int v1 = in.nextInt();
//        int x2 = in.nextInt();
//        int v2 = in.nextInt();

        Assert.assertEquals("NO", kangaroo(0, 2, 5, 3));
        Assert.assertEquals("YES", kangaroo(0, 3, 4, 2));
    }
}

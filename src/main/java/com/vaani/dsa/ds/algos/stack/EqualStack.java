package com.vaani.dsa.ds.algos.stack;

import org.junit.Assert;
import org.w3c.dom.ls.LSOutput;

import java.util.Stack;

public class EqualStack {
    static int equalStacks(int[] heightArr1, int[] heightArr2, int[] heightArr3) {
        /*
         * Write your code here.
         */
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        Stack<Integer> s3 = new Stack<>();

        int h1 = getHeight(heightArr1, s1);
        int h2 = getHeight(heightArr2, s2);
        int h3 = getHeight(heightArr3, s3);

        while (true) {
            if (areEqual(h1, h2, h3)) {
                return h1;
            }
            int min = getMinHeight(h1, h2, h3);
            h1 = updateHeight(s1, h1, min);
            h2 = updateHeight(s2, h2, min);
            h3 = updateHeight(s3, h3, min);

            if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
                break;
            }

        }
        return 0;
    }

    private static int updateHeight(Stack<Integer> s, int h, int min) {
        while (h > min && !s.isEmpty()) {
            h = h - s.pop();
        }
        return h;
    }

    private static int getHeight(int[] heightArr, Stack<Integer> s) {
        int h = 0;
        for (int i = heightArr.length - 1; i > -1; i--) {
            s.push(heightArr[i]);
            h += heightArr[i];
        }
        return h;
    }

    public static int getMinHeight(int h1, int h2, int h3) {
        if (Math.min(h1, h2) < Math.min(h2, h3)) {
            return Math.min(h1, h2);
        }
        return Math.min(h2, h3);
    }

    public static boolean areEqual(int h1, int h2, int h3) {
        if (h1 == h2 && h1 == h3) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Assert.assertEquals(5, equalStacks(new int[]{3, 2, 1, 1, 1}, new int[]{4, 3, 2}, new int[]{1, 1, 4, 1}));
    }
}

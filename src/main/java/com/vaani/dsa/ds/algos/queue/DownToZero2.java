package com.vaani.dsa.ds.algos.queue;

import org.junit.Assert;
import org.w3c.dom.ls.LSOutput;

import java.util.Optional;

//https://www.hackerrank.com/challenges/down-to-zero-ii/problem
public class DownToZero2 {
    static Optional<Integer> getFactors(int n) {
        for (int i = n / 2; i >= 2; i--) {
            if (n % i == 0) {
                int other = n / i;
                if (other == 1){
                    return Optional.of(i);
                }
                return Optional.of(Math.max(i, n / i));
            }
        }
        return Optional.empty();
    }

    static int downToZero(int n) {
        /*
         * Write your code here.
         */
        int count = 0;
        while (n != 0) {
            Optional<Integer> k = getFactors(n);
            if (k.isPresent()) {
                n = k.get();
            } else {
                n = n - 1;
            }
            count++;
        }

        return count;

    }

    public static void main(String[] args) {
        Assert.assertEquals(3, downToZero(4));
        Assert.assertEquals(3, downToZero(6));
    }
}

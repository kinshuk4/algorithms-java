package com.vaani.dsa.algo.numerical;

public class AddTwoNumbersWithoutPlusOperator {

    static int add(int x, int y) {
        // Iterate till there is no carry
        while (y != 0)  {
            // carry now contains common
            // set bits of x and y
            int carry = x & y;

            // Sum of bits of x and
            // y where at least one
            // of the bits is not set
            x = x ^ y;

            // Carry is shifted by
            // one so that adding it
            // to x gives the required sum
            y = carry << 1;
        }
        return x;
    }

    public static void main(String[] args) {
        int result  = add(5, 7);
        System.out.println(result);
    }
}
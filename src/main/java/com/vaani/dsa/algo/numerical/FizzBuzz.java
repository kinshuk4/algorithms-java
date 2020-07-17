package com.vaani.dsa.algo.numerical;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/fizz-buzz/
 * WWrite a program that outputs the string representation of numbers from 1 to n.
 * <p>
 * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
 */
public class FizzBuzz {
    public static void main(String[] args) {
        FizzBuzz test = new FizzBuzz();
        test.fizzBuzz1(40);
    }

    public List<String> fizzBuzz1(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");

            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(Integer.toString(i)); // Integer.toString makes solution faster, instead of ""+i
            }
        }
        return result;
    }

}

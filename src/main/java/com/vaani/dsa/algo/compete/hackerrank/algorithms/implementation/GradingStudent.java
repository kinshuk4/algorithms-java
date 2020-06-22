package com.vaani.dsa.algo.compete.hackerrank.algorithms.implementation;

import java.util.*;
//https://www.hackerrank.com/challenges/grading/problem
public class GradingStudent {
    public static List<Integer> gradingStudents(List<Integer> grades) {
        // Write your code here
        final List<Integer> result = new ArrayList<>();
        grades.forEach(i -> {
            if (i < 38 || i % 5 < 3) {
                result.add(i);
            } else {
                result.add((i / 5 + 1) * 5);
            }

        });
        return result;
    }
}

package com.vaani.dsa.algo.array;

import java.util.*;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * <p>
 * For example, given numRows = 5,
 * Return
 */
/*
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

public class PascalsTriangle {
    public static void main(String[] args) {
        PascalsTriangle test = new PascalsTriangle();
        List<List<Integer>> result = test.generate(10);
        for (List<Integer> single : result) {
            System.out.println(single);
        }
    }

    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> curr;
        List<Integer> prev = new ArrayList<Integer>();
        for (int i = 0; i < numRows; i++) {
            curr = new ArrayList<Integer>();
            if (i > 0) prev = result.get(i - 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    curr.add(1);
                else
                    curr.add(prev.get(j - 1) + prev.get(j));
            }
            result.add(curr);
        }
        return result;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows <= 0) {
            return result;
        }

        List<Integer> prev = new ArrayList<Integer>();
        prev.add(1);
        result.add(prev);

        for (int i = 2; i <= numRows; i++) {
            ArrayList<Integer> curr = new ArrayList<Integer>();

            curr.add(1); //first
            for (int j = 0; j < prev.size() - 1; j++) {
                curr.add(prev.get(j) + prev.get(j + 1)); //middle
            }
            curr.add(1);//last

            result.add(curr);
            prev = curr;
        }

        return result;
    }
}

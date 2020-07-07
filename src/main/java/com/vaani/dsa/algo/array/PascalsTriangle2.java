package com.vaani.dsa.algo.array;

import java.util.*;
/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * <p>
 * For example, given k = 3,
 * Return [1,3,3,1].
 * <p>
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 * <p>
 */
public class PascalsTriangle2 {
    public static void main(String[] args) {
        PascalsTriangle2 test = new PascalsTriangle2();
        System.out.println(test.getRow(3));
    }
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();

        if (rowIndex < 0)
            return result;

        result.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = result.size() - 2; j >= 0; j--) {
                result.set(j + 1, result.get(j) + result.get(j + 1));
            }
            result.add(1);
        }
        return result;
    }

    public List<Integer> getRow3(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        if (rowIndex < 0) return list;
        list.add(1);
        if (rowIndex == 0) return list;

        list.add(1);
        for (int i = 2; i <= rowIndex; i++) {
            list.add(1, list.get(0) + list.get(1));
            for (int j = 2; j < list.size() - 1; j++) {
                list.set(j, list.get(j) + list.get(j + 1));
            }
        }

        return list;
    }

    public List<Integer> getRow2(int rowIndex) {
        List<Integer> result = new ArrayList<Integer>();
        List<Integer> tmp = new ArrayList<Integer>();
        for (int i = 0; i <= rowIndex; i++) {
            tmp.clear();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    tmp.add(1);
                else
                    tmp.add(result.get(j - 1) + result.get(j));
            }
            result = new ArrayList<Integer>(tmp);
        }
        return result;
    }
}

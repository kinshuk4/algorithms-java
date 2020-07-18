package com.vaani.dsa.algo.ds.array;

import java.util.HashMap;
import java.util.Map;

//https://www.hackerrank.com/challenges/sparse-arrays/problem?h_r=next-challenge&h_v=zen
public class SparseArrays {
    static int[] matchingStrings(String[] strings, String[] queries) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String phrase : strings) {
            if (!frequencyMap.containsKey(phrase)) {
                frequencyMap.put(phrase, 1);
            } else {
                frequencyMap.put(phrase, frequencyMap.get(phrase) + 1);
            }
        }

        int[] result = new int[queries.length];
        int i = 0;
        for (String query : queries) {
            if (frequencyMap.containsKey(query)) {
                result[i++] = frequencyMap.get(query);
            } else {
                result[i++] = 0;
            }
        }

        return result;

    }
}

package com.vaani.dsa.algo.ds.array;

import java.util.*;

/**
 * Given an array of number. Find the most repeated number. If there are
 * multiple such number, return one of them following the uniform distribution.
 */
public class MostRepeatedNumber {

    public static int mostRepeatedNumber(int[] vals) {
        Map<Integer, Integer> counts = new HashMap<>();

        int maxCount = 0;
        List<Integer> candidates = new ArrayList<>();

        for (int val : vals) {
            Integer count = counts.get(val);
            if (count == null) {
                count = 0;
            }
            ++count; // update count
            if (count > maxCount) { // empty candidates
                candidates.clear();
                candidates.add(val);
                maxCount = count;
            } else if (count == maxCount) {
                candidates.add(val);
            }
            counts.put(val, count);
        }

        Random rnd = new Random();
        int rndIdx = rnd.nextInt(candidates.size());

        return candidates.get(rndIdx);
    }

}

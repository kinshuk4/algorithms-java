package com.vaani.dsa.algo.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * <p>
 * Example 1:
 * <p>
 * Input: [10,2]
 * Output: "210"
 * <p>
 * Example 2:
 * <p>
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * <p>
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class LargestNumber {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }

        // Convert int array to String array, so we can sort later on
        String[] stringNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // faster way to convert string to int
            // https://stackoverflow.com/questions/653990/what-is-the-most-efficient-way-to-convert-an-int-to-a-string
            stringNums[i] = Integer.toString(nums[i]);
        }

        // Comparator to decide which string should come first in concatenation
        Comparator<String> comp = (str1, str2) -> {
            String s1 = str1 + str2;
            String s2 = str2 + str1;
            return s2.compareTo(s1); // reverse order here, so we can do append() later
        };

        Arrays.sort(stringNums, comp);
        // An extreme edge case by LC, say you have only a bunch of 0 in your int array
        if (stringNums[0].charAt(0) == '0') {
            return "0";
        }
        StringBuilder sb = new StringBuilder(nums.length);// just adding initial capacity for faster
        Arrays.stream(stringNums).forEach(word -> sb.append(word));
        return sb.toString();
        // StringBuilder is still faster than collector joining and string join
        // sb > collector.joining > String.joining
        //https://stackoverflow.com/questions/50177385/collectors-joining-vs-stringbuilder-append
        // https://masterfromus.com/2020/02/17/stringjoiner-vs-stringbuffer-vs-collectors-joining-performance-test/
//        return Arrays.stream(stringNums).collect(Collectors.joining());
//                return String.join("", stringNums);
    }
}

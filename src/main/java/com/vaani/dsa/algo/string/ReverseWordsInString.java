package com.vaani.dsa.algo.string;

import com.vaani.dsa.ds.utils.generic.ArrayUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.vaani.dsa.ds.utils.generic.ArrayUtils.reverse;
import static com.vaani.dsa.ds.utils.generic.ArrayUtils.reverseBetweenRange;

/** https://leetcode.com/problems/reverse-words-in-a-string/
 * Given an input string, reverse the string word by word.
 * <p>
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 *  "How are you?" -> "you? are How"
 * <p>
 * Input: "  hello world!  ", Output: "world! hello"
 *Input: ""a good   example", "example good a"
 *
 * click to show clarification.
 * <p>
 * Clarification:
 * <p>
 * What constitutes a word?
 * A sequence of non-space characters constitutes a word.
 * Could the input string contain leading or trailing spaces?
 * Yes. However, your reversed string should not contain leading or trailing spaces.
 * How about multiple spaces between two words?
 * Reduce them to a single space in the reversed string.
 *
 *
 * <p>
 *
 */
public class ReverseWordsInString {
    public static void main(String[] args) {
        String s = "the sky is blue";
        ReverseWordsInString test = new ReverseWordsInString();
        System.out.println(test.reverseWords2(s));
    }

    public String reverseWords(String s) {
        s = s.trim();
        if (s.isEmpty()) return s;

        StringBuilder result = new StringBuilder();
        int index = s.length() - 1;
        while (index >= 0) {
            int end = index;
            while (index >= 0 && s.charAt(index) != ' ') {
                index--;
            }

            result.append(s.substring(index + 1, end + 1));

            if (index >= 0) {
                result.append(" ");
            }
            while (index >= 0 && s.charAt(index) == ' ') {
                index--;
            }
        }
        return result.toString();
    }


    // will not work for cases with more than 1 space between wrods
    public String reverseWords2(String s) {
        s = s.trim();

        char[] arr = s.toCharArray();

        // reverse whole string
        // uncomment if you want to use streams
//        IntStream.range(0,s.length()).map(i -> arr[s.length()-i-1])
//                .toArray();
        reverse(arr);

        // reverse in each word
        int start = 0;
        int end = 0;
        while (true) {
            while (end < arr.length && arr[end] != ' ') {
                ++end;
            }
            int nextStart = end + 1;
            // reverse the chars in a word
            --end;
            reverseBetweenRange(arr, start, end);

            if (nextStart >= arr.length) {
                break;
            }
            start = nextStart;
            end = nextStart;
        }

        // Handle extra spaces

        return new String(arr);
    }

    // clean and simple
    public String reverseWords3(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        // split to words by space
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; --i) {
            if (!arr[i].equals("")) {
                sb.append(arr[i]).append(" ");
            }
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
}

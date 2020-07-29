package com.vaani.dsa.algo.ds.string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;


/**
 * https://leetcode.com/problems/reverse-string/
 * 344. Reverse String
 * Easy
 * <p>
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * You may assume all the characters consist of printable ascii characters.
 */
public class ReverseString {
    private String input;
    private String output = "";

    public ReverseString(String in) {
        input = in;
    }

    public static String getString() throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static void main(String[] args) throws Exception {
        String input, output;
        while (true) {
            System.out.println("Enter a String:");
            System.out.flush();
            input = ReverseString.getString();
            if (input.equals("")) {
                break;
            }
            ReverseString reverse = new ReverseString(input);
            output = reverse.doRev();
            System.out.println("Reversed:" + output);

        }

    }

    public String doRev() {
        int stackSize = input.length();
        Stack<Character> theStack = new Stack<>();
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            theStack.push(ch);
        }
        while (!theStack.isEmpty()) {
            char ch = theStack.pop();
            output = output + ch;
        }
        return output;
    }

    public void reverseString(char[] s) {
        int n = s.length;
        for (int i = 0; i < n / 2; i++) {
            char c = s[i];
            s[i] = s[n - i - 1];
            s[n - i - 1] = c;
        }
    }

    /*
     * As string is immutable in java, we use char[] instead.
     * O(1) space, O(n) time.
     */
    public void reverseString2(char[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            char tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            ++start;
            --end;
        }
    }
}

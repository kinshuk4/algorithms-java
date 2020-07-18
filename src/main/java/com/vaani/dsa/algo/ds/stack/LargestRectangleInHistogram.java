package com.vaani.dsa.algo.ds.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 */


public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        LargestRectangleInHistogram test = new LargestRectangleInHistogram();
        int[] height = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(height));

        System.out.println(largestRectangleArea(new int[]{1, 2, 3, 4, 5}));
    }


    public static int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int[] heights = new int[height.length + 1];

        for (int i = 0; i <= height.length; i++) {
            if (i == height.length) heights[i] = 0;
            else {
                heights[i] = height[i];
            }
        }
        for (int i = 0; i < heights.length; ) {
            if (stack.isEmpty() || heights[i] > heights[stack.peek()]) {
                stack.push(i++);
            } else {
                int index = stack.pop();
                result = Math.max(result, heights[index] * (stack.isEmpty() ? i : (i - stack.peek() - 1)));
            }
        }
        return result;
    }

    public static int largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int len = heights.length;
        for (int i = 0; i < heights.length; ) {
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                result = Math.max(result, calculateArea(heights, stack, i));
            }
        }
        while (!stack.isEmpty()) {
            result = Math.max(result, calculateArea(heights, stack, len));
        }
        return result;
    }

    private static int calculateArea(int[] height, Stack<Integer> stack, int i) {
        int index = stack.pop();
        int h = height[index];
        int w = stack.isEmpty() ? i : i - stack.peek() - 1;
        return h * w;
    }
}

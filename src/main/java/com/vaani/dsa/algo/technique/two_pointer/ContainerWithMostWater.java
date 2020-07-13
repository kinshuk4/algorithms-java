package com.vaani.dsa.algo.technique.two_pointer;


/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/
public class ContainerWithMostWater {
    //O(N)
    public int maxAreaTwoPointer(int[] height) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int max = 0;
        int n = height.length;

        int left = 0;
        int right = n - 1;
        while (left < right) {
            int leftHeight = height[left];
            int rightHeight = height[right];

            int minHeight = Math.min(rightHeight, leftHeight);
            max = Math.max(max, minHeight * (right - left));
            if (leftHeight < rightHeight) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    //O(N2) exceeds time limit
    public int maxAreaBruteForce(int[] height) {
        int n = height.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            int leftHeight = height[i];
            for (int j = i + 1; j < n; j++) {
                int rightHeight = height[j];
                int width = j - i;
                int area = width * Math.min(rightHeight, leftHeight);
                max = Math.max(max, area);
            }
        }
        return max;
    }


}
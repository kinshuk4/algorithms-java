package com.vaani.dsa.algo.technique.two_pointer;

/* https://leetcode.com/problems/sort-colors/submissions/
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
*/

import static com.vaani.dsa.ds.utils.generic.ArrayUtils.swap;

public class SortColors {
    // checking for all the variable - left, right and middle
    // not middle <= right and not just middle < right
    // similar to partition logic
    // should work but not workig
    public void sortColors1(int[] A) {
        int left = 0;
        int right = A.length - 1;
        int middle = 0;
        while (middle <= right) {
            if (A[middle] == 0) {
                swap(A, middle, left);
                left++;
                middle++;
            }
            if (A[middle] == 1) {
                middle++;
            }
            // note we are not incrementing middle here
            if (A[middle] == 2) {
                swap(A, middle, right);
                right--;
            }
        }
    }

    // Skip checking for middle and extract out middle++
    public void sortColors2(int[] A) {
        int left = 0;
        int right = A.length - 1;
        int middle = 0;
        while (middle <= right) {
            if (A[middle] == 0) {
                swap(A, middle, left);
                left++;

            }else if (A[middle] == 2) {
                swap(A, middle, right);
                right--;
                middle--; // as middle shouldn't increase
            }
            middle++;
        }
    }

    // similar to above but with for loop
    public void sortColors3(int[] A) {
        int i = -1;
        int j = A.length;

        for (int cur = 0; cur < j; cur++) {
            if (A[cur] == 0) {
                i++;
                swap(A, cur, i);

            } else if (A[cur] == 2) {
                j--;
                swap(A, cur, j);
                cur--;
            }
        }
    }
}

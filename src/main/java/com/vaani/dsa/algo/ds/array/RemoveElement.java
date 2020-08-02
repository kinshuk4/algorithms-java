package com.vaani.dsa.algo.ds.array;
import static com.vaani.dsa.ds.utils.generic.ArrayUtils.swap;

/**
 * https://leetcode.com/problems/remove-element/
 * 27. Remove Element
 * Easy
 * <p>
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * <p>
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * <p>
 * Example 1:
 * <p>
 * Given nums = [3,2,2,3], val = 3,
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 2.
 * <p>
 * It doesn't matter what you leave beyond the returned length.
 * <p>
 * Example 2:
 * <p>
 * Given nums = [0,1,2,2,3,0,4,2], val = 2,
 * <p>
 * Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.
 * <p>
 * Note that the order of those five elements can be arbitrary.
 * <p>
 * It doesn't matter what values are set beyond the returned length.
 * <p>
 * Clarification:
 * <p>
 * Confused why the returned value is an integer but your answer is an array?
 * <p>
 * Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
 * <p>
 * Internally you can think of this:
 * <p>
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeElement(nums, val);
 * <p>
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * <p>
 * <p>
 * https://www.youtube.com/watch?v=sRKByfozeEg
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] A = {2};
        int len = removeElement(A, 2);
        for (int i = 0; i < len; i++) {
            System.out.print(A[i] + " ");
        }
    }

    public static int removeElement2(int[] A, int elem) {
        int index = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != elem) {
                A[index] = A[i];
                index++;
            }
        }
        return index;
    }

    public static int removeElement(int[] A, int elem) {
        if (A.length == 0) return 0;
        int len = -1;
        for (int i = 0; i < A.length; i++) {
            int index = i;
            while (index < A.length && A[index] == elem) index++;
            if (index == A.length) return len + 1;
            if (index != i) swap(A, i, index);
            len = i;
        }
        return len == -1 ? 0 : len + 1;
    }


    public int removeElement3(int[] A, int elem) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (A.length == 0) {
            return 0;
        }
        int readSeq;
        int writeSeq = 0;
        for (readSeq = 0; readSeq < A.length; readSeq++) {
            if (A[readSeq] != elem) {
                A[writeSeq] = A[readSeq];
                writeSeq++;
            }
        }
        return writeSeq;
    }


}

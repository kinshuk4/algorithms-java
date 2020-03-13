package com.vaani.dsa.algo.array;

import java.util.Arrays;

/**
 * Given an array A[0, n - 1], generate another array B such that
 * B[i] = A[0] * ... * A[i - 1] *  A[i + 1] * ... * A[n - 1].
 */
public class ArrayProductExceptSelf {

    /*
    Algorithm:
        1) Construct a temporary array left[] such that left[i] contains product of all elements on left of arr[i] excluding arr[i].
        2) Construct another temporary array right[] such that right[i] contains product of all elements on on right of arr[i] excluding arr[i].
        3) To get prod[], multiply left[] and right[].
     */
    //Time Complexity: O(n)
    //Space Complexity: O(n) - 2 aux arrays left and right
    static int[] productArray1(int A[]) {
        // Base case
        if (A.length == 1) {
            return A;
        }

        // Initialize memory to all arrays
        int[] left = new int[A.length];
        int[] right = new int[A.length];
        int[] prod = new int[A.length];

        int i, j;

        /* Left most element of left array is always 1 */
        left[0] = 1;
        /* Construct the left array */
        for (i = 1; i < A.length; i++) {
            left[i] = A[i - 1] * left[i - 1];
        }


        /* Rightmost most element of right array is always 1 */
        right[A.length - 1] = 1;
        /* Construct the right array */
        for (j = A.length - 2; j >= 0; j--) {
            right[j] = A[j + 1] * right[j + 1];
        }

        /* Construct the product array using
        left[] and right[] */
        for (i = 0; i < A.length; i++) {
            prod[i] = left[i] * right[i];
        }

        /* print the constructed prod array */
        for (i = 0; i < A.length; i++) {
            System.out.print(prod[i] + " ");
        }

        return prod;
    }


    // O(n) space, O(n) time without division using single aux array
    public static int[] productArray2(int[] A) {
        int[] aux = new int[A.length];


        aux[0] = 1;
        for (int i = 1; i < A.length; ++i) {
            aux[i] = aux[i - 1] * A[i - 1];
        }

        int[] B = new int[A.length];
        B[A.length - 1] = 1;
        for (int i = A.length - 2; i >= 0; --i) {
            B[i] = B[i + 1] * A[i + 1];
            B[i + 1] *= aux[i + 1];
        }

        return B;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    //Auxiliary Space: O(1)
    static int[] productArray3(int A[]) {

        // Base case
        if (A.length == 1) {
            return A;
        }



        /* Allocate memory for the product array */
        int[] prod = new int[A.length];

        /* Initialize the product array as 1 */
        for (int j = 0; j < A.length; j++) {
            prod[j] = 1;
        }

        int temp = 1;
        /* In this loop, temp variable contains product of
           elements on left side excluding arr[i] */
        for (int i = 0; i < A.length; i++) {
            prod[i] = temp;
            temp *= A[i];
        }

        /* Initialize temp to 1 for product on right side */
        temp = 1;

        /* In this loop, temp variable contains product of
           elements on right side excluding arr[i] */
        for (int i = A.length - 1; i >= 0; i--) {
            prod[i] *= temp;
            temp *= A[i];
        }

        return prod;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};

        int[] out = productArray3(arr);
        System.out.println(Arrays.toString(out));
    }

}

package com.vaani.dsa.algo.paradigm.dnc;

import java.util.Arrays;

public class CountInversions {
    static int countInversions(int[] a) {
        return countInversions(a, 0, a.length - 1);
    }

    static int countInversions(int[] arr, int start, int end) {
        int count = 0;

        if (start < end) {
            int m = (start + end) / 2;

            // Total inversion count = left subarray count
            // + right subarray count + merge count

            // Left subarray count
            count += countInversions(arr, start, m);

            // Right subarray count
            count += countInversions(arr, m + 1, end);

            // Merge count
            count += countSkipInversions(arr, start, m, end);
        }

        return count;
    }

    private static int countSkipInversions(int[] arr, int l, int m, int r) {
        // Left subarray
        int[] left = Arrays.copyOfRange(arr, l, m + 1);

        // Right subarray
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l, swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }

        // Fill from the rest of the left subarray
        while (i < left.length)
            arr[k++] = left[i++];

        // Fill from the rest of the right subarray
        while (j < right.length)
            arr[k++] = right[j++];

        return swaps;
    }
}

package com.vaani.dsa.algo.ds.array.sort;

import static com.vaani.dsa.ds.utils.generic.ArrayUtils.swap;

/**
 * https://www.baeldung.com/java-sorting-arrays-with-repeated-entries
 */
public class ThreeWayQuicksort {
    static class Tuple<X, Y> {
        public final X x;
        public final Y y;

        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int compare(int num1, int num2) {
        if (num1 > num2)
            return 1;
        else if (num1 < num2)
            return -1;
        else
            return 0;
    }

    public static Tuple<Integer, Integer> partition(int[] input, int begin, int end) {
        int lt = begin, current = begin, gt = end;
        int partitioningValue = input[begin];
        while (current <= gt) {
            int compareCurrent = compare(input[current], partitioningValue);
            switch (compareCurrent) {
                case -1:
                    swap(input, current++, lt++);
                    break;
                case 0:
                    current++;
                    break;
                case 1:
                    swap(input, current, gt--);
                    break;
            }
        }
        return new Tuple<>(lt, gt);
    }

    public static void quicksort(int[] input, int begin, int end) {
        if (end <= begin)
            return;

        Tuple<Integer, Integer> middlePartition = partition(input, begin, end);

        quicksort(input, begin, middlePartition.x - 1);
        quicksort(input, middlePartition.y + 1, end);
    }

    public static void main(String[] args) {

    }

    public void quicksort1(int[] input, int left, int right) {

        if (right <= left) return;

        int i, j;

        // Note that i and j are passed as reference
        Tuple<Integer, Integer> t = partition1(input, left, right);

        // Recur
        quicksort1(input, left, t.y);
        quicksort1(input, t.x, right);
    }

    /* This function partitions a[] in three parts
       a) a[l..i] contains all elements smaller than pivot
       b) a[i+1..j-1] contains all occurrences of pivot
       c) a[j..r] contains all elements greater than pivot */
    static Tuple<Integer, Integer> partition1(int a[], int left, int right) {
        int l = left - 1, r = right; // l and r are 2 pointers simlar to normal quicksort
        int m = left - 1, q = right;

        int p = a[right]; // pivot

        while (true) {
            // From left, find the first element greater than
            // or equal to v. This loop will definitely terminate
            // as v is last element
            while (a[l] < p) {
                l++;
            }

            // From right, find the first element smaller than or equal to v
            while (p < a[r]) {
                r--;
                if (r == left)
                    break;
            }

            // If i and j cross, then we are done
            if (l >= r) break;

            // Swap, so that smaller goes on left greater goes on right
            swap(a, l, r);

            // Move all same left occurrence of pivot to beginning of
            // array and keep count using p
            if (a[l] == p) {
                m++;
                swap(a, m, l);
            }

            // Move all same right occurrence of pivot to end of array
            // and keep count using q
            if (a[r] == p) {
                q--;
                swap(a, r, q);
            }
        }

        // Move pivot element to its correct index
        swap(a, l, right);

        // Move all left same occurrences from beginning
        // to adjacent to arr[i]
        r = l - 1;
        for (int k = left; k < m; k++, r--)
            swap(a, k, r);

        // Move all right same occurrences from end
        // to adjacent to arr[i]
        l = l + 1;
        for (int k = right - 1; k > q; k--, l++)
            swap(a, l, k);

        return new Tuple<>(l, r);
    }


}

package com.vaani.dsa.algo.array.sorted;

public class SortedSquares {
    public int[] sortedSquares(int[] A) {
        int i = 0;
        int j = A.length - 1;

        int[] result = new int[A.length];

        int count = A.length - 1;
        while(count >= 0) {
            if(Math.abs(A[i]) > Math.abs(A[j])) {
                result[count] = A[i]*A[i];
                i++;
            }
            else {
                result[count] = A[j]*A[j];
                j--;
            }
            count--;
        }

        return result;
    }
}

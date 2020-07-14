package com.vaani.dsa.ds.utils.generic;


import java.util.Arrays;
import java.util.List;

public class ArrayUtils {
    public static void reverseBetweenRange(int[] array, int start, int end) {
        while (start < end) {
            swap(array, start++, end--);
        }
    }

    public static void reverse(int[] array) {
        reverseBetweenRange(array, 0, array.length - 1);
    }

    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }


    public static <T> void swap(T[] A, int i, int j) {
        T tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static Character[] toObjectArray(char[] list) {
        Character[] charArr = new Character[list.length];
        for (int i = 0; i < list.length; i++) {
            charArr[i] = list[i];
        }
        return charArr;
    }

    public static int[] toPrimitiveArray(Integer[] list) {
        int[] arr = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            arr[i] = list[i];
        }
        return arr;
    }

    public static int[] listToPrimitiveArray(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static int sum(int[] array) {
        return Arrays.stream(array).sum();
    }

    /* Function to find index of value in arr[start...end], the function assumes that value is present in in[] */
    public static int linearSearch(int[] arr, int value, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}

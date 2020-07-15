package com.vaani.dsa.algo.misc;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 */

public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] a = {2, 3, 2, 2, 2, 2, 2, 2, 2, 2};

        System.out.println(rotatedSearchIterative1(a, 3));
        System.out.println(rotatedSearchRecursive(a, 0, a.length - 1, 3));
        System.out.println(rotatedSearchIterative2(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));

    }

    // submitted
    public static int rotatedSearchIterative1(int[] arr, int target) {
        int start = 0, end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (target < arr[mid]) {
                if (arr[mid] < arr[end]) {// the second half is sorted
                    end = mid - 1;
                } else {                // the lower is ordered
                    if (target < arr[start]) {   // the higher is not ordered, x is less than all lowers.
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }
            } else if (target > arr[mid]) {
                if (arr[start] < arr[mid]) { //the lower is ordered
                    start = mid + 1;
                } else {
                    if (target > arr[end]) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                }
            }
        }
        return -1;

    }

    // submitted- didnt pass
    public static int rotatedSearchIterative2(int[] arr, int target) {
        int start = 0, end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > arr[start]) { // left part is sorted
                if (target < arr[mid] && target > arr[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else { // right side is sorted

                if (target > arr[mid] && target < arr[start]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }

            }
        }
        return -1;

    }

    public static int rotatedSearchRecursive(int a[], int left, int right, int x) {
        int mid = (left + right) / 2;
        if (x == a[mid]) { // Found element
            return mid;
        }
        if (right < left || right < 0 || left >= a.length || a.length == 0) {
            return -1;
        }

        /* While there may be an inflection point due to the rotation, either the left or
         * right half must be normally ordered.  We can look at the normally ordered half
         * to make a determination as to which half we should search.
         */
        if (a[left] < a[mid]) { // Left is normally ordered.
            if (x >= a[left] && x <= a[mid]) {
                return rotatedSearchRecursive(a, left, mid - 1, x);
            } else {
                return rotatedSearchRecursive(a, mid + 1, right, x);
            }
        } else if (a[mid] < a[left]) { // Right is normally ordered.
            if (x >= a[mid] && x <= a[right]) {
                return rotatedSearchRecursive(a, mid + 1, right, x);
            } else {
                return rotatedSearchRecursive(a, left, mid - 1, x);
            }
        } else if (a[left] == a[mid]) { // Left is either all repeats OR loops around (with the right half being all dups)
            if (a[mid] != a[right]) { // If right half is different, search there
                return rotatedSearchRecursive(a, mid + 1, right, x);
            } else { // Else, we have to search both halves
                int result = rotatedSearchRecursive(a, left, mid - 1, x);
                if (result == -1) {
                    return rotatedSearchRecursive(a, mid + 1, right, x);
                } else {
                    return result;
                }
            }
        }
        return -1;
    }
}

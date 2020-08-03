package com.vaani.dsa.algo.search.binary;

/*
https://www.geeksforgeeks.org/find-first-and-last-positions-of-an-element-in-a-sorted-array/
 */
public class BinarySearchFirstIndex {

    public static int searchFirst(int[] nums, int target) {
        return searchFirstRecursive(nums, target, 0, nums.length - 1);
    }

    private static int searchFirstRecursive(int[] arr, int target, int low, int high) {
        if (high >= low) {
            int mid = low + (high - low) / 2;
            if ((mid == 0 || target > arr[mid - 1]) && arr[mid] == target) {
                return mid;
            } else if (target > arr[mid]) {
                return searchFirstRecursive(arr, target, (mid + 1), high);
            } else {
                return searchFirstRecursive(arr, target, low, (mid - 1));
            }
        }
        return -1;
    }

    public static int searchFirstIterative(int[] arr, int target) {
        int index = -1;
        int start = 0;
        int end = arr.length - 1;

        while(start<=end){
            int mid = start  + (end - start) / 2;
            // note we are not just doing > , but also = i.e. >=
            if(arr[mid] >= target){
                end  = mid - 1;
            }else {
                start = mid + 1;
            }

            if(arr[mid] == target){
                index = mid;
            }
        }

        return index;
    }


}

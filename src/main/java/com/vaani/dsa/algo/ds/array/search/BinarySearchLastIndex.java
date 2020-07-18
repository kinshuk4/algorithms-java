package com.vaani.dsa.algo.ds.array.search;

public class BinarySearchLastIndex {

    public static int searchLast(int[] nums, int target) {
        return searchLastRecursive(nums, target, 0, nums.length - 1);
    }

    private static int searchLastRecursive(int[] arr, int target, int low, int high) {
        if (high >= low) {
            int mid = low + (high - low) / 2;
            if ((mid == arr.length - 1 || target < arr[mid + 1]) && arr[mid] == target) {
                return mid;
            } else if (target < arr[mid]) {
                return searchLastRecursive(arr, target, low, (mid - 1));
            } else {
                return searchLastRecursive(arr, target, (mid + 1), high);
            }
        }
        return -1;
    }


    public static int searchLastIterative(int[] arr, int target) {
        int index = -1;
        int start = 0;
        int end = arr.length - 1;

        while(start<=end){
            int mid = start  + (end - start) / 2;

            if(arr[mid] <= target){
                start  = mid + 1;
            }else {
                end = mid - 1;
            }

            if(arr[mid] == target){
                index = mid;
            }
        }

        return index;
    }
}

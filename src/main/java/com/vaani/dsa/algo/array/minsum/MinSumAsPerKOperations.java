package com.vaani.dsa.algo.array.minsum;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class MinSumAsPerKOperations {
    static int minSum(int arr[], int n, int x) {
        int sum = 0;

        // To store the largest element
        // from the array which is
        // divisible by x
        int largestDivisible = -1,
                minimum = arr[0];
        for (int i = 0; i < n; i++) {

            // Sum of array elements before
            // performing any operation
            sum += arr[i];

            // If current element is divisible
            // by x and it is maximum so far
            if (arr[i] % x == 0 &&
                    largestDivisible < arr[i])
                largestDivisible = arr[i];

            // Update the minimum element
            if (arr[i] < minimum)
                minimum = arr[i];
        }

        // If no element can be reduced then
        // there's no point in performing the
        // operation as we will end up increasing
        // the sum when an element is multiplied by x
        if (largestDivisible == -1)
            return sum;

        // Subtract the chosen elements from the
        // sum and then add their updated values
        int sumAfterOperation = sum - minimum - largestDivisible +
                (largestDivisible / x);

        // Return the minimized sum
        return Math.min(sum, sumAfterOperation);
    }


    public static int minSum2(List<Integer> list, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((Integer o1, Integer o2) -> (-Integer.compare(o1, o2)));
        for (Integer i : list) {
            maxHeap.add(i);
        }
        for (int i = 0; i < k; i++) {
            int maxValue = maxHeap.poll();

            int newValue = (int) Math.round(Math.ceil((double) maxValue / 2));

            maxHeap.add(newValue);
        }

        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += maxHeap.poll();
        }

        return sum;
    }

    static int next(List<Integer> arr, int target)
    {
        int start = 0;

        int ans = -1;
        int end = arr.size();
        while (start <= end)
        {
            int mid = (start + end) / 2;

            // Move to the left side if the target is smaller
            if (arr.get(mid) >= target)
            {
                end = mid - 1;
            }

            // Move right side
            else
            {
                ans = mid;
                start = mid + 1;
            }
        }
        return ans;
    }


    public static int webReq(List<Integer> timestamp, List<Integer> top) {
        int result = 0;

        for(int t : top) {
            int queVal = Arrays.binarySearch(timestamp.toArray(), t);
            if(queVal < 0) {
                queVal = next(timestamp, t);
            }


            for (int i = queVal; i >= 0 ; i--){
                timestamp.remove(i);
                result ++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Integer[] arr = {10, 20, 7};
        int k = 4;


//        System.out.println(minSum2(Arrays.asList(arr), 4));

        List<Integer> timeStamp  = new LinkedList<>(Arrays.asList(1, 2, 2, 3, 4, 5,6, 6, 13, 16));
        List<Integer> top = new LinkedList<>(Arrays.asList(10, 15));

        System.out.println(webReq(timeStamp, top));

    }
}

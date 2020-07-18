package com.vaani.dsa.algo.ds.array.sorted.twoarrays;

/* https://leetcode.com/problems/median-of-two-sorted-arrays/
There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

nums1 = [1, 3]
nums2 = [2]

The median is 2.0

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

*/

//it is frustrating to manipulate arrays in Java.

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return helper(nums1, 0, nums1.length, nums2, 0, nums2.length);
    }

    public double helper(int a[], int aStart, int aEnd, int b[], int bStart, int bEnd) {
        if (aEnd == 0) {
            return findMedianSingleArray(b, bStart, bEnd);
        } else if (bEnd == 0) {
            return findMedianSingleArray(a, aStart, aEnd);
        } else if (aEnd - aStart == 1) {
            return findMedianBaseCase(a[aStart], b, bStart, bEnd);
        } else if (bEnd - bStart == 1) {
            return findMedianBaseCase(b[bStart], a, aStart, aEnd);
        } else if (aEnd - aStart == 2) {
            return findMedianBaseCase2(a[aStart], a[aStart + 1], b, bStart, bEnd);
        } else if (bEnd - bStart == 2) {
            return findMedianBaseCase2(b[bStart], b[bStart + 1], a, aStart, aEnd);
        }

        int aMid = (aStart + aEnd) / 2;
        int bMid = (bStart + bEnd) / 2;
        //the number of numbers discarded in each list each time
        int k;

        if (a[aMid] <= b[bMid]) {
            //keep the neighbour when the size of the list that has a smaller median is even
            if ((aEnd - aStart) % 2 == 0) {
                k = Math.min(aMid - 1 - aStart, bEnd - bMid - 1);
            } else {
                k = Math.min(aMid - aStart, bEnd - bMid - 1);
            }
            return helper(a, aStart + k, aEnd, b, bStart, bEnd - k);
        } else {
            if ((bEnd - bStart) % 2 == 0) {
                k = Math.min(bMid - 1 - bStart, aEnd - aMid - 1);
            } else {
                k = Math.min(bMid - bStart, aEnd - aMid - 1);
            }
            return helper(a, aStart, aEnd - k, b, bStart + k, bEnd);
        }
    }

    public double findMedianSingleArray(int A[], int start, int end) {
        int mid = (start + end) / 2;
        if ((end - start) % 2 == 0) {
            return (A[mid] + A[mid - 1]) / 2.0;
        } else {
            return A[mid];
        }
    }

    public double findMedianBaseCase(int med, int A[], int start, int end) {
        int length = end - start;
        if (length == 1) {
            return (med + A[0]) / 2.0;
        }
        if (length % 2 == 0) {
            int a = A[length / 2 - 1];
            int b = A[length / 2];
            if (med <= a) {
                return a;
            } else if (med <= b) {
                return med;
            } else {
                return b;
            }
        } else {
            int a = A[length / 2 - 1];
            int b = A[length / 2];
            int c = A[length / 2 + 1];
            if (med <= a) {
                return (a + b) / 2.0;
            } else if (med <= c) {
                return (med + b) / 2.0;
            } else {
                return (b + c) / 2.0;
            }
        }
    }

    public double findMedianBaseCase2(int med1, int med2, int C[], int start, int end) {
        //length >= 2 by default
        int n = end + start;
        int length = end - start;
        if (length % 2 == 0) {
            int a = (((n / 2 - 2) >= 0) ? C[n / 2 - 2] : Integer.MIN_VALUE);
            int b = C[n / 2 - 1], c = C[n / 2];
            int d = (((n / 2 + 1) <= end - 1) ? C[n / 2 + 1] : Integer.MAX_VALUE);
            if (med2 <= b)
                return (b + Math.max(med2, a)) / 2.0;
            else if (med1 <= b)
                return (b + Math.min(med2, c)) / 2.0;
            else if (med1 >= c)
                return (c + Math.min(med1, d)) / 2.0;
            else if (med2 >= c)
                return (c + Math.max(med1, b)) / 2.0;
            else  /* a < med1 <= med2 < b */
                return (med1 + med2) / 2.0;
        } else {
            int a = C[n / 2 - 1], b = C[n / 2], c = C[n / 2 + 1];
            if (med1 >= b)
                return Math.min(med1, c);
            else if (med2 <= b)
                return Math.max(med2, a);
            else  /* med1 < b < med2 */
                return b;
        }
    }

    // Time complexity: O(m+n). Although the running time is similar, it is worse than the required O(log (m+n)).
    public double findMedianSortedArraysIterative(int[] nums1, int[] nums2) {
        int index1 = 0;
        int index2 = 0;
        int med1 = 0;
        int med2 = 0;
        for (int i = 0; i <= (nums1.length + nums2.length) / 2; i++) {
            med1 = med2;
            if (index1 == nums1.length) {
                med2 = nums2[index2];
                index2++;
            } else if (index2 == nums2.length) {
                med2 = nums1[index1];
                index1++;
            } else if (nums1[index1] < nums2[index2]) {
                med2 = nums1[index1];
                index1++;
            } else {
                med2 = nums2[index2];
                index2++;
            }
        }

        // the median is the average of two numbers
        if ((nums1.length + nums2.length) % 2 == 0) {
            return (float) (med1 + med2) / 2;
        }

        return med2;
    }

    //https://www.youtube.com/watch?v=LPFhl65R7ww
    // nums1 should be smaller than nums2
    public double findMedianSortedArraysIterative2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArraysIterative2(nums2, nums1);
        int m = nums1.length;
        int n = nums2.length;
        int low = 0;
        int high = m;
        while (low <= high) {
            int partitionArr1 = (low + high) / 2;
            int partitionArr2 = (m + n + 1) / 2 - partitionArr1;
            int xLeft = partitionArr1 == 0 ? Integer.MIN_VALUE : nums1[partitionArr1 - 1];
            int xRight = partitionArr1 == m ? Integer.MAX_VALUE : nums1[partitionArr1];
            int yLeft = partitionArr2 == 0 ? Integer.MIN_VALUE : nums2[partitionArr2 - 1];
            int yRight = partitionArr2 == n ? Integer.MAX_VALUE : nums2[partitionArr2];
            if (xLeft <= yRight && yLeft <= xRight) {
                if ((m + n) % 2 == 0) {
                    return ((double) Math.max(xLeft, yLeft) + Math.min(xRight, yRight)) / 2;
                } else {
                    return Math.max(xLeft, yLeft);
                }
            } else if (xLeft > yRight) {
                high = partitionArr1 - 1;
            } else {
                low = partitionArr1 + 1;
            }
        }
        return 0;
    }

}

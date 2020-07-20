package com.vaani.dsa.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/majority-element/
 */
//https://algorithms.tutorialhorizon.com/majority-element-boyer-moore-majority-vote-algorithm/
public class MajorityNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 2, 2, 4, 2, 5, 8, 2};
        ArrayList<Integer> a = new ArrayList<>();
        for (int i : nums) {
            a.add(i);
        }
        System.out.println(new MajorityNumber().majorityNumber(a));
    }

    public int boyerMooreMajority(int[] nums) {
        int size = nums.length;
        if (size == 0)
            return -1;

        int majorityElement = nums[0];
        int count = 1;
        for (int i = 1; i < size; i++) {
            if (majorityElement == nums[i]) {
                count++;
            } else if (count == 0) {
                majorityElement = nums[i];
                count = 1;
            } else {
                count--;
            }
        }
        //check if majorityElement is appearing more than n/2 times
        count = 0;
        for (int i = 0; i < size; i++) {
            if (nums[i] == majorityElement) {
                count++;
            }
        }
        if (count > size / 2)
            return majorityElement;
        else
            return -1;
    }

    public int majorityElementUsingSort(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }

        Arrays.sort(nums);

        int prev=nums[0];
        int count=1;
        for(int i=1; i<nums.length; i++){
            if(nums[i] == prev){
                count++;
                if(count > nums.length/2) return nums[i];
            }else{
                count=1;
                prev = nums[i];
            }
        }

        return 0;
    }

    public int majorityNumber(ArrayList<Integer> nums) {
        int candidate1 = 0, candidate2 = 0;
        int count1, count2;
        count1 = count2 = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (candidate1 == nums.get(i)) {
                count1++;
            } else if (candidate2 == nums.get(i)) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = nums.get(i);
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = nums.get(i);
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = count2 = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == candidate1) {
                count1++;
            } else if (nums.get(i) == candidate2) {
                count2++;
            }
        }
        return count1 > count2 ? candidate1 : candidate2;
    }
}

package com.vaani.dsa.algo.paradigm.dp;

import java.util.HashSet;
import java.util.Stack;
/* https://leetcode.com/problems/jump-game/
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
*/


public class JumpGame {
    public static void main(String[] args) {
        int[] A = {2, 2, 1, 0, 4, 0, 0, 0};
        System.out.println(canJump1(A));
    }

    //BFS, can't pass large set, time limit exceeds
    public static boolean canJump1(int[] A) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.

        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        stack.push(0);
        visited.add(0);

        while (!stack.isEmpty()) {
            int index = stack.pop();
            int maxJump = A[index];
            if (index + maxJump >= A.length - 1) {
                return true;
            }
            for (int i = 1; i <= maxJump; i++) {
                if (!visited.contains(index + i)) {
                    visited.add(index + i);
                    stack.push(index + i);
                }
            }
        }

        return false;
    }

    //brilliant solution - use peaks and valley approach
    //use coverage to track how far you can try
    // submitted
    public static boolean canJump2(int[] A) {
        int coverage = 0;
        for (int i = 0; i < A.length; i++) {
            if(coverage < i){ // we cannot recover now, as we already have lost the momentum
                return false;
            }
            coverage = Math.max(coverage, A[i] + i);
            // just to break out of loop earlier
            if(coverage >= A.length-1){
                return true;
            }
        }
        return true;
    }

    // this is similar to above
    public static boolean canJump2x(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            if (i <= max) {
                max = Math.max(i + A[i], max);
                if (max >= A.length - 1) return true;
            }
        }
        return false;
    }
}


class JumpGameSolution2 {



}

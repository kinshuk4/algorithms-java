package com.vaani.dsa.algo.paradigm.dp;

/** https://leetcode.com/problems/jump-game-ii/
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * For example:
 * Given array A = [2,3,1,1,4]
 * <p>
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * <p>
 */
public class JumpGame2 {


    /* Very similar to coverage solution. Just keeping the track of steps now.
     * We use "last" to keep track of the maximum distance that has been reached
     * by using the minimum steps "steps", whereas "curr" is the maximum distance
     * that can be reached by using "ret+1" steps. Thus,
     * curr = max(i+A[i]) where 0 <= i <= last.
     */
    public static int jump(int[] nums) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int lastCoverage = 0;
        int currCoverage = 0;
        int steps = 0;

        for (int i = 0; i < nums.length; i++) {
            // when the last coverage cannot be reached from current point, we update the coverage
            if (i > lastCoverage) {

                lastCoverage = currCoverage;
                steps++;
            }

            if (lastCoverage >= nums.length - 1) {
                break;
            }
            currCoverage = Math.max(currCoverage, nums[i] + i);
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] A = {2, 3, 1, 1, 4};
        System.out.println(jump(A));
    }
}

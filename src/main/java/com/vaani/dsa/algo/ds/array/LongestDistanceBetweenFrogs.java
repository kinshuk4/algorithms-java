package com.vaani.dsa.algo.ds.array;

public class LongestDistanceBetweenFrogs {
    /**
     * There are N blocks, numbered from 0 to N-1, arranged in a row. A couple of frogs were sitting together on one
     * block when they had a terrible quarrel. Now they want to jump away from one another so that the distance between
     * them will be as large as possible. The distance between blocks numbered J and K, where J ≤ K, is computed as
     * K − J + 1. The frogs can only jump up, meaning that they can move from one block to another only if the two
     * blocks are adjacent and the second block is of the same or greater height as the first. What is the longest
     * distance that they can possibly create between each other, if they also chose to sit on the optimal starting
     * block initially?
     * Write a function:
     * <code>class Solution { public int solution(int[] blocks); }</code>
     * <p>
     * that, given an array <i>blocks</i> consisting of N integers denoting the heights of the blocks, returns the longest
     * possible distance that two frogs can make between each other starting from one of the blocks.
     * <p>
     * Examples:
     * 1. Given blocks = [2, 6, 8, 5], the function should return 3. If starting from blocks[0], the first frog can stay
     * where it is and the second frog can jump to blocks[2] (but not to blocks[3]).
     * <img src="https://codility-frontend-prod.s3.amazonaws.com/media/task_static/angry_frogs/static/images/auto/528e5a11d1436f60e03c5295601df025.png"
     * alt="Graphical representation of example 1.">
     * <p>
     * 2. Given blocks = [1, 5, 5, 2, 6], the function should return 4. If starting from blocks[3], the first frog can
     * jump to blocks[1], but not blocks[0], and the second frog can jump to blocks[4].
     * <img class="inline-description-image" src="https://codility-frontend-prod.s3.amazonaws.com/media/task_static/angry_frogs/static/images/auto/3bc1ca1cf8c38f73c43bdd33334645d5.png"
     * alt="Graphical representation of example 2.">
     * <p>
     * 3. Given blocks = [1, 1], the function should return 2. If starting from blocks[1], the first frog can jump to
     * blocks[0] and the second frog can stay where it is. Starting from blocks[0] would result in the same distance.
     * <img class="inline-description-image" src="https://codility-frontend-prod.s3.amazonaws.com/media/task_static/angry_frogs/static/images/auto/f0a735937c259e07cf5cd048afdca3a2.png"
     * alt="Graphical representation of example 3."></p>
     * <p>
     * Write an efficient algorithm for the following assumptions:
     * - N is an integer within the range [2..200,000]
     * - each element of array blocks is an integer within the range [1..1,000,000,000]
     */

    final static boolean TREND_GOING_UP = true;
    final static boolean TREND_GOING_DOWN = !TREND_GOING_UP;

    public int solution(int[] blocks) {

        int lastMaxHeight = 0;
        int maxDistance = 0;
        int currEqualHeightCount = 0;

        boolean prevTrend = TREND_GOING_DOWN;
        boolean currTrend;
        for (int i = 1; i < blocks.length; i++) {
            int heightDiff = blocks[i] - blocks[i - 1];

            currTrend = getCurrentTrend(heightDiff, prevTrend);

            if (heightDiff == 0 && prevTrend == TREND_GOING_UP) {
                currEqualHeightCount++;
            }

            if (prevTrend == currTrend) {
                if (prevTrend == TREND_GOING_UP && heightDiff != 0) {
                    currEqualHeightCount = 0;
                }
            } else if (prevTrend == TREND_GOING_DOWN && currTrend == TREND_GOING_UP) {
                prevTrend = TREND_GOING_UP;
            } else {
                int currDistance = i - lastMaxHeight;
                if (currDistance > maxDistance) {
                    maxDistance = currDistance;
                }
                lastMaxHeight = i - 1 - currEqualHeightCount;
                prevTrend = TREND_GOING_DOWN;
            }
        }
        int lastDistance = blocks.length - lastMaxHeight;
        if (lastDistance > maxDistance) {
            maxDistance = lastDistance;
        }
        return maxDistance;
    }

    private boolean getCurrentTrend(int difference, boolean prevTrend) {
        boolean currTrend;
        if (difference < 0) {
            currTrend = TREND_GOING_DOWN;
        } else if (difference > 0) {
            currTrend = TREND_GOING_UP;
        } else { // 0
            // No change to the trends
            currTrend = prevTrend;
        }
        return currTrend;
    }


//    public int solution(int[] blocks) {
//        final boolean GOING_UP = true;
//        final boolean GOING_DOWN = !GOING_UP;
//        int lastPeak = 0;
//        int maxDistance = 0;
//
//        int sameNumberCount = 0;
//        boolean prevTrend = false;
//        boolean currTrend;
//        for (int i = 1; i < blocks.length; i++) {
//            int difference = blocks[i] - blocks[i - 1];
//
//            if (difference < 0) {
//                currTrend = GOING_DOWN;
//            } else if (difference > 0) {
//                currTrend = GOING_UP;
//            } else { // 0
//                // No change to the trends
//                currTrend = prevTrend;
//                if (prevTrend == GOING_UP) {
//                    sameNumberCount++;
//                }
//            }
//
//            if (prevTrend == currTrend) {
//                if (prevTrend == GOING_UP && difference != 0) {
//                    sameNumberCount = 0;
//                }
//            } else if (prevTrend == GOING_DOWN && currTrend == GOING_UP) {
//                prevTrend = GOING_UP;
//            } else { //currentTrend == GOING_UP && trend == GOING_DOWN
//                int distance = i - lastPeak;
//                if (distance > maxDistance) {
//                    maxDistance = distance;
//                }
//                lastPeak = i - 1 - sameNumberCount;
//                prevTrend = GOING_DOWN;
//            }
//        }
//        int lastDistance = blocks.length - lastPeak;
//        if (lastDistance > maxDistance) {
//            maxDistance = lastDistance;
//        }
//        return maxDistance;
//    }

    public static void main(String[] args) {

        LongestDistanceBetweenFrogs longestDistanceBetweenFrogs = new LongestDistanceBetweenFrogs();
//        System.out.println(longestDistanceBetweenFrogs.solution(new int[]{2,6,8,5}));
        System.out.println(longestDistanceBetweenFrogs.solution(new int[]{6, 30, 14, 14, 6, 10, 7, 29, 21, 1, 9, 6}));
//        System.out.println(longestDistanceBetweenFrogs.solution(new int[]{1, 5, 5, 2, 6}));
//        System.out.println(longestDistanceBetweenFrogs.solution(new int[]{1, 1}));
    }
}

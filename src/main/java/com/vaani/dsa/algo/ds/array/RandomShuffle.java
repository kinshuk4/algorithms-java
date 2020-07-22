package com.vaani.dsa.algo.ds.array;

import java.util.Arrays;
import java.util.Random;

public class RandomShuffle {

    public void shuffle(int[] A) {
        Random rnd = new Random();
        for (int i = 1; i < A.length; ++i) {
            int idx = rnd.nextInt(i); // get random number in range [0, i - 1]
            int tmp = A[i];
            A[i] = A[idx];
            A[idx] = tmp;
        }
    }

    static class ShuffleArray2{
        private final int[] original;
        private int[] shuffle = null;
        final Random rand;

        public ShuffleArray2(int[] nums) {
            this.original = nums;
            shuffle = Arrays.copyOf(nums, nums.length);
            rand = new Random();
        }

        public int[] reset() {
            shuffle = Arrays.copyOf(original, original.length);
            return shuffle;
        }

        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            for (int i = 0; i < shuffle.length; i++) {
                int x = rand.nextInt(shuffle.length - i);
                int idx = x + i;

                int tmp = shuffle[idx];
                shuffle[idx] = shuffle[i];
                shuffle[i] = tmp;
            }

            return shuffle;
        }
    }

}

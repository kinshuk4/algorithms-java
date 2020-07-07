package com.vaani.dsa.algo.array;

//import static org.apache.commons.lang3.ArrayUtils.reverse;
import static com.vaani.dsa.ds.utils.generic.ArrayUtils.reverseBetweenRange;

public class RotateArrayLeft {
    static void rotateArrayLeft(int array[], int rotatePos) {
        int n = array.length;
        rotatePos = rotatePos % n;
        reverseBetweenRange(array, 0, rotatePos - 1);
        reverseBetweenRange(array, rotatePos, n - 1);
        reverseBetweenRange(array, 0, n - 1);
    }
}

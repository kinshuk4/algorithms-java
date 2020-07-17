package com.vaani.dsa.algo.bit_manipulation;

public class NumberOfOneBits {
    // elegant
    public int hammingWeight1Rec(int n) {
        if (n==0) {
            return 0;
        }
        if (n<0) {
            return 32 - hammingWeight1Rec(~(n)) ;
        }
        if (n%2==0) {
            return  hammingWeight1Rec(n/2);
        } else {
            return 1 + hammingWeight1Rec(n/2);
        }

    }
    // submitted
    public int hammingWeight2(int n) {
        int oneCount = 0;
        while (n != 0){
            oneCount++;
            n &= (n - 1);
        }
        return oneCount;
    }
}

package com.vaani.dsa.algo.numerical;

// https://leetcode.com/problems/happy-number/
/*  ----------------------------------------------------------------------------------------------------------------/
/   Problem: A happy number is one where taking each digit in the given integer, and summing their squares will     /
/            result in 1 or continue before.                                                                        /
/                                                                                                                   /
/   Example: 19                                                                                                     /
/            1*1 + 9*9 = 81                                                                                         /
/            8*8 + 1*1 = 64                                                                                         /
/            6*6 + 4*4 = 100                                                                                        /
/            1*1 + 0*0 + 0*0 = 1                                                                                    /
/            return true                                                                                            /
/   ---------------------------------------------------------------------------------------------------------------*/
public class HappyNumber {

    public boolean isHappyRecursive(int n) {
        int output = 0;
        int lastDigit;
        while(n > 9){
            lastDigit = n % 10;
            n = n/10;
            output = output + (lastDigit * lastDigit);
        }
        output = output + (n * n);

        if(output == 1){
            return true;
        }
        else{
            isHappyRecursive(output);
        }
        return false;
    }

    // submitted - elegant
    public boolean isHappyFloydCycleDetection(int n) {
        int slow = n;
        int fast = getNext(n);

        while(fast != 1 && fast != slow) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }

        return fast == 1;
    }
    private int getNext(int n) {
        int ans = 0;
        while(n > 0) {
            int digit = n%10;
            ans += digit*digit;
            n/=10;
        }
        return ans;
    }


}

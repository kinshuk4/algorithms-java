package com.vaani.dsa.algo.paradigm.greedy;

import java.util.Scanner;

/*
The goal in this problem is to find the minimum number of coins needed to change the input value(an integer) into coins with denominations 1, 5, and 10
Input = 2, Output:2 = 1 + 1.

 */
public class MinCoinChange {
    static int[] denoms = {1, 5, 10};
    private static int getChange(int m) {
        //write your code here
        int change = 0;
        int moneyRemaining = m;
        for (int i = denoms.length - 1; i >= 0 ; i--){
            int currDenom = denoms[i];
            int currChange = moneyRemaining / currDenom;
            moneyRemaining = moneyRemaining % currDenom;

            change += currChange ;
            if (moneyRemaining == 0){
                break;
            }
        }

        return change;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

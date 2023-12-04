package com.vaani.dsa.algo.paradigm.greedy;


import java.util.*;

public class FractionalKnapsack {
    static class ItemValue {
        Double cost;
        double wt, val, ind;

        // item value function
        public ItemValue(int wt, int val, int ind) {
            this.wt = wt;
            this.val = val;
            this.ind = ind;
            cost = Double.valueOf(val / wt);
        }

    }

    // function to get maximum value
    private static double getOptimalValue(int capacity, int[] wt, int[] val) {
        ItemValue[] iVal = new ItemValue[wt.length];

        for (int i = 0; i < wt.length; i++) {
            iVal[i] = new ItemValue(wt[i], val[i], i);
        }

        // sorting items by value;
        Arrays.sort(iVal, new Comparator<>() {
            @Override
            public int compare(ItemValue o1, ItemValue o2) {
                return o2.cost.compareTo(o1.cost);
            }
        });

        double totalValue = 0d;

        for (ItemValue i : iVal) {
            int curWt = (int) i.wt;
            int curVal = (int) i.val;

            if (capacity - curWt >= 0) {// this weight can be picked while
                capacity = capacity - curWt;
                totalValue += curVal;

            } else {// item cant be picked whole
                double fraction = ((double) capacity / (double) curWt);
                totalValue += (curVal * fraction);
                capacity = (int) (capacity - (curWt * fraction));
                break;
            }

        }
        return totalValue;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
}

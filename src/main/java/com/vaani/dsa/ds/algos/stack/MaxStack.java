package com.vaani.dsa.ds.algos.stack;

import java.util.Stack;

//https://github.com/shijiebei2009/Algorithms/blob/master/src%2Fmain%2Fjava%2Fcn%2Fcodepub%2Falgorithms%2Fstack%2FMinStack.java
public class MaxStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> maxStack = new Stack<>();

    public void push(int element) {
        stack.push(element);
        if (maxStack.isEmpty() || element > maxStack.peek()) {
            maxStack.push(element);
        } else {
            maxStack.push(maxStack.peek());
        }
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int max() {
        return maxStack.peek();
    }

    public static void main(String[] args) {
        MaxStack ms = new MaxStack();
        int nums[] = new int[]{3, 4, 2, 1, 9};
        for (int i : nums) {
            ms.push(i);
        }


        System.out.println(ms.max());
        System.out.println(ms.pop());
        System.out.println(ms.max());

    }

//    public static void main(String[] args) {
//        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
//        MaxStack maxStack = new MaxStack();
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        for (int i = 0; i < n; i++) {
//            int q = sc.nextInt();
//            int y;
//            switch (q) {
//                case 1:
//                    y = sc.nextInt();
//                    maxStack.push(y);
//                    break;
//                case 2:
//                    maxStack.pop();
//                    break;
//                case 3:
//                    System.out.println(maxStack.max());
//                    break;
//
//            }
//        }
//        sc.close();
//
//    }
}
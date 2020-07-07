package com.vaani.dsa.ds.algos.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/min-stack/
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 */
public class MinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public void push(int element) {
        stack.push(element);
        if (minStack.size() == 0 || element <= minStack.peek()) {
            minStack.push(element);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public int pop() {
        minStack.pop();
        return stack.pop();
    }


    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack ms = new MinStack();
        int nums[] = new int[]{3, 4, 2, 1, 9};
        for (int i : nums) {
            ms.push(i);
        }


        System.out.println(ms.getMin());
        System.out.println(ms.pop());
        System.out.println(ms.getMin());
        System.out.println(ms.top());
    }
}
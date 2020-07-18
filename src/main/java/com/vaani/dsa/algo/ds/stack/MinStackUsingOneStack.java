package com.vaani.dsa.algo.ds.stack;

import java.util.Stack;

public class MinStackUsingOneStack {
    class Node {
        int value, min;

        Node(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }

    private Stack<Node> stack = new Stack<>();

    public void push(int x) {
        Node node;
        if (!stack.isEmpty()) {
            Node top = stack.peek();
            node = new Node(x, Math.min(top.min, x));
        } else {
            node = new Node(x, x);
        }
        stack.push(node);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().value;
    }

    public int getMin() {
        return stack.peek().min;
    }
}

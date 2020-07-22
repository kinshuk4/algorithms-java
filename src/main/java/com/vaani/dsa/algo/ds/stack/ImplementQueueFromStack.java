package com.vaani.dsa.algo.ds.stack;

import java.util.Stack;

/** Implement the following operations of a queue using stacks.

 push(x) -- Push element x to the back of queue.
 pop() -- Removes the element from in front of queue.
 peek() -- Get the front element.
 empty() -- Return whether the queue is empty.

 Example:

 MyQueue queue = new MyQueue();

 queue.push(1);
 queue.push(2);
 queue.peek();  // returns 1
 queue.pop();   // returns 1
 queue.empty(); // returns false

 Notes:

 You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
 Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
 You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).

*/

 public class ImplementQueueFromStack {
    static class MyQueue {
        private final Stack<Integer> inStack;
        private final Stack<Integer> outStack;
        /** Initialize your data structure here. */
        public MyQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            inStack.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            updateOutStack();
            return outStack.pop();
        }

        /** Get the front element. */
        public int peek() {
                updateOutStack();
                return outStack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return outStack.isEmpty() && inStack.isEmpty();
        }

        private void updateOutStack(){
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
        }
    }

    static class StringQueue {
        private final Stack<String> inStack;
        private final Stack<String> outStack;
        /** Initialize your data structure here. */
        public StringQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void enqueue(String x) {
            inStack.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public String dequeue() {
            updateOutStack();
            return outStack.pop();
        }

        /** Get the front element. */
        public String peek() {
            updateOutStack();
            return outStack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return outStack.isEmpty() && inStack.isEmpty();
        }

        private void updateOutStack(){
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
        }
    }



    public static void main(String[] args) {
        StringQueue queue = new StringQueue();
        queue.enqueue("first");
        queue.enqueue("second");
        queue.enqueue("third");
        queue.enqueue("fourth");
        queue.enqueue("fifth");
        System.out.println("1. " + queue.dequeue());
        System.out.println("2. " + queue.dequeue());
        System.out.println("3. " + queue.dequeue());
        System.out.println("4. " + queue.dequeue());
        System.out.println("5. " + queue.dequeue());
    }


}

package com.vaani.dsa.algo.ds.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement the following operations of a stack using queues.
 * <p>
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * <p>
 * Example:
 * <p>
 * MyStack stack = new MyStack();
 * <p>
 * stack.push(1);
 * stack.push(2);
 * stack.top();   // returns 2
 * stack.pop();   // returns 2
 * stack.empty(); // returns false
 * <p>
 * Notes:
 * <p>
 * You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 * Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
 * You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
 */
public class ImplementStackUsingQueues {
    static class MyStackOneQueue {
        Queue<Integer> queue;

        /**
         * Initialize your data structure here.
         */
        public MyStackOneQueue() {
            this.queue = new LinkedList<Integer>();
        }

        // Push element x onto stack.
        public void push(int x) {
            queue.add(x);
        }

        // Removes the element on top of the stack.
        public int pop() {
            if (queue.isEmpty()) {
                throw new NullPointerException("Queue is empty");
            }
            int res = getPopped();
            return res;
        }

        // Get the top element.
        public int top() {
            if (queue.isEmpty()) {
                throw new NullPointerException("Queue is empty");
            }
            int res = getPopped();
            queue.add(res);
            return res;
        }

        private int getPopped() {
            Queue<Integer> temp = new LinkedList<>();
            while (queue.size() > 1) {
                temp.add(queue.poll());
            }
            int res = queue.poll();
            while (!temp.isEmpty()) {
                queue.add(temp.poll());
            }
            return res;
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return queue.isEmpty();
        }
    }

    class MyStackTwoQueue {
        private Queue<Integer> q1;
        private Queue<Integer> q2;
        private int top;

        // Push element x onto stack.
        public void push(int x) {
            q1.add(x);
            top = x;
        }

        /**
         * Initialize your data structure here.
         */
        public MyStackTwoQueue() {
            this.q1 = new LinkedList<Integer>();
            this.q2 = new LinkedList<Integer>();
        }

        // Removes the element on top of the stack.
        public int pop() {
            int res = getPopped();
            return res;
        }

        private int getPopped() {
            while (q1.size() > 1) {
                top = q1.remove();
                q2.add(top);
            }
            int res = q1.remove();
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            return res;
        }

        // Get the top element.
        public int top() {
            int res = getPopped();
            q1.add(res);
            return res;
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return q1.isEmpty();
        }
    }

    static class MyStackOneQueueClean {
        private final Queue<Integer> q = new LinkedList<Integer>();

        // Push element x onto stack.
        public void push(int x) {
            q.add(x);
            int sz = q.size();
            while (sz > 1) { //rotate the queue to make the tail be the head
                q.add(q.poll());
                sz--;
            }
        }

        // Removes the element on top of the stack.
        public int pop() {
            return q.poll();
        }

        // Get the top element.
        public int top() {
            return q.peek();
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return q.isEmpty();
        }
    }
}

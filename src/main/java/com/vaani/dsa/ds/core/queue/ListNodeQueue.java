package com.vaani.dsa.ds.core.queue;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

import java.util.LinkedList;
import java.util.Queue;

public class ListNodeQueue {
    private ListNode rear = null, front = null;
    private int count = 0;

    // Insert at the rear
    public boolean enqueue(int item) {
        ListNode node = new ListNode(item);
        if (front == null) {
            front = node;
            rear = node;
        } else {
            rear.next = node;
            rear = node;
        }

        count += 1;
        return true;
    }

    // delete at the beginning
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        ListNode temp = front;

        front = front.next;

        // if the list becomes empty
        if (front == null) {
            rear = null;
        }
        count -= 1;
        return temp.val;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.val;
    }

    public boolean isEmpty() {
        return count == 0; // We can also do: rear == null && front == null;
    }

    private int size() {
        return count;
    }
}


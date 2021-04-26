package com.vaani.dsa.ds.core.queue;

public class FixedArrayQueueBadRemove {
    private int[] arr;      // array to store queue elements
    private int front;      // front points to the front element in the queue
    private int rear;       // rear points to the last element in the queue
    private int capacity;   // maximum capacity of the queue
    private int count;      // current size of the queue

    // Constructor to initialize a queue
    FixedArrayQueueBadRemove(int size) {
        arr = new int[size];
        capacity = size;
        front = 0;
        rear = -1;
        count = 0;
    }


    public boolean enqueue(int item) {
        // check for queue overflow
        if (isFull()) {
            return false;
        }

        arr[rear++] = item;
        count++;
        return true;
    }


    // Utility function to dequeue the front element
    public int dequeue() {
        // check for queue underflow
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        int temp = arr[front];

        for(int i = 0; i< rear - 1; i++) {
            arr[i] = arr[i + 1];
        }
        // // store 0 at rear indicating there's no element
        arr[rear--] = 0;
        count--;
        return temp;
    }

    // Utility function to return the front element of the queue
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return arr[front];
    }

    // Utility function to return the size of the queue
    public int size() {
        return count;
    }

    // Utility function to check if the queue is empty or not
    public boolean isEmpty() {
        return front == rear;
    }

    // Utility function to check if the queue is full or not
    public boolean isFull() {
        return rear == capacity;
    }


    public static void main(String[] args) {
        // create a queue of capacity 5
        FixedArrayQueueBadRemove q = new FixedArrayQueueBadRemove(5);

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        System.out.println("The front element is " + q.peek());
        q.dequeue();
        System.out.println("The front element is " + q.peek());

        System.out.println("The queue size is " + q.size());

        q.dequeue();
        q.dequeue();

        if (q.isEmpty()) {
            System.out.println("The queue is empty");
        } else {
            System.out.println("The queue is not empty");
        }
    }

}

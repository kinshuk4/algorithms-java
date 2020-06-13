package com.vaani.dsa.ds.core.heap;

import com.vaani.dsa.ds.utils.ArrayUtils;
//http://people.cs.vt.edu/~shaffer/Book/JAVA/progs/Grkrusm/MinHeap.java
public class MinHeap<E extends Comparable<? super E>> {
    private E[] heap;   // Pointer to the heap array
    private int capacity;   // Maximum size of the heap
    private int size;      // Number of things in heap

    public MinHeap(E[] h, int num, int max) {
        heap = h;
        size = num;
        capacity = max;
        buildheap();
    }

    /**
     * Return current size of the heap
     */
    public int heapsize() {
        return size;
    }

    /**
     * Is pos a leaf position?
     */
    public boolean isLeaf(int pos) {
        return (pos >= size / 2) && (pos < size);
    }

    /**
     * Return position for left child of pos
     */
    public int getLeftChildIndex(int pos) {
        assert pos < size / 2 : "Position has no left child";
        return 2 * pos + 1;
    }

    /**
     * Return position for right child of pos
     */
    public int getRightChildIndex(int pos) {
        assert pos < (size - 1) / 2 : "Position has no right child";
        return 2 * pos + 2;
    }

    /**
     * Return position for parent
     */
    public int getParentIndex(int pos) {
        assert pos > 0 : "Position has no parent";
        return (pos - 1) / 2;
    }

    /**
     * Heapify contents of Heap
     */
    public void buildheap() {
        for (int i = size / 2 - 1; i >= 0; i--){
            siftdown(i);
        }
    }

    /**
     * Insert into heap
     */
    public void insert(E val) {
        assert size < capacity : "Heap is full";
        int curr = size++;
        heap[curr] = val;                 // Start at end of heap
        // Now sift up until curr's parent's key > curr's key
        siftUp(curr);
    }

    private void siftUp(int pos) {
        while ((pos != 0) &&
                (heap[pos].compareTo(heap[getParentIndex(pos)]) < 0)) {
            ArrayUtils.swap(heap, pos, getParentIndex(pos));
            pos = getParentIndex(pos);
        }
    }

    /**
     * Put element in its correct place
     */
    private void siftdown(int pos) {
        assert (pos >= 0) && (pos < size) : "Illegal heap position";
        while (!isLeaf(pos)) {
            int j = getLeftChildIndex(pos);
            if ((j < (size - 1)) && (heap[j].compareTo(heap[j + 1]) > 0))
                j++; // j is now index of child with greater value
            if (heap[pos].compareTo(heap[j]) <= 0)
                return;
            ArrayUtils.swap(heap, pos, j);
            pos = j;  // Move down
        }
    }

    public E removemin() {     // Remove minimum value
        assert size > 0 : "Removing from empty heap";
        ArrayUtils.swap(heap, 0, --size); // Swap minimum with last value
        if (size != 0)      // Not on last element
            siftdown(0);   // Put new heap root val in correct place
        return heap[size];
    }

    /**
     * Remove element at specified position
     */
    public E remove(int pos) {
        assert (pos >= 0) && (pos < size) : "Illegal heap position";
        if (pos == (size - 1)) size--; // Last element, no work to be done
        else {
            ArrayUtils.swap(heap, pos, --size); // Swap with last value
            // If we just swapped in a small value, push it up
            while ((pos > 0) && (heap[pos].compareTo(heap[getParentIndex(pos)]) < 0)) {
                ArrayUtils.swap(heap, pos, getParentIndex(pos));
                pos = getParentIndex(pos);
            }
            if (size != 0) siftdown(pos);   // If it is big, push down
        }
        return heap[size];
    }
}

package com.vaani.dsa.ds.design;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianOfDataStreamUsingTwoHeaps {
    private PriorityQueue<Integer> maxHeap;  // contains smaller values
    private PriorityQueue<Integer> minHeap; // contains larger value

    public MedianOfDataStreamUsingTwoHeaps() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
//        maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
//        maxHeap = new PriorityQueue<>((a, b) -> b - 1);
//        maxHeap = new PriorityQueue<>((a, b) -> b.compareTo(a));
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num < maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        rebalanceHeap();
    }

    private void rebalanceHeap() {
        // nthing to rebalance
        if (Math.abs(maxHeap.size() - minHeap.size()) <= 1) {
            return;
        }
        PriorityQueue<Integer> biggerHeap = maxHeap.size() > minHeap.size() ? maxHeap : minHeap;
        PriorityQueue<Integer> smallerHeap = maxHeap.size() > minHeap.size() ? minHeap : maxHeap;
        smallerHeap.add(biggerHeap.poll());
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2;
        }
        PriorityQueue<Integer> biggerHeap = maxHeap.size() > minHeap.size() ? maxHeap : minHeap;

        return biggerHeap.peek();
    }
}

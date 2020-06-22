package com.vaani.dsa.ds.algos.heap;

import java.util.*;

//https://www.hackerrank.com/challenges/jesse-and-cookies/problem
public class JesseAndCookie {
    static int cookies(int k, int[] A) {
        /*
         * Write your code here.
         */
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i : A) {
            queue.add(i);
        }
        int opCount = -1;
        while (queue.peek() < k) {
            opCount++;
            int leastSweetCookie = queue.poll();
            int secondLeastSweetCookie = queue.poll();

            int newCookie = 1 * leastSweetCookie + 2 * secondLeastSweetCookie;
            queue.add(newCookie);
        }
        return opCount;
    }

//import java.io.*;
//import java.math.*;
//import java.text.*;
//import java.util.*;
//import java.util.regex.*;

//    public class Solution {
//
//
//
//
//        static int cookies(int k, Queue<Integer> queue) {
//            /*
//             * Write your code here.
//             */
//            //  Queue<Integer> queue = new PriorityQueue<>();
//            //  for(int i: A){
//            //      queue.add(i);
//            //  }
//            int opCount = 0;
//            while(queue.peek() < k && queue.size()> 1){
//                opCount++;
//                // int leastSweetCookie = queue.poll();
//                // int secondLeastSweetCookie = queue.poll();
//
//                // int newCookie = leastSweetCookie + 2 * secondLeastSweetCookie;
//                // queue.add(newCookie);
//                queue.add(queue.poll()+2*queue.poll());
//            }
//            return (queue.peek() >= k) ? opCount : -1;
//        }
//
//        private static final Scanner scanner = new Scanner(System.in);
//
//        public static void main(String[] args) throws IOException {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//            String[] nk = scanner.nextLine().split(" ");
//
//            int n = Integer.parseInt(nk[0].trim());
//
//            int k = Integer.parseInt(nk[1].trim());
//
//            int[] A = new int[n];
//
//            String[] AItems = scanner.nextLine().split(" ");
//
//            // for (int AItr = 0; AItr < n; AItr++) {
//            //     int AItem = Integer.parseInt(AItems[AItr].trim());
//            //     A[AItr] = AItem;
//            // }
//
//            Queue<Integer> queue = new PriorityQueue<>();
//            for (int AItr = 0; AItr < n; AItr++) {
//                int AItem = Integer.parseInt(AItems[AItr].trim());
//                queue.add(AItem);
//                // A[AItr] = AItem;
//            }
//
//
//            int result = cookies(k, queue);
//
//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
//
//            bufferedWriter.close();
//        }
//    }



//    import java.io.*;
//import java.math.*;
//import java.text.*;
//import java.util.*;
//import java.util.regex.*;
//
//    public class Solution {
//
//        /*
//         * Complete the cookies function below.
//         */
//        static class MinHeap {
//
//            private ArrayList<Integer> list;
//
//            public MinHeap() {
//
//                this.list = new ArrayList<Integer>();
//            }
//
//            public MinHeap(ArrayList<Integer> items) {
//
//                this.list = items;
//                buildHeap();
//            }
//
//            public void insert(int item) {
//
//                list.add(item);
//                int i = list.size() - 1;
//                int parent = parent(i);
//
//                while (parent != i && list.get(i) < list.get(parent)) {
//
//                    swap(i, parent);
//                    i = parent;
//                    parent = parent(i);
//                }
//            }
//
//            public void buildHeap() {
//
//                for (int i = list.size() / 2; i >= 0; i--) {
//                    minHeapify(i);
//                }
//            }
//
//            public int extractMin() {
//
//                if (list.size() == 0) {
//
//                    throw new IllegalStateException("MinHeap is EMPTY");
//                } else if (list.size() == 1) {
//
//                    int min = list.remove(0);
//                    return min;
//                }
//
//                // remove the last item ,and set it as new root
//                int min = list.get(0);
//                int lastItem = list.remove(list.size() - 1);
//                list.set(0, lastItem);
//
//                // bubble-down until heap property is maintained
//                minHeapify(0);
//
//                // return min key
//                return min;
//            }
//
//            public int poll(){
//                return extractMin();
//            }
//
//            public int peek() {
//                return list.get(0);
//            }
//
//            public int size() {
//                return list.size();
//            }
//
//            public void add(int item) {
//                insert(item);
//            }
//
//            public void decreaseKey(int i, int key) {
//
//                if (list.get(i) < key) {
//
//                    throw new IllegalArgumentException("Key is larger than the original key");
//                }
//
//                list.set(i, key);
//                int parent = parent(i);
//
//                // bubble-up until heap property is maintained
//                while (i > 0 && list.get(parent) > list.get(i)) {
//
//                    swap(i, parent);
//                    i = parent;
//                    parent = parent(parent);
//                }
//            }
//
//            private void minHeapify(int i) {
//
//                int left = left(i);
//                int right = right(i);
//                int smallest = -1;
//
//                // find the smallest key between current node and its children.
//                if (left <= list.size() - 1 && list.get(left) < list.get(i)) {
//                    smallest = left;
//                } else {
//                    smallest = i;
//                }
//
//                if (right <= list.size() - 1 && list.get(right) < list.get(smallest)) {
//                    smallest = right;
//                }
//
//                // if the smallest key is not the current key then bubble-down it.
//                if (smallest != i) {
//
//                    swap(i, smallest);
//                    minHeapify(smallest);
//                }
//            }
//
//            public int getMin() {
//
//                return list.get(0);
//            }
//
//            public boolean isEmpty() {
//
//                return list.size() == 0;
//            }
//
//            private int right(int i) {
//
//                return 2 * i + 2;
//            }
//
//            private int left(int i) {
//
//                return 2 * i + 1;
//            }
//
//            private int parent(int i) {
//
//                if (i % 2 == 1) {
//                    return i / 2;
//                }
//
//                return (i - 1) / 2;
//            }
//
//            private void swap(int i, int parent) {
//
//                int temp = list.get(parent);
//                list.set(parent, list.get(i));
//                list.set(i, temp);
//            }
//
//        }
//
//        static int cookies(int k, MinHeap queue) {
//            /*
//             * Write your code here.
//             */
//            //  Queue<Integer> queue = new PriorityQueue<>();
//            //  for(int i: A){
//            //      queue.add(i);
//            //  }
//            int opCount = 0;
//            while(queue.peek() < k && queue.size()> 1){
//                opCount++;
//                int leastSweetCookie = queue.poll();
//                int secondLeastSweetCookie = queue.poll();
//
//                int newCookie = 1* leastSweetCookie + 2 * secondLeastSweetCookie;
//                queue.add(newCookie);
//            }
//            return (queue.peek() >= k) ? opCount : -1;
//        }
//
//        private static final Scanner scanner = new Scanner(System.in);
//
//        public static void main(String[] args) throws IOException {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//            String[] nk = scanner.nextLine().split(" ");
//
//            int n = Integer.parseInt(nk[0].trim());
//
//            int k = Integer.parseInt(nk[1].trim());
//
//            int[] A = new int[n];
//
//            String[] AItems = scanner.nextLine().split(" ");
//
//            // for (int AItr = 0; AItr < n; AItr++) {
//            //     int AItem = Integer.parseInt(AItems[AItr].trim());
//            //     A[AItr] = AItem;
//            // }
//
//            // Queue<Integer> queue = new PriorityQueue<>();
//            // for (String aitemsValue: AItems) {
//            //     int AItem = Integer.valueOf(aitemsValue);
//            //     queue.add(AItem);
//            // }
//
//            ArrayList<Integer> array = new ArrayList<Integer>();
//            for (String aitemsValue: AItems) {
//                int AItem = Integer.valueOf(aitemsValue);
//                array.add(AItem);
//            }
//
//            MinHeap queue = new MinHeap(array);
//
//
//
//            int result = cookies(k, queue);
//
//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
//
//            bufferedWriter.close();
//        }
//    }

}

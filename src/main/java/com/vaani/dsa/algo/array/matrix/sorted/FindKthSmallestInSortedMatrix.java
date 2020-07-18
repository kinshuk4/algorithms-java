package com.vaani.dsa.algo.array.matrix.sorted;

import org.apache.commons.lang3.NotImplementedException;

import java.util.*;
import java.util.PriorityQueue;
import java.util.Set;

/** https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * Given a matrix where each row/column is sorted, find the kth smallest element in matrix.
 * e.g.,
 * 1 6 6
 * 2 7 7
 * 2 8 9
 * k=7
 * <p>
 * Space cost O(n), time cost O(nlogn).
 */

public class FindKthSmallestInSortedMatrix {

    public int findKthPQ(int[][] matrix, int k) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }
        if (k == 0) {
            return matrix[0][0];
        }
        int maxRow = matrix.length;
        int maxCol = matrix[0].length;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        pq.add(new Node(0, matrix[0][0]));
        while (!pq.isEmpty()) {
      /* System.out.println(k); */
            Node cur = pq.poll();
            int row = cur.index / maxCol;
            int col = cur.index % maxCol;
            if (k == 0) {
                return cur.val;
            }
            --k;
            int index = (row + 1) * maxCol + col;
            if (row + 1 < matrix.length && !visited.contains(index)) {
                pq.add(new Node(index, matrix[row + 1][col]));
                visited.add(index);
            }
            index = row * maxCol + col + 1;
            if (col + 1 < matrix[0].length && !visited.contains(index)) {
                pq.add(new Node(index, matrix[row][col + 1]));
                visited.add(index);
            }
        }
        return -1;
    }

    public static class Node implements Comparable<Node> {
        int index;
        int val;

        public Node(int index, int val) {
            this.index = index;
            this.val = val;
        }

        @Override
        public int compareTo(Node other) {
            if (other instanceof Node) {
                Node o = (Node) other;
                return Integer.compare(val, o.val);
            }
            return 0;
        }
    }

    // cleaner
    public int kthSmallestPQ2(int[][] matrix, int k) {
        class Node {            // Inner class for combining row and column into single object
            int row, col;
            Node(int r, int c){
                row = r;
                col = c;
            }
        }

        Queue<Node> minHeap = new PriorityQueue<>( (a, b) -> matrix[a.row][a.col] - matrix[b.row][b.col]);

        int n = matrix[0].length;

        // add the first row
        for(int i = 0; i < n && i < k ; i++){
            minHeap.add( new Node(0, i) );
        }

        while( k > 1){
            Node node = minHeap.poll();
            if( node.row +1 < matrix.length )
                minHeap.add(new Node(node.row +1, node.col));
            k--;
        }

        return matrix[minHeap.peek().row][minHeap.peek().col];

    }

    // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/394294/Using-Binary-Search-in-Java-and-analysis
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        if(k < n){
            return matrix[0][k];
        }

        throw new NotImplementedException("Not yet implemented");


    }
}

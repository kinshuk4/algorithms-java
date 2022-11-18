package com.vaani.dsa.algo.bit_manipulation;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        int[][] mat = {{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 0, 1}};
        int cols = 2;
        Solution sol = new Solution();
        int ans = sol.maximumRows(mat, cols);

    }

    static class ColCnt {
        int idx;
        int count;

        public ColCnt(int idx, int count) {
            this.idx = idx;
            this.count = count;
        }
    }

    public int maximumRows(int[][] mat, int cols) {
        int m = mat.length;
        int n = mat[0].length;
        if (cols >= n) {
            return m;
        }
        // var rowMap = new HashMap<Integer, Integer>();
        var colMap = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    // rowMap.put(i, rowMap.getOrDefault(i) + 1);
                    colMap.putIfAbsent(j, new ArrayList<>());
                    colMap.get(j).add(i);
                }
            }
        }
        PriorityQueue<ColCnt> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.count, a.count));
        for (int colIdx : colMap.keySet()) {
            maxHeap.add(new ColCnt(colIdx, colMap.get(colIdx).size()));
        }
        while (cols > 0 && !maxHeap.isEmpty()) {
            int colIdx = maxHeap.poll().idx;
            List<Integer> rowIdxs = colMap.get(colIdx);
            for (int rowIdx : rowIdxs) {
                mat[rowIdx][colIdx] = 0;
            }
            cols--;
        }
        int notAnsCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    notAnsCount++;
                    break;
                }
            }
        }
        return m - notAnsCount;

    }
}
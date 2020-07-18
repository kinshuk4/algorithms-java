package com.vaani.dsa.algo.ds.array.matrix;


import java.awt.*;
import java.util.*;

public class ShortestPathinMaze {

    static int[] rowNum = {-1, 0, 0, 1};
    static int[] colNum = {0, -1, 1, 0};

    // return true if row number and
    // column number is in range
    static boolean isValid(int row, int col, int ROW, int COL) {
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
    }

    static int bfs(int mat[][], Point src, Point dst) {
        // check source and destination cell
        // of the matrix have value 1
        if (mat[src.x][src.y] != 1 || mat[dst.x][dst.y] != 1) {
            return -1;
        }


        int ROW = mat.length;
        int COL = mat[0].length;

        boolean[][] visited = new boolean[ROW][COL];

        // Mark the source cell as visited
        visited[src.x][src.y] = true;

        Queue<AbstractMap.SimpleEntry<Point, Integer>> queue = new LinkedList<>();

        // Distance of source cell is 0
        AbstractMap.SimpleEntry<Point, Integer> s = new AbstractMap.SimpleEntry<>(src, 0);
        queue.add(s); // Enqueue source cell

        // Do a BFS starting from source cell
        while (!queue.isEmpty()) {
            AbstractMap.SimpleEntry<Point, Integer> curr = queue.peek();

            Point currPoint = curr.getKey();
            int currDist = curr.getValue();

            // If we have reached the destination cell,
            // we are done
            if (currPoint.x == dst.x && currPoint.y == dst.y)
                return currDist;

            // Otherwise dequeue the front cell in the queue
            // and enqueue its adjacent cells
            queue.remove();

            for (int i = 0; i < 4; i++) {
                int row = currPoint.x + rowNum[i];
                int col = currPoint.y + colNum[i];

                // if adjacent cell is valid, has path
                // and not visited yet, enqueue it.
                if (isValid(row, col, ROW, COL) && mat[row][col] == 1 && !visited[row][col]) {
                    // mark cell as visited and enqueue it
                    visited[row][col] = true;
                    AbstractMap.SimpleEntry<Point, Integer> Adjcell = new AbstractMap.SimpleEntry<>(new Point(row, col), currDist + 1);
                    queue.add(Adjcell);
                }
            }
        }

        // Return -1 if destination cannot be reached
        return -1;
    }

}

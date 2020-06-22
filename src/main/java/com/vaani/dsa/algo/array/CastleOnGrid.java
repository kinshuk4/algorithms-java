package com.vaani.dsa.algo.array;


import java.awt.*;
import java.util.*;


public class CastleOnGrid {
    static int[] rowNum = {-1, 0, 0, 1};
    static int[] colNum = {0, -1, 1, 0};

    // return true if row number and
    // column number is in range
    static boolean isValid(int row, int col, int ROW, int COL) {
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT, NOT_DEFINED
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

        Direction prevDirection = Direction.NOT_DEFINED;
        int result = 0;
        // Do a BFS starting from source cell
        while (!queue.isEmpty()) {
            AbstractMap.SimpleEntry<Point, Integer> curr = queue.poll();

            Point currPoint = curr.getKey();
            int currDist = curr.getValue();

            // If we have reached the destination cell,
            // we are done
            if (currPoint.x == dst.x && currPoint.y == dst.y)
                return currDist;

            // Otherwise dequeue the front cell in the queue
            // and enqueue its adjacent cells


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

    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
        int n = grid.length;
        int m = grid[0].length();
        int[][] maze = new int[n][m];

        for (int i = 0; i < n; i++) {
            String temp = grid[i];
            for (int j = 0; j < m; j++) {
                char c = temp.charAt(j);
                if ('X' == c) {
                    maze[i][j] = 0;
                } else {
                    maze[i][j] = 1;
                }

            }
        }

        return bfs(maze, new Point(startX, startY), new Point(goalX, goalY));

    }

    public static void main(String[] args) {
        String[] grid = {".X.", ".X.", "..."};
        System.out.println(minimumMoves(grid, 0, 0, 0, 2));
//        try (Scanner in = new Scanner(System.in);
//             int n = in.nextInt();
//             int m = in.nextInt();
//
//            assert(n >=2&&n<=100) ;
//        assert (m >= 2 && n <= 100);
//
//        char[][] maze = new char[n][m];
//        boolean[][] visited = new boolean[n][m];
//
//        in.nextLine();
//
//
//        out.println(findShortestPath(n, m, maze, visited));
//    } catch(
//    Exception e)
//
//    {
//        e.printStackTrace();
//    }
    }
}

package com.vaani.dsa.algo.technique.depth_first_search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * <p>
 * A region is captured by flipping all 'O's into 'X's in that surrounded region .
 * <p>
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Explanation:
 * <p>
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */

public class SurroundedRegions {
    public void solve(char[][] board) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if (board == null || board.length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            bfs(i, 0, board);
            bfs(i, n - 1, board);
        }

        for (int j = 1; j < n; j++) {
            bfs(0, j, board);
            bfs(m - 1, j, board);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '+') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void bfs(int i, int j, char[][] board) {
        Queue<Integer> q = new LinkedList<Integer>();
        visit(i, j, q, board);

        int rowLength = board.length;
        int columnLength = board[0].length;

        while (!q.isEmpty()) {
            int r = q.poll();
            int col = r % columnLength;
            int row = r / columnLength;

            visit(row, col - 1, q, board);
            visit(row, col + 1, q, board);
            visit(row - 1, col, q, board);
            visit(row + 1, col, q, board);
        }

    }

    public void visit(int i, int j, Queue<Integer> q, char[][] board) {
        int rowLength = board.length;
        int columnLength = board[0].length;
        if (i < 0 || i >= rowLength || j < 0 || j >= columnLength || board[i][j] == 'X')
            return;
        if (board[i][j] == 'O') {
            board[i][j] = '+';
            q.offer(i * columnLength + j);
            return;
        }
    }

    public void solve2DFS(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        // 1 X 1 at maxs
        if (board.length < 2 || board[0].length < 2) {
            return;
        }

        int m = board.length, n = board[0].length;
        // Row boundary DFS
        //Any 'O' connected to a boundary can't be turned to 'X', so ...
        //Start from first and last column, turn 'O' to '*'.
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                boundaryDFS(board, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                boundaryDFS(board, i, n - 1);
            }
        }

        // Column boundary DFS
        //Start from first and last row, turn '0' to '*'
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                boundaryDFS(board, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                boundaryDFS(board, m - 1, j);
            }
        }


        //post-processing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '*')
                    board[i][j] = 'O';
            }
        }
    }

    //Use DFS to turn internal however boundary-connected 'O' to '*';
    private void boundaryDFS(char[][] board, int i, int j) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1)
            return;
        if (board[i][j] == 'O')
            board[i][j] = '*';
        if (i > 1 && board[i - 1][j] == 'O')
            boundaryDFS(board, i - 1, j);
        if (i < board.length - 2 && board[i + 1][j] == 'O')
            boundaryDFS(board, i + 1, j);
        if (j > 1 && board[i][j - 1] == 'O')
            boundaryDFS(board, i, j - 1);
        if (j < board[i].length - 2 && board[i][j + 1] == 'O')
            boundaryDFS(board, i, j + 1);
    }
}

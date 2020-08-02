package com.vaani.dsa.algo.paradigm.backtracking;

/**
 * https://leetcode.com/problems/sudoku-solver/
 * 37. Sudoku Solver
 * Hard
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * <p>
 * Empty cells are indicated by the character '.'.
 * <p>
 * Note:
 * <p>
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 */

public class SudokuSolver {

    // simple and submitted
    static class DFS1 {
        public void solveSudoku(char[][] board) {
            solveSudokuHelper(board);
        }

        public boolean solveSudokuHelper(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') {
                        for (int k = 0; k < 9; k++) {
                            board[i][j] = (char) ('1' + k);
                            if (isValid(board, i, j) && solveSudokuHelper(board)) {
                                return true;
                            }
                            board[i][j] = '.';
                        }
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean isValid(char[][] board, int row, int col) {
            char c = board[row][col];

            //check row
            for (int j = 0; j < 9; j++) {
                if (j == col) {
                    continue;
                }
                if (board[row][j] == c) {
                    return false;
                }
            }

            //check column
            for (int i = 0; i < 9; i++) {
                if (i == row) {
                    continue;
                }
                if (board[i][col] == c) {
                    return false;
                }
            }

            //check square
            int baseRow = row / 3;
            int baseCol = col / 3;
            for (int i = baseRow * 3; i < baseRow * 3 + 3; i++) {
                for (int j = baseCol * 3; j < baseCol * 3 + 3; j++) {
                    if (i == row && j == col) {
                        continue;
                    }
                    if (board[i][j] == c) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    // faster - https://leetcode.com/problems/sudoku-solver/discuss/644044/Java-93-3ms-backtracking-boolean-arrays
    static class UsingBooleanArrays {
        public void solveSudoku(char[][] board) {
            boolean[][] row = new boolean[10][10]; //dictionary to know if there is a number already in the row
            boolean[][] col = new boolean[10][10]; //dictionary to know if a number 0-9 exists in the current column.
            boolean[][] box = new boolean[10][10]; //dictionary to know if a number 0-9 exists in the current 3x3 box.
            //Reason for dictionary above, it is a O{1) operation to know if you can safely insert a number in the current row, if it is not safe.

            for (int i = 0; i < 9; i++) {//fill the dictionaries above with the numbers we got from the board[][] input.
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        row[i][board[i][j] - '0'] = true;
                        col[j][board[i][j] - '0'] = true;
                        int b = (i / 3) * 3 + (j / 3);
                        box[b][board[i][j] - '0'] = true;
                    }
                }
            }
            doDfs(row, col, box, board, 0, 0);
        }

        public boolean doDfs(boolean[][] row, boolean[][] col, boolean[][] box, char[][] board, int x, int y) {
            //If we reached this index in the matrix then we have filled the sudoku successfully.
            if (x == 8 && y == 9) {
                return true;
            } else if (y == 9) {
                y = 0;
                x += 1;
            }
            if (board[x][y] == '.') {
                for (int i = 1; i <= 9; i++) {//try all 9 numbers to be inserted in this current cell while backtracking.
                    if (isValid(row, col, box, i, x, y)) {
                        row[x][i] = true;
                        col[y][i] = true;
                        int b = ((x / 3) * 3) + (y / 3);
                        box[b][i] = true;
                        board[x][y] = (char) (i + '0');
                        if (doDfs(row, col, box, board, x, y + 1)) {
                            return true;
                        }
                        board[x][y] = '.';
                        row[x][i] = false;
                        col[y][i] = false;
                        box[b][i] = false;
                    }
                }
            } else {
                return doDfs(row, col, box, board, x, y + 1);
            }
            return false;
        }

        //Checks if the number i is safe to be inserted in the row/col/3x3 box in O(1) operation.
        public boolean isValid(boolean[][] row, boolean[][] col, boolean[][] box, int i, int x, int y) {
            if (row[x][i]) {
                return false;
            }
            if (col[y][i]) {
                return false;
            }
            int b = ((x / 3) * 3) + (y / 3); // formula to get the 3x3 box and all 9 elements in it.
            if (box[b][i]) {
                return false;
            }
            return true;
        }
    }

}

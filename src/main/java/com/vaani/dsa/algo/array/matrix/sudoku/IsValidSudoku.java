package com.vaani.dsa.algo.array.matrix.sudoku;


import java.util.*;

public class IsValidSudoku {

    public static boolean validate(char[] boardPart) {
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = 0; i < boardPart.length; i++) {
            char c = boardPart[i];
            if (c != '.') {
                if (hashSet.contains(c)) {
                    return false;
                }
                hashSet.add(c);
            }
        }
        return true;
    }

    public static boolean isValidSudoku_ExtraSpace(char[][] board) {

        if (board == null || board.length != 9 || board[0].length != 9)
            return false;
        int n = board.length;

//        int squareSize = (int) Math.sqrt(n);

        for (int i = 0; i < board.length; i++) {
            char[] row = new char[n];
            char[] square = new char[n];
            char[] column = board[i].clone();

            for (int j = 0; j < n; j++) {
                row[j] = board[j][i];
                square[j] = board[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3];
            }
            if (!(validate(column) && validate(row) && validate(square)))
                return false;


        }
        return true;
    }

    public static boolean isCharacterRepeating(char c, HashSet<Character> hashSet) {
        if (c != '.') {
            if (hashSet.contains(c)) {
                return true;
            }
            hashSet.add(c);
        }
        return false;
    }

    public boolean isValidSudoku(char[][] grid) {
        if (grid == null || grid.length != 9 || grid[0].length != 9)
            return false;
        // check each column
        for (int i = 0; i < 9; i++) {
            HashSet<Character> hashSet = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char c = grid[i][j];
                if (isCharacterRepeating(c, hashSet)) {
                    return false;
                }
            }
        }

        //check each row
        for (int j = 0; j < 9; j++) {
            boolean[] m = new boolean[9];
            HashSet<Character> hashSet = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                char c = grid[i][j];
                if (isCharacterRepeating(c, hashSet)) {
                    return false;
                }
            }
        }

        //check each 3*3 matrix
        for (int block = 0; block < 9; block++) {
            HashSet<Character> hashSet = new HashSet<>();
            for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
                for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
                    char c = grid[i][j];
                    if (isCharacterRepeating(c, hashSet)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static boolean isCharacterRepeatingInt(int c, HashSet<Integer> hashSet) {
        if (hashSet.contains(c)) {
            return true;
        }
        hashSet.add(c);
        return false;
    }

    public boolean isValidSudokuInt(int[][] grid) {
        if (grid == null || grid.length != 9 || grid[0].length != 9)
            return false;
        // check each column
        for (int i = 0; i < 9; i++) {
            HashSet<Integer> hashSet = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                int c = grid[i][j];
                if (isCharacterRepeatingInt(c, hashSet)) {
                    return false;
                }
            }
        }

        //check each row
        for (int j = 0; j < 9; j++) {
            boolean[] m = new boolean[9];
            HashSet<Integer> hashSet = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                int c = grid[i][j];
                if (isCharacterRepeatingInt(c, hashSet)) {
                    return false;
                }
            }
        }

        //check each 3*3 matrix
        for (int block = 0; block < 9; block++) {
            HashSet<Integer> hashSet = new HashSet<>();
            for (int i = block / 3 * 3; i < block / 3 * 3 + 3; i++) {
                for (int j = block % 3 * 3; j < block % 3 * 3 + 3; j++) {
                    int c = grid[i][j];
                    if (isCharacterRepeatingInt(c, hashSet)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isValidSudoku3(char[][] board) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int rowLength = board.length;
        int columnLength = board[0].length;

        if (rowLength != 9 || columnLength != 9) {
            return false;
        }

        int row = 0;
        //check rows
        while (row < rowLength) {
            boolean[] nums = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[row][j] == '.') {
                } else {
                    int number = board[row][j] - '1';
                    if (nums[number] == true) {
                        return false;
                    } else {
                        nums[number] = true;
                    }
                }

            }
            row++;
        }
        //check columns
        int col = 0;
        while (col < columnLength) {
            boolean[] nums = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[j][col] == '.') {
                } else {
                    int number = board[j][col] - '1';
                    if (nums[number] == true) {
                        return false;
                    } else {
                        nums[number] = true;
                    }
                }

            }
            col++;
        }

        //check square
        for (row = 0; row < 9; row += 3) {
            for (col = 0; col < 9; col += 3) {
                boolean[] nums = new boolean[9];
                for (int i = row; i < row + 3; i++) {
                    for (int j = col; j < col + 3; j++) {
                        if (board[i][j] == '.') {

                        } else {
                            int number = board[i][j] - '1';
                            if (nums[number] == true) {
                                return false;
                            } else {
                                nums[number] = true;
                            }
                        }

                    }
                }
            }
        }

        return true;
    }

    public boolean IsValidSudokuUsingString(char[][] board) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int rowLength = board.length;
        int columnLength = board[0].length;

        if (rowLength != 9 || columnLength != 9) {
            return false;
        }

        int row = 0;
        //check rows
        while (row < rowLength) {
            boolean[] nums = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[row][j] == '.') {
                } else {
                    int number = board[row][j] - '1';
                    if (nums[number] == true) {
                        return false;
                    } else {
                        nums[number] = true;
                    }
                }

            }
            row++;
        }
        //check columns
        int col = 0;
        while (col < columnLength) {
            boolean[] nums = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[j][col] == '.') {
                } else {
                    int number = board[j][col] - '1';
                    if (nums[number] == true) {
                        return false;
                    } else {
                        nums[number] = true;
                    }
                }

            }
            col++;
        }

        //check square
        for (row = 0; row < 9; row += 3) {
            for (col = 0; col < 9; col += 3) {
                boolean[] nums = new boolean[9];
                for (int i = row; i < row + 3; i++) {
                    for (int j = col; j < col + 3; j++) {
                        if (board[i][j] == '.') {

                        } else {
                            int number = board[i][j] - '1';
                            if (nums[number] == true) {
                                return false;
                            } else {
                                nums[number] = true;
                            }
                        }

                    }
                }
            }
        }

        return true;
    }

    public boolean isValidSudokuUsingString(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!seen.add(number + " in row " + i) ||
                            !seen.add(number + " in column " + j) ||
                            !seen.add(number + " in block " + i/3 + "-" + j/3))
                        return false;
            }
        }
        return true;
    }
}


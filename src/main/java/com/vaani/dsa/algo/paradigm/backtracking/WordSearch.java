package com.vaani.dsa.algo.paradigm.backtracking;

/* https://leetcode.com/problems/word-search/
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

public class WordSearch {
    public boolean exist1(char[][] board, String word) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.

        int rowLength = board.length;
        int colLength = board[0].length;

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                // once we find the first character in the board
                if (board[i][j] == word.charAt(0)) {
                    if (existsHelper1(board, i, j, word.substring(1))) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    private boolean existsHelper1(char[][] board, int r, int c, String word) {

        if (word.length() == 0) {
            return true;
        }

        //left
        if (c > 0 && board[r][c - 1] == word.charAt(0)) {
            char cur = board[r][c];
            board[r][c] = '*';
            if (existsHelper1(board, r, c - 1, word.substring(1))) {
                return true;
            }
            board[r][c] = cur;
        }

        //up
        if (r > 0 && board[r - 1][c] == word.charAt(0)) {
            char cur = board[r][c];
            board[r][c] = '*';
            if (existsHelper1(board, r - 1, c, word.substring(1))) {
                return true;
            }
            board[r][c] = cur;
        }

        //down
        if (r < board.length - 1 && board[r + 1][c] == word.charAt(0)) {
            char cur = board[r][c];
            board[r][c] = '*';
            if (existsHelper1(board, r + 1, c, word.substring(1))) {
                return true;
            }
            board[r][c] = cur;
        }

        //right
        if (c < board[0].length - 1 && board[r][c + 1] == word.charAt(0)) {
            char cur = board[r][c];
            board[r][c] = '*';
            if (existsHelper1(board, r, c + 1, word.substring(1))) {
                return true;
            }
            board[r][c] = cur;
        }

        return false;
    }

    // submitted
    public boolean exist2(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // once we find the first character in the board
                if (board[i][j] == word.charAt(0)) {
                    if (existsHelper2(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean existsHelper2(char[][] board, int r, int c, String word, int count) {
        if (count == word.length()) {
            return true;
        }
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(count)) {
            return false;
        }

        char temp = board[r][c];
        board[r][c] = '*';
        count = count + 1;
        boolean found = existsHelper2(board, r + 1, c, word, count) || existsHelper2(board, r - 1, c, word, count)
                || existsHelper2(board, r, c + 1, word, count) || existsHelper2(board, r, c - 1, word, count);

        board[r][c] = temp;
        return found;

    }

}

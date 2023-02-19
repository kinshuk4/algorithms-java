package com.vaani.dsa.algo.paradigm.backtracking;

import com.vaani.dsa.ds.core.trie.morebasic.TrieNode;

import java.util.*;

/**
 * 7/4/2017. Given a 2D board and a list of words from the dictionary, find
 * all words in the board.
 *
 * <p>Each word must be constructed from letters of sequentially adjacent cell, where "adjacent"
 * cells are those horizontally or vertically neighboring. The same letter cell may not be used more
 * than once in a word.
 *
 * <p>For example, Given words = ["oath","pea","eat","rain"] and board =
 *
 * <p>[ ['o','a','a','n'], ['e','t','a','e'], ['i','h','k','r'], ['i','f','l','v'] ] Return
 * ["eat","oath"]. Note: You may assume that all inputs are consist of lowercase letters a-z.
 */
public class WordSearch2 {

    static class UsingTrie1 {
        private final int[] R = {0, 0, -1, 1};
        private final int[] C = {-1, 1, 0, 0};

        public static void main(String[] args) throws Exception {
            char[][] board = {
                    {'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}
            };
            String[] words = {"oath", "pea", "eat", "rain"};
//            System.out.println(new WordSearch2().findWords1(board, words));
        }

        // solution with DFS + Trie + HM
        public List<String> findWords1(char[][] board, String[] words) {
//            Set<String> dictionary = new HashSet<>();
////            TrieNode trie = new TrieNode("");
//            for (String w : words) {
////                trie.insert(w);
//                dictionary.add(w);
//            }
//            boolean[][] visited = new boolean[board.length][board[0].length];
//            Set<String> resultSet = new HashSet<>();
//            for (int i = 0; i < board.length; i++) {
//                for (int j = 0; j < board[0].length; j++) {
//                    dfs1(i, j, visited, board, resultSet, trie, dictionary, String.valueOf(board[i][j]));
//                }
//            }
//            return new ArrayList<>(resultSet);
            return null;
        }

        private void dfs1(int r, int c, boolean[][] visited, char[][] board, Set<String> result, TrieNode trie, Set<String> dictionary, String s) {
            char newChar = board[r][c];
            TrieNode subTrie = trie.children.get(newChar);
            if (subTrie == null) {
                return;
            }
            visited[r][c] = true;
            if (dictionary.contains(s)) {
                result.add(s);
            }
            for (int i = 0; i < 4; i++) {
                int newR = r + R[i];
                int newC = c + C[i];
                if (newR >= 0 && newC >= 0 && newR < board.length && newC < board[0].length) {
                    if (!visited[newR][newC]) {
                        dfs1(newR, newC, visited, board, result, subTrie, dictionary, s + board[newR][newC]);
                    }
                }
            }
            visited[r][c] = false;
        }
    }


    static class UsingTrie2 {
        private static final int[] R = {0, 0, -1, 1};
        private static final int[] C = {-1, 1, 0, 0};

        public List<String> findWords(char[][] board, String[] words) {
            TrieNode trie = new TrieNode();
            for (String w : words) {
                insert(trie, w);
            }
            boolean[][] visited = new boolean[board.length][board[0].length];
            Set<String> ansSet = new HashSet<>();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    dfs(board, i, j, visited,  ansSet, trie, "");
                }
            }
            return new ArrayList<>(ansSet);
        }

        public void insert(TrieNode root, String word) {
            TrieNode curr = root;
            for (char c : word.toCharArray()) {
                curr.children.putIfAbsent(c, new TrieNode());
                curr = curr.children.get(c);
            }
            curr.word = word;
        }

        private void dfs(char[][] board, int r, int c, boolean[][] visited, Set<String> ansSet, TrieNode trie, String s) {
            if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) return;
            if (visited[r][c]) return;

            char newChar = board[r][c];
            if (!trie.children.containsKey(newChar)) {
                return;
            }

            s = s + newChar;
            if (! "".equals(trie.children.get(newChar).word)) {
                ansSet.add(s);
            }

            visited[r][c] = true;
            TrieNode subTrie = trie.children.get(newChar);
            for (int i = 0; i < 4; i++) {
                int newR = r + R[i];
                int newC = c + C[i];
                dfs(board, newR, newC, visited, ansSet, subTrie, s);
            }
            visited[r][c]=false;
        }
    }

    static class TrieNode {
        public Map<Character, TrieNode> children = new HashMap<>();
        public String word = "";
    }

    static class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }



        public boolean contains(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    return false;
                }
                node = node.children.get(c);
            }
            return node.word.equals(word);
        }

        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    return false;
                }
                node = node.children.get(c);
            }
            return true;
        }
    }


}

//    private class Trie {
//
//        private Map<Character, Trie> map;
//
//        /**
//         * Initialize your data structure here.
//         */
//        public Trie() {
//            map = new HashMap<>();
//        }
//
//        /**
//         * Inserts a word into the trie.
//         */
//        public void insert(String word) {
//            if (word != null) {
//                add(0, word, word.length());
//            }
//        }
//
//        private void add(int i, String word, int length) {
//            if (i < length) {
//                char c = word.charAt(i);
//                Trie subTrie = map.get(c);
//                if (subTrie == null) {
//                    subTrie = new Trie();
//                    map.put(c, subTrie);
//                }
//                subTrie.add(i + 1, word, length);
//            } else map.put(null, new Trie()); // use null to indicate end of string
//        }
//
//        /**
//         * Get next Trie node
//         *
//         * @param c char c
//         * @return return Trie
//         */
//        public Trie next(char c) {
//            return this.map.get(c);
//        }
//    }

//    public List<String> findWords(char[][] board, String[] words) {
//        dictionary = new HashSet<>();
//        TrieNode trie = new TrieNode("");
//        for (String w : words) {
//            trie.insert(w);
//            dictionary.add(w);
//        }
//
//        List<String> result = new ArrayList<>();
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                existsHelper2(board, i, j, word, 0);
//            }
//        }
//
//        return result;
//    }
//
//    private void existsHelper2(char[][] board, int r, int c, List<String> result, TrieNode trieNode) {
//        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length ||  !trieNode.children.containsKey(board[r][c])) {
//            return ;
//        }
//
//        char temp = board[r][c];
//        board[r][c] = '*';
//        count = count + 1;
//        boolean found = existsHelper2(board, r + 1, c, word, count) || existsHelper2(board, r - 1, c, word, count)
//                || existsHelper2(board, r, c + 1, word, count) || existsHelper2(board, r, c - 1, word, count);
//
//        board[r][c] = temp;
//        return found;
//
//    }
//}

package com.vaani.dsa.ds.core.trie.basic;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    public TrieNode root;

    public Trie() {
        this.root = new TrieNode("");
    }

    public void insert(String word) {
        int length = word.length();
        TrieNode curr = this.root;

        for (int i = 0; i < length; i++) {
            char c = word.charAt(i);
            boolean isWord = i == length - 1;
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode(word.substring(0, i + 1), isWord));
            } else {
                // only set when this condition matches. Otherwise, it may unset in case of other child match, wehre iit thhe cheild is not leaf node
                if (isWord) {
                    curr.children.get(c).isWord = isWord;// for existing child, check if it is a word based on new word
                }
            }

            curr = curr.children.get(c);
        }
    }

    public void insertAll(String[] words) {
        for (String word : words) {
            insert(word);
        }
    }

    public boolean search(String word) {
        return exists(word);
    }

    public boolean exists(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            TrieNode child = curr.children.get(c);
            if (child == null)
                return false;
            curr = child;
        }

        if (curr.isWord) {
            return true;
        } else {
            return false;
        }
    }

    public boolean startsWith(String prefix) {
        //can be rewritten as: return getPrefixNode(prefix) != null
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            TrieNode child = node.children.get(c);
            if (child == null)
                return false;
            node = child;
        }
        return true;
    }

    private TrieNode getPrefixNode(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            TrieNode child = node.children.get(c);
            if (child == null)
                return null;
            node = child;
        }
        return node;
    }

    private void findAllChildWords(TrieNode n, List<String> results) {
        if (n.isWord) {
            results.add(n.prefix);
        }

        for (Character c : n.children.keySet()) {
            findAllChildWords(n.children.get(c), results);
        }
    }

    public List<String> wordsWithPrefix(String prefix) {
        char[] prefixArray = prefix.toCharArray();
        TrieNode temp = root;
        TrieNode tn = getPrefixNode(prefix);

        List<String> words = new ArrayList<>();

        findAllChildWords(tn, words);
        return words;
    }
}

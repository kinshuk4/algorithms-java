package com.vaani.dsa.ds.core.trie.encapsulated;

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
            if (!curr.hasChild(c)) {
                curr.addChild(c, word.substring(0, i + 1), i == length - 1);
            } else {
                // only set when this condition matches. Otherwise, it may unset in case of other child match, wehre iit thhe cheild is not leaf node
                if (i == length - 1) {
                    curr.getChild(c).setWord(i == length - 1);// for existing child, check if it is a word based on new word
                }
            }

            curr = curr.getChild(c);
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
            TrieNode child = curr.getChild(c);
            if (child == null)
                return false;
            curr = child;
        }

        if (curr.isWord()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean startsWith(String prefix) {
        //can be rewritten as: return getPrefixNode(prefix) != null
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            TrieNode child = node.getChild(c);
            if (child == null)
                return false;
            node = child;
        }
        return true;
    }

    private TrieNode getPrefixNode(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            TrieNode child = node.getChild(c);
            if (child == null)
                return null;
            node = child;
        }
        return node;
    }

    private void findAllChildWords(TrieNode n, List<String> results) {
        if (n.isWord()) {
            results.add(n.getPrefix());
        }

        for (Character c : n.getChildren().keySet()) {
            findAllChildWords(n.getChild(c), results);
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

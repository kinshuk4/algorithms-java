package com.vaani.dsa.ds.core.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    public final int ALPHABET = 26;

    private boolean isWord;
    private String prefix;
    private Map<Character, TrieNode> children;

    public TrieNode(String prefix) {
       this(prefix, false);
    }

    public TrieNode(String prefix, boolean isWord) {
        this.isWord = isWord;
        this.prefix = prefix;
        children = new HashMap<>(ALPHABET);
    }

    public TrieNode getChild(char letter) {

        if (children.containsKey(letter)) {
            return children.get(letter);
        }

        return null;
    }

    public void addChild(char c, String prefix, boolean isWord) {
        children.put(c, new TrieNode(prefix, isWord));
    }

    public boolean hasChild(char letter) {
        return this.children.containsKey(letter);
    }

    public boolean isWord() {
        return isWord;
    }

    public String getPrefix() {
        return prefix;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void setWord(boolean word) {
        isWord = word;
    }
}

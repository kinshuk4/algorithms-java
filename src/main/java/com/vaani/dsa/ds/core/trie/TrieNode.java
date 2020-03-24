package com.vaani.dsa.ds.core.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    public final int ALPHABET = 26;

    public boolean isWord;
    public String prefix;
    public Map<Character, TrieNode> children;

    public TrieNode(String prefix) {
        this.isWord = false;
        this.prefix = prefix;
        children = new HashMap<>(ALPHABET);
    }

    public TrieNode getChild(char letter) {

        if (children.containsKey(letter)) {
            return children.get(letter);
        }

        return null;
    }
}

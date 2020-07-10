package com.vaani.dsa.ds.core.trie.basic;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    public boolean isWord;
    public String prefix;
    public Map<Character, TrieNode> children;

    public TrieNode(String prefix) {
       this(prefix, false);
    }

    public TrieNode(String prefix, boolean isWord) {
        this.isWord = isWord;
        this.prefix = prefix;
        children = new HashMap<>();
    }
}

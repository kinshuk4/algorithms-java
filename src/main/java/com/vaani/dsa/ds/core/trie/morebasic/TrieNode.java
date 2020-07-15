package com.vaani.dsa.ds.core.trie.morebasic;

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

    public void insert(String word) {
        int length = word.length();
        TrieNode curr = this;

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
}

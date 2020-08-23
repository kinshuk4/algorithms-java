package com.vaani.dsa.ds.core.trie.morebasic.without_prefix;

import java.util.Map;

public class TrieNode {

    public boolean isWord;
    public Map<Character, TrieNode> children;

    @SuppressWarnings("Duplicates")
    public void insert(String word) {
        int length = word.length();
        TrieNode curr = this;

        for (int i = 0; i < length; i++) {
            char c = word.charAt(i);
            boolean isWord = i == length - 1;
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            // only set when this condition matches. Otherwise, it may unset in case of other child match, wehre iit thhe cheild is not leaf node
            if (isWord) {
                curr.children.get(c).isWord = isWord;// for existing child, check if it is a word based on new word
            }

            curr = curr.children.get(c);
        }
    }
}

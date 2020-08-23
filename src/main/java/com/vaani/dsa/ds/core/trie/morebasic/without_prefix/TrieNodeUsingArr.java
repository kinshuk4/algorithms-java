package com.vaani.dsa.ds.core.trie.morebasic.without_prefix;

public class TrieNodeUsingArr {

    public boolean isWord;
    public TrieNodeUsingArr[] children = new TrieNodeUsingArr[26];
    
    public void insert(String word) {
        int length = word.length();
        TrieNodeUsingArr curr = this;

        for (int i = 0; i < length; i++) {
            char c = word.charAt(i);
            boolean isWord = i == length - 1;
            int childIdx = c - 'a';
            if (curr.children[childIdx] == null) {
                curr.children[childIdx] = new TrieNodeUsingArr();
            }
            // only set when this condition matches. Otherwise, it may unset in case of other child match, wehre iit thhe cheild is not leaf node
            if (isWord) {
                curr.children[childIdx].isWord = isWord;// for existing child, check if it is a word based on new word
            }

            curr = curr.children[childIdx];
        }
    }
}

package com.vaani.dsa.ds.core.trie;

import com.vaani.dsa.ds.core.trie.TrieNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Trie {
    public TrieNode root;
    public int minLevDist;

    public Trie() {
        this.root = new TrieNode("");
    }

    public void insert(String word) {
        int length = word.length();
        TrieNode curr = this.root;

        for (int i = 0; i < length; i++) {
            if(!curr.children.containsKey(word.charAt(i))){
                curr.children.put(word.charAt(i), new TrieNode(word.substring(0, i+1)));
            }

            curr = curr.children.get(word.charAt(i));

//            if (child != null) {
//                current = child;
//            } else {
//                current.children.put(letter, new TrieNode(letter));
//                current = current.getChild(letter);
//            }
            if (i == length - 1) {
                curr.isWord = true;
            }
        }
    }

    public boolean exists(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            TrieNode child = node.getChild(c);
            if (child == null)
                return false;
            node = child;
        }
        if (node.isWord) {
            return true;
        } else {
            return false;
        }
    }

    public boolean startsWith(String prefix){
        //can be rewritten as: return getPrefixNode(prefix) != null
        TrieNode node = root;
        for(char c: prefix.toCharArray()){
            TrieNode child = node.getChild(c);
            if(child==null)
                return false;
            node = child;
        }
        return true;
    }

    private TrieNode getPrefixNode(String prefix){
        TrieNode node = root;
        for(char c: prefix.toCharArray()){
            TrieNode child = node.getChild(c);
            if(child==null)
                return null;
            node = child;
        }
        return node;
    }

    private void findAllChildWords(TrieNode n, List<String> results){
        if(n.isWord){
            results.add(n.prefix);
        }

        for(Character c: n.children.keySet()){
            findAllChildWords(n.children.get(c), results);
        }
    }

    public List<String> wordsWithPrefix(String prefix){
        char[] prefixArray = prefix.toCharArray();
        TrieNode temp = root;
        TrieNode tn = getPrefixNode(prefix);

        List<String> words = new ArrayList<String>();

        findAllChildWords(tn, words);
        return words;
    }
}

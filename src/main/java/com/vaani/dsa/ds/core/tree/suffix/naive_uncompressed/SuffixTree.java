package com.vaani.dsa.ds.core.tree.suffix.naive_uncompressed;

import java.util.List;

public class SuffixTree {
    SuffixTreeNode root = new SuffixTreeNode();

    public SuffixTree(String s) {
        for (int i = 0; i < s.length(); ++i) {
            root.insert(s.substring(i), i);
        }
    }

    public List<Integer> getIndices(String s) {
        return root.getIndices(s);
    }

    public static void main(String[] args) {
        SuffixTree suffixTree = new SuffixTree("banana");
        System.out.println(suffixTree.getIndices("nana"));
    }
}

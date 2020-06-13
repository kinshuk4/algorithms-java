package com.vaani.dsa.ds.core.tree.suffix.naive_uncompressed;

import java.util.*;

public class SuffixTreeNode {
    private List<Integer> indices = new ArrayList<>();
    private Map<Character, SuffixTreeNode> children = new HashMap<>();

    public void insert(String s, int index) {
        indices.add(index);
        if (s != null && s.length() > 0) {
            char c = s.charAt(0);
            if (!children.containsKey(c)) {
                children.put(c, new SuffixTreeNode());
            }
            children.get(c).insert(s.substring(1), index);
        }
    }

    public List<Integer> getIndices(String s) {
        if (s == null || s.length() == 0)
            return indices;
        else {
            char character = s.charAt(0);
            if (children.containsKey(character))
                return children.get(character).getIndices(
                        s.substring(1));
            else
                return null;
        }
    }
}

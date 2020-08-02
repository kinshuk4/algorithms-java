package com.vaani.dsa.ds.core.tree.nary;

import java.util.List;

public class NAryTreeNode {
    public int val;
    public List<NAryTreeNode> children;

    public NAryTreeNode() {
    }

    public NAryTreeNode(int _val, List<NAryTreeNode> _children) {
        val = _val;
        children = _children;
    }
}

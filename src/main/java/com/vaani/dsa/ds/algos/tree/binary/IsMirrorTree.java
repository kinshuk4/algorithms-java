package com.vaani.dsa.ds.algos.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

public class IsMirrorTree {
    public static boolean isMirror(BinaryTreeNode t1, BinaryTreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }
}

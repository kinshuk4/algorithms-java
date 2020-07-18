package com.vaani.dsa.algo.ds.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

public class KthSmallestInBst {
    static int kthSmallestInBST(BinaryTreeNode t, int k) {
        return NthElementInorderTraversal.getNthIterative(t, k);
    }
}

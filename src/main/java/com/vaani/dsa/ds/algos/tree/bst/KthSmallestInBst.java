package com.vaani.dsa.ds.algos.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode;

public class KthSmallestInBst {
    static int kthSmallestInBST(BinaryTreeNode<Integer> t, int k) {
        return NthElementInorderTraversal.getNthIterative(t, k);
    }
}

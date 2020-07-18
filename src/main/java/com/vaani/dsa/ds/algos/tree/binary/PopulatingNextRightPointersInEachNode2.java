package com.vaani.dsa.ds.algos.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeLinkNode;

/*
Follow up for problem "Populating Next Right Pointers in Each ListNode".

What if the given tree could be any binary tree? Would your previous getTreeHeight still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
 */
public class PopulatingNextRightPointersInEachNode2 {
    /*
     * Why recursive the right subtree first?
     *
     * Think about what is happening in this case:
     *
     *         1
     *       /  \
     *      2    3
     *     /    / \
     *    4    5   7
     *     \      / \
     *     10    11 12
     *
     * */

    public TreeLinkNode connect(TreeLinkNode root) {
        connectRecursive(root);
        return root;
    }

    public void connectRecursive(TreeLinkNode root) {
        if (root == null) return;

        TreeLinkNode node = root.next;
        while (node != null && node.left == null && node.right == null) {
            node = node.next;
        }

        TreeLinkNode next = node == null ? null : node.left != null ? node.left : node.right;

        if (root.left != null) {
            root.left.next = root.right != null ? root.right : next;
        }

        if (root.right != null) {
            root.right.next = next;
        }

        connectRecursive(root.right);
        connectRecursive(root.left);
    }

    public void connectIterative(TreeLinkNode root) {
        while (root != null) {
            TreeLinkNode next = null; //first node of next level
            TreeLinkNode prev = null; //previous node on the same level
            while (root != null) {
                if (next == null) {
                    next = root.left == null ? root.right : root.left;
                }
                if (root.left != null) {
                    if (prev != null) {
                        prev.next = root.left;
                    }
                    prev = root.left;
                }
                if (root.right != null) {
                    if (prev != null) {
                        prev.next = root.right;
                    }
                    prev = root.right;
                }
                root = root.next;
            }
            root = next;
        }
    }
}

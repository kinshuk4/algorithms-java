package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeLinkNode;

import java.util.ArrayList;
import java.util.List;

/*
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL

 */
public class PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);
        root.left.left = new TreeLinkNode(4);
        root.left.right = new TreeLinkNode(5);

        root.right.left = new TreeLinkNode(6);
        root.right.right = new TreeLinkNode(7);

        PopulatingNextRightPointersInEachNode test = new PopulatingNextRightPointersInEachNode();

        test.connectRecursive(root);
    }

    public TreeLinkNode connect(TreeLinkNode root) {
        connectRecursive(root);
        return root;
    }

    public void connectRecursive(TreeLinkNode root) {
        if (root == null || root.left == null || root.right == null) {
            return;
        }

        root.left.next = root.right;
        root.right.next = root.next == null ? null : root.next.left;

        connectRecursive(root.left);
        connectRecursive(root.right);
    }

    public void connectIterative(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        List<TreeLinkNode> levelList = new ArrayList<>();
        levelList.add(root);

        while (!levelList.isEmpty()) {

            List<TreeLinkNode> nextLevel = new ArrayList<>();
            for (int i = 0; i < levelList.size(); i++) {
                levelList.get(i).next = (i + 1 == levelList.size()) ? null : levelList.get(i + 1);
                if (levelList.get(i).left != null) {
                    nextLevel.add(levelList.get(i).left);
                }
                if (levelList.get(i).right != null) {
                    nextLevel.add(levelList.get(i).right);
                }
            }
            levelList = nextLevel;
        }
    }
}

package com.vaani.dsa.algo.ds.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/*https://leetcode.com/problems/unique-binary-search-trees-ii/submissions/
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
*/


public class UniqueBinarySearchTrees2 {
    public List<BinaryTreeNode> generateTreesRecursive(int n) {
        if (n == 0){
            return new ArrayList<>();
        }
        return helper(1, n);
    }

    private List<BinaryTreeNode> helper(int start, int end) {
        List<BinaryTreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<BinaryTreeNode> leftTrees = helper(start, i - 1);
            List<BinaryTreeNode> rightTrees = helper(i + 1, end);

            for (BinaryTreeNode left : leftTrees) {
                for (BinaryTreeNode right : rightTrees) {
                    BinaryTreeNode root = new BinaryTreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }
}

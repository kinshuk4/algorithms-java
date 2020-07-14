package com.vaani.dsa.ds.algos.tree.binary;
/*

https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

*/

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import static com.vaani.dsa.ds.utils.generic.ArrayUtils.linearSearch;

public class ConstructBinaryTreeFromInorderAndPreorder {
    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 5, 1, 6, 3, 7};

        buildTree1(preorder, inorder);
    }

    public static BinaryTreeNode buildTree1(int[] preorder, int[] inorder) {
        int preLength = preorder.length;
        int inLength = inorder.length;

        if (preLength != inLength) {
            return null;
        }

//        return buildTreeHelper(preorder, inorder, 0, preLength - 1, 0, inLength - 1);
        return buildTreeHelper2(preorder, inorder, 0, 0, inLength - 1);
    }

    public static BinaryTreeNode buildTreeHelper(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preEnd < preStart || inEnd < inStart) {
            return null;
        }

        BinaryTreeNode root = new BinaryTreeNode(preorder[preStart]);

        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                int leftPreStart = preStart + 1; // left node will be next node if not null
                int rightPreStart = preEnd - inEnd + i + 1; // diff of 2 nodes
                int leftPreEnd = preStart - inStart + i;
                root.left = buildTreeHelper(preorder, inorder, leftPreStart, leftPreEnd, inStart, i - 1);
                root.right = buildTreeHelper(preorder, inorder, rightPreStart, preEnd, i + 1, inEnd);
            }
        }

        return root;
    }

    private static BinaryTreeNode buildTreeHelper2(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if (inStart > inEnd || preStart > preorder.length - 1) {
            return null;
        }

        // Pick current node from Preorder traversal using preStart and increment preStart
        BinaryTreeNode root = new BinaryTreeNode(preorder[preStart]);

        // If this node has no children then return
        if (inStart == inEnd) {
            return root;
        }

        // Else find the index of this node in Inorder traversal
        int inIndex = linearSearch(inorder, root.val, inStart, inEnd);

        // Using index in Inorder traversal, construct left and right subtrees
        root.left = buildTreeHelper2(preorder, inorder, preStart + 1, inStart, inIndex - 1);
        // We are skipping the elements on the left of the current pre order node by adding inIndex - inStart + 1
        // very good explanation: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/34538/My-Accepted-Java-Solution
        root.right = buildTreeHelper2(preorder, inorder, preStart + inIndex - inStart + 1, inIndex + 1, inEnd);

        return root;
    }


}
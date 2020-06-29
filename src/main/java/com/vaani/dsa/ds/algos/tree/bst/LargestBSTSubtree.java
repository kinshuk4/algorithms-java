package com.vaani.dsa.ds.algos.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode;

/**
 * Given a binary tree,
 * find the largest subtree which is a Binary Search Tree (BST),
 * where largest means subtree with largest number of nodes in it.
 */
public class LargestBSTSubtree {

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode(10);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node15 = new BinaryTreeNode(15);
        BinaryTreeNode node6 = new BinaryTreeNode(6);
        BinaryTreeNode node7 = new BinaryTreeNode(7);
        node15.right = node7;
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node12 = new BinaryTreeNode(12);
        BinaryTreeNode node11 = new BinaryTreeNode(11);
        BinaryTreeNode node13 = new BinaryTreeNode(13);
        BinaryTreeNode node14 = new BinaryTreeNode(14);

//        root.insertForBst(node5);
//        root.insertForBst(node6);
//        root.insertForBst(node15);
//        root.insertForBst(node3);
//        root.insertForBst(node12);
//        root.insertForBst(node11);
//        root.insertForBst(node13);
//        root.insertForBst(node14);

        int result = largestBSTSubtree(root);
        System.out.println("largest bst (may not include all children): " + result);

        LargestBST largeBST = largestBSTSubtree1(root);
        if (largeBST != null)
            System.out.println(largeBST.node.value + " : size " + largeBST.maxNode);

        LargestBST largeBST2 = largestBSTSubtree2(root);
        if (largeBST2 != null)
            System.out.println(largeBST2.node.value + " : size " + largeBST2.maxNode);
    }

    // Given a binary tree, find the largest Binary Search Tree (BST),
    // where largest means BST with largest number of nodes in it.
    // The largest BST may or may not include all of its descendants.
    public static int largestBSTSubtree(BinaryTreeNode<Integer> node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        
        BinaryTreeNode<Integer> left = node.left;
        BinaryTreeNode<Integer> right = node.right;
        int leftNode = largestBSTSubtree(left);
        int rightNode = largestBSTSubtree(right);

        if ( left != null && right != null) {
            if ((left.value < node.value) && (right.value > node.value)) {
                return leftNode + rightNode + 1;
            } else if (left.value < node.value) {
                return leftNode + 1;
            } else if (right.value > node.value) {
                return rightNode + 1;
            } else {
                return Math.max(rightNode, leftNode);
            }
        } else if (left != null) {
            if (left.value < node.value)
                return leftNode + 1;
            else
                return leftNode;
        } else {// if (right != null){
            if (node.value < right.value)
                return rightNode + 1;
            else
                return rightNode;
        }
    }

    // Given a binary tree, find the largest Binary Search Tree (BST),
    // where largest means BST with largest number of nodes in it.
    // The largest BST may or may not include all of its descendants.
    public static LargestBST largestBSTSubtree1(BinaryTreeNode<Integer> node) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null) {
            return new LargestBST(node, node.size(), node.value, node.value);
        }

        LargestBST leftNode = largestBSTSubtree1(node.left);
        LargestBST rightNode = largestBSTSubtree1(node.right);

        if (leftNode != null && rightNode != null) {
            if ((node.value > leftNode.max && node.left == leftNode.node)
                    && (node.value < rightNode.min && node.right == rightNode.node)) {

                LargestBST bst = new LargestBST(node,
                        leftNode.maxNode + rightNode.maxNode + 1,
                        leftNode.min,
                        rightNode.max);

                return bst;
            } else if (node.value > leftNode.max && node.left == leftNode.node) {
                return new LargestBST(node, leftNode.maxNode + 1, leftNode.min, node.value);

            } else if (node.value < rightNode.min && node.right == rightNode.node) {
                return new LargestBST(node, rightNode.maxNode + 1, node.value, rightNode.max);
            } else {
                return (leftNode.maxNode > rightNode.maxNode) ? leftNode : rightNode;
            }
        } else if (leftNode != null) {
            if (node.value > leftNode.max && node.left == leftNode.node) {
                return new LargestBST(node, leftNode.maxNode + 1, leftNode.min, node.value);
            } else {
                return leftNode;
            }

        } else if (rightNode != null) {
            if (node.value < rightNode.min && node.right == rightNode.node) {
                return new LargestBST(node, rightNode.maxNode + 1, node.value, rightNode.max);
            } else {
                return rightNode;
            }
        }

        return null;
    }

    // Given a binary tree, find the largest Binary Search Tree (BST),
    // where largest means BST with largest number of nodes in it.
    // The largest BST must include all of its descendants.
    public static LargestBST largestBSTSubtree2(BinaryTreeNode<Integer> node) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null) {
            return new LargestBST(node, node.size(), node.value, node.value);
        }

        LargestBST leftNode = largestBSTSubtree2(node.left);
        LargestBST rightNode = largestBSTSubtree2(node.right);

        if (leftNode != null && rightNode != null) {
            if ((node.value > leftNode.max && node.left == leftNode.node)
                    && (node.value < rightNode.min && node.right == rightNode.node)) {

                LargestBST bst = new LargestBST(node,
                        leftNode.maxNode + rightNode.maxNode + 1,
                        leftNode.min,
                        rightNode.max);

                return bst;
            } else {
                return (leftNode.maxNode > rightNode.maxNode) ? leftNode : rightNode;
            }
        } else if (leftNode != null) {
            if (node.value > leftNode.max && node.left == leftNode.node) {
                return new LargestBST(node, leftNode.maxNode + 1, leftNode.min, node.value);
            } else {
                return leftNode;
            }

        } else if (rightNode != null) {
            if (node.value < rightNode.min && node.right == rightNode.node) {
                return new LargestBST(node, rightNode.maxNode + 1, node.value, rightNode.max);
            } else {
                return rightNode;
            }
        }
        return null;
    }

    public static class LargestBST {
        public BinaryTreeNode node;
        public int maxNode;
        public int min;
        public int max;

        public LargestBST(BinaryTreeNode n, int number, int min_value, int max_value) {
            node = n;
            max = max_value;
            min = min_value;
            maxNode = number;
        }
    } // end of class LargestBST


}

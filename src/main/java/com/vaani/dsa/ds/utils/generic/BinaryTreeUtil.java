package com.vaani.dsa.ds.utils.generic;

import com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeUtil {

    public static BinaryTreeNode<Integer> arrayToBinaryTree(Integer[] arr) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(arr[0]);
        BinaryTreeNode<Integer>[] treeNodesArr = (BinaryTreeNode<Integer>[]) new BinaryTreeNode[arr.length];
        treeNodesArr[0] = root;
        for (int i = 1; i < arr.length; i++) {
            BinaryTreeNode<Integer> item = new BinaryTreeNode<>(arr[i]);
            treeNodesArr[i] = item;
            int parent = (i - 1) / 2;
            if ((i - 1) % 2 == 0) {
                treeNodesArr[parent].left = item;
            } else {
                treeNodesArr[parent].right = item;
            }

        }

        return root;
    }


    /*
     * Tree =
     *         20
     *        /
     *       8
     *      / \
     *     4  12
     *       /  \
     *      10  14
     *
     */
    public static BinaryTreeNode<Integer> getBinaryTree() {
        BinaryTreeNode root = new BinaryTreeNode(20);
        root.left = new BinaryTreeNode(8);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(12);
        root.left.right.left = new BinaryTreeNode(10);
        root.left.right.right = new BinaryTreeNode(14);
        return root;
    }

    public static <T> String serializeWithPreorder(BinaryTreeNode<T> head) {
        StringBuilder result = new StringBuilder();
        preorder(head, result);
        return result.toString();
    }

    private static <T> void preorder(BinaryTreeNode<T> head, StringBuilder result) {
        if (head == null) {
            result.append("#");
            result.append(" ");
            return;
        }
        result.append(head.val);
        result.append(" ");
        preorder(head.left, result);
        preorder(head.right, result);
    }

    public static <T> BinaryTreeNode<T> deserializeWithPreorder(T input) {
//        String[] tokens = input.split(" ");
//        return deserializeWithPreorderHelper(tokens);
        return null;
    }

    private static int index = 0;

    private static <T> BinaryTreeNode<T> deserializeWithPreorderHelper(T[] tokens) {
        if (index >= tokens.length || tokens[index].equals("#")) {
            index++;
            return null;
        }
        BinaryTreeNode<T> root = new BinaryTreeNode<T>(tokens[index++]);
        root.left = deserializeWithPreorderHelper(tokens);
        root.right = deserializeWithPreorderHelper(tokens);
        return root;
    }

    public static <T> List<T> levelOrderTraversal(BinaryTreeNode<T> root) {
        List<T> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<BinaryTreeNode<T>> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode<T> remove = queue.remove();
            result.add(remove.val);
            if (remove.left != null) {
                queue.add(remove.left);
            }
            if (remove.right != null) {
                queue.add(remove.right);
            }

        }
        return result;
    }

}

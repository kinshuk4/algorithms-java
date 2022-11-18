package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.LinkedList;

import static com.vaani.dsa.ds.utils.simple.BinaryTreeUtil.getABinaryTree1;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * Design an algorithm and write code to serialize and deserialize a binary tree.
 * Writing the tree to a file is called ‘serialization’ and reading back from the file to reconstruct the exact same binary tree is ‘deserialization’
 * <p>
 * Reference: http://www.geeksforgeeks.org/serialize-deserialize-binary-tree/
 */
public class SerializeBinaryTree {


    private static int index = 0;

    public static String serializeWithPreorder(TreeNode head) {
        StringBuilder result = new StringBuilder();
        preorder(head, result);
        return result.toString();
    }

    @SuppressWarnings("Duplicates")
    private static void preorder(TreeNode head, StringBuilder result) {
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

    public static TreeNode deserializeWithPreOrder(String input) {
        String[] tokens = input.split(" ");
        return deserializeWithPreOrderHelper(tokens);
    }


    @SuppressWarnings("Duplicates")
    private static TreeNode deserializeWithPreOrderHelper(String[] tokens) {

        if (index >= tokens.length || tokens[index].equals("#")) {
            index++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(tokens[index++]));
        root.left = deserializeWithPreOrderHelper(tokens);
        root.right = deserializeWithPreOrderHelper(tokens);
        return root;
    }

    // Encodes a tree to a single string.
    public static String serializeWithLevelOrder(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if (t != null) {
                sb.append(t.val).append(",");
                queue.add(t.left);
                queue.add(t.right);
            } else {
                sb.append("#,");
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static TreeNode deserializeWithLevelOrder(String data) {
        if (data == null || data.length() == 0)
            return null;

        String[] arr = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));


        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                continue;
            }

            if (!arr[i].equals("#")) {
                node.left = new TreeNode(Integer.parseInt(arr[i]));
                queue.offer(node.left);

            } else {
                node.left = null;
                queue.offer(null);
            }
            i++;

            if (!arr[i].equals("#")) {
                node.right = new TreeNode(Integer.parseInt(arr[i]));
                queue.offer(node.right);

            } else {
                node.right = null;
                queue.offer(null);
            }
            i++;
        }

        return root;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(30);
        root.left = new TreeNode(10);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(50);
        root.right.left = new TreeNode(45);
        root.right.right = new TreeNode(35);
        System.out.println(serializeWithPreorder(root));
        String input = "30 10 50 # # # 20 45 # # 35 # # ";
        System.out.println(deserializeWithPreOrder(input));

        String output = serializeWithLevelOrder(getABinaryTree1());
        System.out.println(output);
        System.out.println(deserializeWithLevelOrder(output));
    }

}

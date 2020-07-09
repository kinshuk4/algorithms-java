package com.vaani.dsa.ds.algos.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.LinkedList;

import static com.vaani.dsa.ds.utils.simple.BinaryTreeUtil.getABinaryTree1;

/**
 * Design an algorithm and write code to serialize and deserialize a binary tree.
 * Writing the tree to a file is called ‘serialization’ and reading back from the file to reconstruct the exact same binary tree is ‘deserialization’
 * <p>
 * Reference: http://www.geeksforgeeks.org/serialize-deserialize-binary-tree/
 */
public class SerializeBinaryTree {


    private static int index = 0;

    public static String serializeWithPreorder(BinaryTreeNode head) {
        StringBuilder result = new StringBuilder();
        preorder(head, result);
        return result.toString();
    }

    @SuppressWarnings("Duplicates")
    private static void preorder(BinaryTreeNode head, StringBuilder result) {
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

    public static BinaryTreeNode deserializeWithPreOrder(String input) {
        String[] tokens = input.split(" ");
        return deserializeWithPreOrderHelper(tokens);
    }


    @SuppressWarnings("Duplicates")
    private static BinaryTreeNode deserializeWithPreOrderHelper(String[] tokens) {

        if (index >= tokens.length || tokens[index].equals("#")) {
            index++;
            return null;
        }
        BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(tokens[index++]));
        root.left = deserializeWithPreOrderHelper(tokens);
        root.right = deserializeWithPreOrderHelper(tokens);
        return root;
    }

    // Encodes a tree to a single string.
    public static String serializeWithLevelOrder(BinaryTreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        LinkedList<BinaryTreeNode> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode t = queue.poll();
            if (t != null) {
                sb.append(String.valueOf(t.val)).append(",");
                queue.add(t.left);
                queue.add(t.right);
            } else {
                sb.append("#,");
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static BinaryTreeNode deserializeWithLevelOrder(String data) {
        if (data == null || data.length() == 0)
            return null;

        String[] arr = data.split(",");
        BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(arr[0]));


        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty()) {
            BinaryTreeNode t = queue.poll();

            if (t == null)
                continue;

            if (!arr[i].equals("#")) {
                t.left = new BinaryTreeNode(Integer.parseInt(arr[i]));
                queue.offer(t.left);

            } else {
                t.left = null;
                queue.offer(null);
            }
            i++;

            if (!arr[i].equals("#")) {
                t.right = new BinaryTreeNode(Integer.parseInt(arr[i]));
                queue.offer(t.right);

            } else {
                t.right = null;
                queue.offer(null);
            }
            i++;
        }

        return root;

    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(30);
        root.left = new BinaryTreeNode(10);
        root.right = new BinaryTreeNode(20);
        root.left.left = new BinaryTreeNode(50);
        root.right.left = new BinaryTreeNode(45);
        root.right.right = new BinaryTreeNode(35);
        System.out.println(serializeWithPreorder(root));
        String input = "30 10 50 # # # 20 45 # # 35 # # ";
        System.out.println(deserializeWithPreOrder(input));

        String output = serializeWithLevelOrder(getABinaryTree1());
        System.out.println(output);
        System.out.println(deserializeWithLevelOrder(output));
    }

}

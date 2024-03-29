package com.vaani.dsa.algo.ds.tree.binary.traversal;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.*;

public class ZigZagLevelOrder {

    public static List<List<Integer>> zigzagLevelOrderUsingQueue(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        List<Integer> level = new LinkedList<>();
        queue1.add(root);

        while (!queue1.isEmpty()) {
            TreeNode node = queue1.poll();
            level.add(node.val);

            if (node.left != null) {
                queue2.add(node.left);
            }
            if (node.right != null) {
                queue2.add(node.right);
            }

            if (queue1.isEmpty()) {
                result.add(level);
                level = new LinkedList<>();
                queue1.addAll(queue2);
                queue2.clear();
            }
        }
        for (int i = 1; i < result.size(); i += 2) {
            Collections.reverse(result.get(i));
        }
        return result;
    }

    /**
     * Two Stack Solution
     */
    public static List<List<Integer>> ZigZagLevelOrderUsingStack(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }


        Stack<TreeNode> currLevel = new Stack<>();
        Stack<TreeNode> nextLevel = new Stack<>();
        Stack<TreeNode> temp;

        boolean leftToRight = true;

        // push root in stack
        currLevel.push(root);
        List<Integer> level = new LinkedList<>();

        while (!currLevel.empty()) {
            TreeNode node = currLevel.pop(); //top();
            level.add(node.val);

            if (leftToRight) {
                //if left to right, start pushing from left
                pushToStack(nextLevel, node.left);
                pushToStack(nextLevel, node.right);
            } else {
                //else push the right node first
                pushToStack(nextLevel, node.right);
                pushToStack(nextLevel, node.left);
            }


            if (currLevel.isEmpty()) {
                result.add(level);
                level = new LinkedList<>();

                leftToRight = !leftToRight;
                //swap stacks
                temp = currLevel;
                currLevel = nextLevel;
                nextLevel = temp;
                //we can also set currLevel = nextLevel and instantiate nextLevel again
                //nextLevel = new Stack<BinaryTreeNode>();

            }
        }
        return result;
    }

    private static void pushToStack(Stack<TreeNode> stack, TreeNode node) {
        if (node != null) {
            stack.push(node);
        }
    }

    public static List<List<Integer>> zigzagLevelOrderUsingRecursion(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        dfs(root, 0, result);
        return result;
    }

    private static void dfs(TreeNode cur, int level, List<List<Integer>> result) {
        if (cur == null) return;
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }
        if (level % 2 == 0) {
            result.get(level).add(cur.val);
        } else {
            result.get(level).add(0, cur.val);
        }
        dfs(cur.left, level + 1, result);
        dfs(cur.right, level + 1, result);
    }


}

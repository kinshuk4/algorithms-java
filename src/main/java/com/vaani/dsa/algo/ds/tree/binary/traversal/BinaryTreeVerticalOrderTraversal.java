package com.vaani.dsa.algo.ds.tree.binary.traversal;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.*;

/**
 This is the just Vertial Order traversal - no sorting etc. as in leetcode question 987
 */
public class BinaryTreeVerticalOrderTraversal {
    static class UsingMap {
        public static List<List<Integer>> verticalTraversal(TreeNode root) {
            if (root == null) {
                return new LinkedList<>();
            }

            // Using treemap as keys are automatically sorted
            Map<Integer, List<Integer>> map = new TreeMap<>();
            List<List<Integer>> result = new LinkedList<>();

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            Queue<Integer> distanceQueue = new LinkedList<>();
            distanceQueue.add(0);

            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size > 0) {
                    TreeNode current = queue.poll();

                    int currDistance = distanceQueue.poll();

                    List<Integer> currentList = map.getOrDefault(currDistance, new LinkedList<>());
                    currentList.add(current.val);
                    map.put(currDistance, currentList);

                    if (current.left != null) {
                        queue.add(current.left);
                        distanceQueue.add(currDistance - 1);
                    }

                    if (current.right != null) {
                        queue.add(current.right);
                        distanceQueue.add(currDistance + 1);
                    }
                    size--;
                }

            }

            // left to right, from maximum -ve distance from root to maximum +ve distance from root
            for (int i : map.keySet()) {
                List<Integer> list = map.get(i);
                result.add(list);
            }
            return result;
        }
    }

}

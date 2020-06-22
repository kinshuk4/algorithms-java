package com.vaani.dsa.ds.algos.tree.binary.traversal.view;

import com.vaani.dsa.ds.core.tree.BinaryTreeNode;

import java.util.*;

public class BinaryTreeTopView {
//    public void topView(Node root, int level) {
//        if (root == null)
//            return;
//        // create a queue for level order traversal
//        Queue<QueuePack> queue = new LinkedList<>();
//        // add root with level 0 (create a queue item pack)
//        queue.add(new QueuePack(level, root));
//        while (!queue.isEmpty()) {
//            QueuePack q = queue.remove();
//            // take out the items from the package
//            Node tnode = q.tnode;
//            int lvl = q.level;
//
//            // check if this is the first node you are visiting at the level
//            if (ht.containsKey(lvl)) {
//
//            } else {// print it, its the first element at his level
//                System.out.print(tnode.data + "   ");
//                ht.put(lvl, tnode.data);
//            }
//
//            // add the left and right children of visiting nodes to the queue
//            if (tnode.left != null) {
//                queue.add(new QueuePack(lvl - 1, tnode.left));
//            }
//            if (tnode.right != null) {
//                queue.add(new QueuePack(lvl + 1, tnode.right));
//            }
//        }
//
//    }

    static class DriftedBinaryTreeNode {
        BinaryTreeNode<Integer> node;
        int drift;

        public DriftedBinaryTreeNode(BinaryTreeNode<Integer> node, int drift) {
            this.node = node;
            this.drift = drift;
        }
    }

    static void topView(BinaryTreeNode<Integer> root) {
        Queue<DriftedBinaryTreeNode> queue = new LinkedList<>();
        queue.add(new DriftedBinaryTreeNode(root, 0));

        // map of current drift on left and right and the first value we see in the map
        // we need treemap as we need to sort by the drift
        Map<Integer, Integer> treeMap = new TreeMap<>();

        while (!queue.isEmpty()) {
            DriftedBinaryTreeNode curr = queue.poll();
            int currDrift = curr.drift;
            BinaryTreeNode<Integer> currNode = curr.node;

            if (!treeMap.containsKey(curr.drift)) {
                treeMap.put(curr.drift, currNode.value);
            }

            if (currNode.left != null) {
                queue.add(new DriftedBinaryTreeNode(currNode.left, currDrift - 1));
            }

            if (currNode.right != null) {
                queue.add(new DriftedBinaryTreeNode(currNode.right, currDrift + 1));
            }

        }

        for(int i: treeMap.keySet()){
            System.out.print(" "+treeMap.get(i));
        }
    }
}

package com.vaani.dsa.algo.ds.convert;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;
import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import static com.vaani.dsa.ds.utils.simple.ListUtil.getLength;

/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in
which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted linked list: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5


*/


/*
O(N) solution:

BinaryTree* sortedListToBST(ListNode *& list, int start, int end) {
  if (start > end) return NULL;
  // same as (start+end)/2, avoids overflow
  int mid = start + (end - start) / 2;
  BinaryTree *leftChild = sortedListToBST(list, start, mid-1);
  BinaryTree *parent = new BinaryTree(list->data);
  parent->left = leftChild;
  list = list->next;
  parent->right = sortedListToBST(list, mid+1, end);
  return parent;
}

BinaryTree* sortedListToBST(ListNode *head, int n) {
  return sortedListToBST(head, 0, n-1);
}

*/
public class ConvertSortedListToBST {

    public static void main(String[] args) {
        ConvertSortedListToBST test = new ConvertSortedListToBST();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        test.sortedListToBST3(head);
    }


    public BinaryTreeNode sortedListToBST(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (head == null) {
            return null;
        }
        ListNode end = head;
        while (end != null) {
            end = end.next;
        }
        return getNode(head, end);
    }

    public BinaryTreeNode getNode(ListNode start, ListNode end) {
        ListNode fast = start;
        ListNode slow = start;
        if (start == end) {
            return null;
        }
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        BinaryTreeNode root = new BinaryTreeNode(slow.val);
        root.left = getNode(start, slow);
        root.right = getNode(slow.next, end);
        return root;
    }


    /**
     * Native getTreeHeight as Convert Sorted Array to BST.
     * Top-down approach
     * Time: O(NlogN)
     */
    public BinaryTreeNode sortedListToBST2(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new BinaryTreeNode(head.val);
        ListNode preMid = getPreMid2(head);
        ListNode mid = preMid.next;
        preMid.next = null;
        ListNode first = head, second = mid.next;
        BinaryTreeNode root = new BinaryTreeNode(mid.val);
        root.left = sortedListToBST(first);
        root.right = sortedListToBST(second);
        return root;
    }

    public ListNode getPreMid2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first = head, second = head.next.next;
        while (second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
        }
        return first;
    }

    /**
     * Bottom-up approach
     * Time: O(n)
     * Reference: http://leetcode.com/2010/11/convert-sorted-list-to-balanced-binary.html
     * create nodes bottom-up, and assign them to its parents.
     * The bottom-up approach enables us to access the list in its order at the same time as creating nodes.
     */
    ListNode head;

    // submitted
    public BinaryTreeNode sortedListToBST3(ListNode head) {
        int len = getLength(head);
        this.head = head;
        return sortedListToBST3(0, len - 1);
    }

    public BinaryTreeNode sortedListToBST3(int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        BinaryTreeNode left = sortedListToBST3(start, mid - 1);
        BinaryTreeNode root = new BinaryTreeNode(head.val);
        head = head.next;
        root.left = left;
        root.right = sortedListToBST3(mid + 1, end);
        return root;
    }
}

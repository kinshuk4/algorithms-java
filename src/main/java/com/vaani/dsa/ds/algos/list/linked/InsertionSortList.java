package com.vaani.dsa.ds.algos.list.linked;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

/**
 * Sort a linked list using insertion sort.
 * <p>
 * Reference: http://www.binglu.me/leetcode-question-insertion-sort-list/
 * <p>
 *  on 8/4/2014.
 */
public class InsertionSortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(1);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);

        InsertionSortList test = new InsertionSortList();
        test.insertionSortList(head).display();
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode curr = head;

        while (curr != null) {
            ListNode prev = dummy;
            ListNode next = curr.next;

            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;

            }
            curr.next = prev.next;
            prev.next = curr;
            curr = next;
        }
        return dummy.next;
    }

    public ListNode insertionSortList2(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (head == null) return null;

        ListNode newHead = null;
        ListNode newTail = null;
        int min = head.val;
        while (head != null) {
            ListNode next = head.next;
            ListNode node = head;
            ListNode curMin = null;
            while (node != null) {
                if (curMin == null) curMin = node;
                if (node.val < curMin.val) curMin = node;
                node = node.next;
            }

            if (newHead == null) {
                newHead = curMin;
                newTail = curMin;
            } else {
                newTail.next = curMin;
                newTail = curMin;
            }

            //remove curMin from original list
            node = head;
            if (curMin == head) {
                head = next;
            } else {
                while (node.next != null) {
                    if (node.next == curMin) {
                        node.next = node.next.next;
                        break;
                    }
                    node = node.next;
                }
            }

        }
        return newHead;
    }
}

package com.vaani.dsa.algo.ds.list.linked;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

import static com.vaani.dsa.algo.ds.list.linked.MergeTwoSortedLists.mergeIterative;
import static com.vaani.dsa.ds.utils.simple.ListUtil.getPointerToMidNode;


/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * <p>
 */
public class SortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(1);
        head.next.next = new ListNode(5);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);

        SortList test = new SortList();
        sortList(head).display();
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode partition = getPointerToMidNode(head);

        ListNode first = head;
        ListNode second = partition.next;


        partition.next = null;
        first = sortList(first);
        second = sortList(second);
        return mergeIterative(first, second);
    }

}

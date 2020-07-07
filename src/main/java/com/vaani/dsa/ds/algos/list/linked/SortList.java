package com.vaani.dsa.ds.algos.list.linked;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;
import com.vaani.dsa.ds.utils.simple.ListUtil;


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
        test.sortList(head).display();
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode partition = ListUtil.getMidNode(head);
        ListNode mid = partition.next;
        partition.next = null;

        ListNode first = sortList(head);
        ListNode second = sortList(mid);
        return MergeTwoSortedLists.mergeIterative(first, second);
//        return mergeRecursive(first, second);
    }

}

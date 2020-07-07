package com.vaani.dsa.ds.utils.simple;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

public class ListUtil {

    public static  ListNode getMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        return slow;
    }
}

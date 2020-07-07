package com.vaani.dsa.ds.algos.list.linked;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

public class IntersectionOfTwoLinkedList {
    static int getLength(ListNode head) {
        ListNode curr = head;
        int length = 0;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

    static ListNode findMergeNode(ListNode head1, ListNode head2) {

        int l1 = getLength(head1);
        int l2 = getLength(head2);

        int diff = Math.abs(l1 - l2);
        ListNode big = l1 > l2 ? head1 : head2;
        ListNode small = l1 <= l2 ? head1 : head2;

        while(diff > 0){
            big = big.next;
            diff--;
        }

        while(big != null && small != null){
            if(big.val == small.val){
                return big;
            }
            big = big.next;
            small = small.next;
        }

        return null;

    }
}

package com.vaani.dsa.algo.ds.list.linked;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

//https://www.hackerrank.com/challenges/compare-two-linked-lists/problem
public class CompareLinkedList {
    static boolean compareLists(ListNode head1, ListNode head2) {
        ListNode curr1 = head1;
        ListNode curr2 = head2;

        while(curr1 != null && curr2!=null){
            if(curr1.val != curr2.val){
                return false;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        if(curr1 != null || curr2 != null){
            return false;
        }

        return true;

    }
}

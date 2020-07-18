package com.vaani.dsa.algo.ds.list.linked;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

import static com.vaani.dsa.ds.utils.simple.ListUtil.getLength;

public class IntersectionOfTwoLinkedLists {


    // method when node values are unique
    // will not work when:
    // 1 - 9 - 1
    //              2 - 4
    //     3 - 1
    static ListNode getIntersectionNode1(ListNode head1, ListNode head2) {

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

    public static ListNode getIntersectionNode2(ListNode head1, ListNode head2) {
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
            if(big == small){
                return big;
            }
            big = big.next;
            small = small.next;
        }

        return null;

    }
}

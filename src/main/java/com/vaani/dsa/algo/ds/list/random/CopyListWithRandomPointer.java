package com.vaani.dsa.algo.ds.list.random;

/*

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
*/

import com.vaani.dsa.ds.core.list.random.RandomListNode;





public class CopyListWithRandomPointer {

    /*
     * 1) Create the copy of node 1 and insert it between node 1 & node 2 in
     * original Linked List, create the copy of 2 and insert it between 2 & 3..
     * Continue in this fashion, add the copy of N afte the Nth node 2) Now copy the
     * arbitrary link in this fashion
     *
     * original->next->arbitrary = original->arbitrary->next; /*TRAVERSE TWO NODES
     * This works because original->next is nothing but copy of original and
     * Original->arbitrary->next is nothing but copy of arbitrary. 3) Now restore
     * the original and copy linked lists in this fashion in a single loop.
     *
     * original->next = original->next->next; copy->next = copy->next->next;
     */
    // submitted
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        RandomListNode curr = head;
        while (curr != null) {
            RandomListNode copy = new RandomListNode(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            } else {
                curr.next.random = null;
            }

            curr = curr.next.next;
        }

        RandomListNode newHead = head;
        newHead = newHead.next;
        curr = newHead.next;
        RandomListNode copy = newHead;

        head.next = curr;

        while (curr != null) {
            copy.next = curr.next;
            copy = copy.next;
            curr.next = curr.next.next;
            curr = curr.next;
        }

        return newHead;

    }

    public RandomListNode copyRandomList2(RandomListNode head) {
        if (head == null) return head;
        RandomListNode n = head;
        while (n != null) {
            RandomListNode temp = new RandomListNode(n.val);
            temp.next = n.next;
            n.next = temp;
            n = n.next.next;
        }
        RandomListNode result = head.next;

        n = head;
        while (n != null) {
            if (n.random != null) n.next.random = n.random.next;
            n = n.next.next;
        }

        n = head;
        while (n != null) {
            RandomListNode temp = n.next;
            n.next = temp.next;
            if (temp.next != null) temp.next = temp.next.next;
            n = n.next;
        }
        return result;
    }
}
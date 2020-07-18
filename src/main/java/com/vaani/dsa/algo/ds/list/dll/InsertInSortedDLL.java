package com.vaani.dsa.algo.ds.list.dll;

import com.vaani.dsa.ds.core.list.dll.generic.DLLNode;

public class InsertInSortedDLL {
    public static <T extends Comparable<T>> DLLNode<T> insertInSortedDLL(DLLNode<T> head, T data) {
        DLLNode<T> newNode = new DLLNode<>(data);
        if (newNode.value.compareTo(head.value) < 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return head;
        }


        DLLNode<T> curr = head;
        DLLNode<T> currPrev = head;
        while (curr != null && curr.value.compareTo(newNode.value) < 0) {
            currPrev = curr;
            curr = curr.next;
        }


        newNode.next = currPrev.next;
        newNode.prev = currPrev;
        if (currPrev.next != null) {
            currPrev.next.prev = newNode;
        }
        currPrev.next = newNode;
        return head;
    }

//    static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {
//        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
//        if(head == null){
//            return newNode;
//        }
//        DoublyLinkedListNode curr = head;
//        while(curr.next != null && curr.data < data){
//            curr = curr.next;
//        }
//
//        if(curr.data < data){
//            DoublyLinkedListNode next = curr.next;
//            curr.next = newNode;
//            newNode.prev = curr;
//            newNode.next= next;
//        }else {
//            DoublyLinkedListNode prev = curr.prev;
//            newNode.next = curr;
//            newNode.prev = prev;
//            // curr is not head node
//            if (prev != null){
//                prev.next = newNode;
//
//            }else{
//                head = newNode;
//            }
//
//
//        }
//
//        return head;
//
//    }
}

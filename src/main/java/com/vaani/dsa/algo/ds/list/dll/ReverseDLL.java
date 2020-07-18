package com.vaani.dsa.algo.ds.list.dll;

import com.vaani.dsa.ds.core.list.dll.generic.DoubleLinkedList;

import static com.vaani.dsa.ds.utils.generic.DLLUtil.reverseList;

// http://www.geeksforgeeks.org/reverse-a-doubly-linked-list/
public class ReverseDLL {



    public static void main(String a[]) {
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();
        linkedList.add(11);
        linkedList.add(22);
        linkedList.add(33);
        linkedList.add(44);
        linkedList.add(55);
        linkedList.add(66);
//        linkedList.printList();
        reverseList(linkedList);
//        linkedList.printList();
    }
}
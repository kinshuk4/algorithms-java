package com.vaani.dsa.algo.ds.tree.bst;

/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

*/

import static com.vaani.dsa.algo.numerical.number.CatalanNumber.*;

//f(n) += f(i-1)*f(n-i).
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        return catalanBinomial(n);
    }

    public int numTreesRecursive(int n) {
        return catalanRecursve(n);
    }

    public int numTreesDP(int n) {
        return catalanDP(n);
    }
}

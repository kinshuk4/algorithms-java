package com.vaani.dsa.ds.core.tree.segment;

public class RangeMinQuery {
    private final int n;
    private final int[] segTree;

    public RangeMinQuery(int n) {
        this.n = n;
        segTree = new int[2 * n];
    }

    void build(int[] a) {

        // assign values to leaves of the segment tree
        for (int i = 0; i < n; i++) {
            segTree[n + i] = a[i];
        }

        /*
         * assign values to internal nodes
         * to compute minimum in a given range
         */
        for (int i = n - 1; i >= 1; i--) {
            segTree[i] = Math.min(segTree[2 * i], segTree[2 * i + 1]);
        }
    }

    void update(int pos, int value) {

        // change the index to leaf node first
        pos += n;

        // update the value at the leaf node
        // at the exact index
        segTree[pos] = value;

        while (pos > 1) {

            // move up one level at a time in the tree
            pos >>= 1;

            // update the values in the nodes in
            // the next higher level
            segTree[pos] = Math.min(segTree[2 * pos],
                    segTree[2 * pos + 1]);
        }
    }

    int query(int left, int right) {

        /*
         * Basically the left and right indices will move
         * towards right and left respectively and with
         * every each next higher level and compute the
         * minimum at each height. */
        // change the index to leaf node first
        left += n;
        right += n;

        // initialize minimum to a very high value
        int mi = (int) 1e9;

        while (left < right) {

            // if left index is odd
            if ((left & 1) == 1) {
                mi = Math.min(mi, segTree[left]);

                // make left index even
                left++;
            }

            // if right index is odd
            if ((right & 1) == 1) {

                // make right index even
                right--;

                mi = Math.min(mi, segTree[right]);
            }

            // move to the next higher level
            left /= 2;
            right /= 2;
        }
        return mi;
    }

    // Driver Code
    public static void main(String[] args) {
        int[] a = {2, 6, 10, 4, 7, 28, 9, 11, 6, 33};
        RangeMinQuery rmq = new RangeMinQuery(a.length);

        /*
         * Construct the segment tree by assigning
         * the values to the internal nodes
         */
        rmq.build(a);

        // compute minimum in the range left to right
        int left = 0, right = 5;
        System.out.printf("Minimum in range %d to %d is %d\n",
                left, right, rmq.query(
                        left, right + 1));

        // update the value of index 3 to 1
        int index = 3, value = 1;

        // a[3] = 1;
        // Contents of array : {2, 6, 10, 1, 7, 28, 9, 11, 6, 33}
        rmq.update(index, value); // point update

        // compute minimum in the range left to right
        left = 2;
        right = 6;
        System.out.printf("Minimum in range %d to %d is %d\n",
                left, right, rmq.query(
                        left, right + 1));
    }
}

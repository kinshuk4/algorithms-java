package com.vaani.dsa.algo.search.string;

public class RabinKarp {
    private final String pat;      // the pattern  // needed only for Las Vegas
    private final long patHash;    // pattern hash value
    private final int m;           // pattern length

    private static final long R = 256; // radix
    private static final long Q = (long) 10e9 + 7; // long prime for modulo to avoid overflow
    private final long windowPower;  // R^(m-1) % Q

    public RabinKarp(String pat) {
        this.pat = pat;
        this.m = pat.length();
        this.patHash = hash(this.pat);
        // precompute R^(M-1) % Q for use in removing leading digit
        long temp = 1;
        for (int i = 1; i <= m - 1; i++) {
            temp = (R * temp) % Q;
        }
        windowPower = temp;
    }

    // returns s[0] * R^(m-1) + s[1] * R^(m-2)...
    private long hash(String s) {
        long h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = (h * R + s.charAt(i)) % Q;
        }
        return h;
    }

    private long getNextHash(long currHash, char prevCh, char currCh) {
        long nextHash = currHash;
        nextHash = (nextHash + Q - (windowPower * prevCh) % Q) % Q;
        nextHash = (nextHash * R + currCh) % Q;

        return nextHash;
    }
    private boolean check(String txt, int i) {
        for (int j = 0; j < m; j++) {
            if (pat.charAt(j) != txt.charAt(i + j)) {
                return false;
            }
        }
        return true;
    }

    public int indexOf(String txt) {
        int n = txt.length();
        if (n < m) {
            return -1;
        }

        long txtHash = hash(txt.substring(0, m));

        // check for match at offset 0
        if ((patHash == txtHash) && check(txt, 0)) {
            return 0;
        }

        // check for hash match; if hash match, check for exact match
        int j = m - 1;
        for (int i = 1; i <= n - m; i++) {
            // Remove leading digit, add trailing digit, check for match.
            txtHash = getNextHash(txtHash, txt.charAt(i - 1), txt.charAt(i + j));
            if ((patHash == txtHash) && check(txt, i)) {
                return i;
            }
        }

        // no match
        return -1;
    }

    public static void main(String[] args) {
        RabinKarp rk = new RabinKarp("ababcaabc");
        System.out.println(rk.indexOf("ababcaababcaabc"));
    }
}

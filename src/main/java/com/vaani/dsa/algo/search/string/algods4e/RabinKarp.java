package com.vaani.dsa.algo.search.string.algods4e;

import java.math.BigInteger;
import java.util.Random;

import static java.lang.System.out;

public class RabinKarp {
    private final String pat;      // the pattern  // needed only for Las Vegas
    private final long patHash;    // pattern hash value
    private final int m;           // pattern length
    private final long q;          // a large prime, small enough to avoid long overflow
    private final int R;           // radix
    private long RM;         // R^(M-1) % Q

    public RabinKarp(int R, char[] pattern) {
        throw new RuntimeException("Operation not supported yet");
    }

    public RabinKarp(String pat) {
        this.pat = pat;      // save pattern (needed only for Las Vegas)
        m = pat.length();
        patHash = hash(pat);
        q = longRandomPrime(); // for eg. 101

        R = 256;

        // precompute R^(M-1) % Q for use in removing leading digit
        RM = 1;
        for (int i = 1; i <= m - 1; i++) {
            RM = (R * RM) % q;
        }

    }

    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = new BigInteger(31, new Random());
        return prime.longValue();
    }

    // test client
    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];
        char[] pattern = pat.toCharArray();
        char[] text = txt.toCharArray();

        RabinKarp searcher = new RabinKarp(pat);
        int offset = searcher.search(txt);

        // print results
        out.println("text:    " + txt);

        // from brute force search method 1
        out.print("pattern: ");
        for (int i = 0; i < offset; i++)
            out.print(" ");
        out.println(pat);
    }

    // Compute hash for key[0..M-1].
    private long hash(String key) {
        long h = 0;
        for (int j = 0; j < m; j++) {
            h = (R * h + key.charAt(j)) % q;
        }
        return h;
    }

    // Las Vegas version: does pat[] match txt[i..i-M+1] ?
    private boolean check(String txt, int i) {
        for (int j = 0; j < m; j++) {
            if (pat.charAt(j) != txt.charAt(i + j)) {
                return false;
            }
        }
        return true;
    }


    // Monte Carlo version: always return true
    // private boolean check(int i) {
    //    return true;
    //}

    // check for exact match
    public int search(String txt) {
        int N = txt.length();
        if (N < m) return N;
        long txtHash = hash(txt);

        // check for match at offset 0
        if ((patHash == txtHash) && check(txt, 0))
            return 0;

        // check for hash match; if hash match, check for exact match
        for (int i = m; i < N; i++) {
            // Remove leading digit, add trailing digit, check for match.
            txtHash = (txtHash + q - RM * txt.charAt(i - m) % q) % q;
            txtHash = (txtHash * R + txt.charAt(i)) % q;

            // match
            int offset = i - m + 1;
            if ((patHash == txtHash) && check(txt, offset))
                return offset;
        }

        // no match
        return -1;
    }
}
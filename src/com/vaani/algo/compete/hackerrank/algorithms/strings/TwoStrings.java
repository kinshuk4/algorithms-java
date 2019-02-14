package com.vaani.algo.compete.hackerrank.algorithms.strings;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: ramswaroop
 * @date: 10/22/15
 * @time: 10:08 AM
 */
public class TwoStrings {

    public static String isSubstringInBoth(String[] a) {
        char[] alphabets = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z'};

        for (int i = 0; i < alphabets.length; i++) {
            if (a[0].indexOf(alphabets[i]) != -1 && a[1].indexOf(alphabets[i]) != -1) return "YES";
        }

        return "NO";
    }

    public static void main(String a[]) {
        Scanner in = new Scanner(System.in);

        int t = Integer.parseInt(in.nextLine());
        String[][] input = new String[t][2];

        for (int i = 0; i < t; i++) {
            input[i][0] = in.nextLine();
            input[i][1] = in.nextLine();
        }

        for (int i = 0; i < t; i++) {
            System.out.println(isSubstringInBoth(input[i]));
        }
    }
}

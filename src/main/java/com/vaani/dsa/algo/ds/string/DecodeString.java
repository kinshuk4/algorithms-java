package com.vaani.dsa.algo.ds.string;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given an encoded string, return its corresponding decoded string.
 * <p>
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is repeated exactly k times. Note: k is guaranteed to be a positive integer.
 * <p>
 * Note that your solution should have linear complexity because this is what you will be asked during an interview.
 * <p>
 * Example
 * <p>
 * For s = "4[ab]", the output should be
 * decodeString(s) = "abababab";
 * <p>
 * For s = "2[b3[a]]", the output should be
 * decodeString(s) = "baaabaaa";
 * <p>
 * For s = "z1[y]zzz2[abc]", the output should be
 * decodeString(s) = "zyzzzabcabc".
 */
public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decodeString2("abc"));
        System.out.println(decodeString2("4[ab]"));
        System.out.println(decodeString2("2[b3[a]]"));
        System.out.println(decodeString2("z1[y]zzz2[abc]"));
        System.out.println(decodeString2("2[2[b]]"));
        System.out.println(decodeString2("100[codesignal]"));
        System.out.println(decodeString2("sd2[f2[e]g]i"));
    }

    static String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int k = 0;
        int openCounter = 0;
        List<Integer> counterList = new LinkedList<>();
        List<StringBuilder> stringTillNowList = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (c) {
                case '[':
                    sb = new StringBuilder();
//                    k = s.charAt(i - 1) - '0';
                    counterList.add(openCounter, k);
                    stringTillNowList.add(openCounter, sb);
                    openCounter++;

                    break;
                case ']':
                    openCounter--;
                    String currSubStr = stringTillNowList.get(openCounter).toString();
                    StringBuilder tempSb = new StringBuilder();
                    k = counterList.get(openCounter);
                    for (int j = 0; j < k; j++) {
                        tempSb.append(currSubStr);
                    }
                    if (openCounter != 0) {
                        stringTillNowList.get(openCounter - 1).append(tempSb);
                    } else {
                        sb2.append(tempSb);
                    }
                    break;
                default:
                    if (Character.isDigit(c)) {
                        StringBuilder numSB = new StringBuilder();
                        numSB.append(c);
                        for (int j = i + 1; j < s.length(); ) {
                            char d = s.charAt(j);
                            if (Character.isDigit(d)) {
                                numSB.append(d);
                                j++;
                                continue;
                            }
                            i = j - 1;
                            if (s.charAt(j) == '[') {
                                k = Integer.parseInt(numSB.toString());
                                break;
                            } else {
                                if (openCounter == 0) {
                                    sb2.append(numSB);
                                    break;
                                } else {
                                    sb.append(numSB);
                                    stringTillNowList.add(openCounter - 1, sb);
                                    break;
                                }
                            }
                        }

                    } else if (openCounter == 0) {
                        sb2.append(c);
                    } else {
//                        sb.append(c);
                        StringBuilder currSb = stringTillNowList.get(openCounter - 1);
                        currSb.append(c);
                        stringTillNowList.set(openCounter - 1, currSb);
                    }
//                    if (openCounter == 0 && (i + 1 < s.length() && s.charAt(i + 1) != '[')) {
//                        sb2.append(c);
//                    } else if (openCounter == 0 && i == (s.length() - 1)) {
//                        sb2.append(c);
//                    } else if ((i + 1 < s.length() && s.charAt(i + 1) != '[')) {
//                        sb.append(c);
//                        stringTillNowList.add(openCounter - 1, sb);
//                    }

            }


        }
        return sb2.toString();
    }

    public static String decodeString2(String s) {
        Stack<Integer> counts = new Stack<>();
        Stack<String> results = new Stack<>();

        StringBuilder result = new StringBuilder();
        int i = 0, n = s.length();

        while (i < n) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int count = 0;
                //eg. 30002 we should get to the end of digit
                while (Character.isDigit(c)) {
                    count = count * 10 + (c - '0');
                    i++;
                    c = s.charAt(i);
                }
                counts.push(count);
            } else if (c == '[') {
                results.push(result.toString());
                result = new StringBuilder();
                i++;
            } else if (c == ']') {
                String str = results.pop();
                int count = counts.pop();
                String repeated = result.toString().repeat(count);
                result = new StringBuilder(str).append(repeated);
                i++;
            } else {
                result.append(c);
                i++;
            }
        }

        return result.toString();
    }


}

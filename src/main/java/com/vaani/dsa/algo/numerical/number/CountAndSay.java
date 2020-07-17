package com.vaani.dsa.algo.numerical.number;


/* https://leetcode.com/problems/count-and-say/
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string. 
*/

public class CountAndSay {

    public static void main(String[] args) throws Exception {
        System.out.println(new CountAndSay().countAndSay3(4));
    }

    public String countAndSay1(int n) {
        if (n == 0) {
            return "";
        }
        String s = "1";
        if (n == 1) {
            return s;
        }

        int count = 1;
        int k = 1;
        while (k < n) {
            StringBuilder cur = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {
                if (i == s.length() - 1 || s.charAt(i + 1) != s.charAt(i)) {
                    cur.append(count);
                    cur.append(s.charAt(i));
                    count = 1;
                } else {
                    count++;
                }
            }
            s = cur.toString();
            k++;
        }

        return s;
    }

    public String countAndSay2(int n) {
        String result = "1";
        for (int i = 1; i < n; i++) {
            int count = 1;
            char num = result.charAt(0);
            StringBuilder temp = new StringBuilder();
            for (int j = 1, l = result.length(); j < l; j++) {
                if (result.charAt(j) == num) {
                    count++;
                } else {
                    temp = temp.append(count).append(num);
                    num = result.charAt(j);
                    count = 1;
                }
            }
            temp = temp.append(String.valueOf(count)).append(String.valueOf(num));
            result = temp.toString();
        }
        return result;
    }

    // submitted
    public String countAndSay3(int n) {
        if (n == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("1");
        return countAndSay3Helper(n, 1, sb);
    }

    public String countAndSay3Helper(int n, int curr, StringBuilder sb) {
        if (curr == n) {
            return sb.toString();
        }

        if (sb.length() == 1) {
            sb = new StringBuilder("11");
        } else {
            String s = sb.toString();
            sb = new StringBuilder();
            int count = 1;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == s.charAt(i - 1)) {
                    count++;
                } else {
                    sb.append(count).append(s.charAt(i - 1));
                    count = 1;
                }
            }
            // handle last index value
            sb.append(count).append(s.charAt(s.length() - 1));
        }
        return countAndSay3Helper(n, curr + 1, sb);

    }
}

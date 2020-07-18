package com.vaani.dsa.algo.ds.string;

/* https://leetcode.com/problems/valid-palindrome/
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/

public class ValidPalindrome {
    public static void main(String[] args) throws Exception {
        System.out.println(new ValidPalindrome().isPalindrome("989 "));
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        StringBuilder sanitizedString = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

            char cur = s.charAt(i);

            if ((cur >= 'a' && cur <= 'z') || (cur >= '0' && cur <= '9')) {
                sanitizedString.append(cur);
            }

            if (cur >= 'A' && cur <= 'Z') {
                cur = Character.toLowerCase(cur);
                sanitizedString.append(cur);
            }

        }

        int size = sanitizedString.length();
        for (int i = 0; i < size / 2; i++) {
            if (sanitizedString.charAt(i) != sanitizedString.charAt(size - i - 1)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome2(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        s = s.toLowerCase();
        for (int i = 0, j = s.length() - 1; i < j; ) {
            char leftChar = s.charAt(i);
            char rightChar = s.charAt(j);
            if (!isCharacterOrDigit(leftChar)) {
                i++;
                continue;
            }
            if (!isCharacterOrDigit(rightChar)) {
                j--;
                continue;
            }
            if (leftChar != rightChar) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isCharacterOrDigit(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
            return true;
        }
//        return Character.isLetterOrDigit(c);
        return false;
    }
}

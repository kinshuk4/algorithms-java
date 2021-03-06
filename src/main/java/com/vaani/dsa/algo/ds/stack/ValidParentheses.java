package com.vaani.dsa.algo.ds.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
/**
 * 20. Valid Parentheses
 * Easy
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */

public class ValidParentheses {

    // using stack - submitted
    public static boolean isValid1(String s) {
        HashMap<Character, Character> parenthese = new HashMap<>() {{
            put('(', ')');
            put('{', '}');
            put('[', ']');
        }};

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (parenthese.containsKey(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char cKey = stack.pop();

                char cVal = parenthese.get(cKey);

                if (cVal != c) {
                    return false;
                }

            }
        }

        return stack.isEmpty();
    }

    // Using list
    public static boolean isValid2(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        HashMap<Character, Character> parenthese = new HashMap<>() {{
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }};

        ArrayList<Character> toBeClosed = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (!parenthese.containsKey(cur)) {
                toBeClosed.add(cur);
            } else {
                if (toBeClosed.isEmpty()) {
                    return false;
                } else {
                    int length = toBeClosed.size();
                    if (toBeClosed.get(length - 1) == parenthese.get(cur)) {
                        toBeClosed.remove(length - 1);
                    } else {
                        return false;
                    }
                }
            }
        }

        if (toBeClosed.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    // Using stack + switch case + also handles unknown characters
    public static boolean isValid3(String str) {
        if (str == null) return true;
        HashMap<Character, Character> MAP = new HashMap<>();
        MAP.put(')', '(');
        MAP.put('}', '{');
        MAP.put('>', '<');
        MAP.put(']', '[');

        Stack<Character> stack = new Stack<>();
        for (int i = 0, l = str.length(); i < l; i++) {
            switch (str.charAt(i)) {
                case '(':
                case '{':
                case '[':
                case '<':
                    stack.push(str.charAt(i));
                    break;

                case ')':
                case '}':
                case ']':
                case '>':
                    if (stack.isEmpty()) return false;
                    char top = stack.pop();
                    if (top != MAP.get(str.charAt(i))) return false;
                    break;

                default: // ignore
            }
        }
        return stack.isEmpty() ? true : false;
    }
}

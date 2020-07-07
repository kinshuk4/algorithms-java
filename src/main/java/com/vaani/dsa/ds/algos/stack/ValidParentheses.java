package com.vaani.dsa.ds.algos.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

public class ValidParentheses {


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

    public static boolean isValid2(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        HashMap<Character, Character> parenthese = new HashMap<>() {{
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }};

        ArrayList<Character> toBeClosed = new ArrayList<Character>();
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
}

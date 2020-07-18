package com.vaani.dsa.algo.ds.stack;

import java.util.Stack;
/* https://leetcode.com/problems/evaluate-reverse-polish-notation/
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> operandStack = new Stack<Integer>();

        for (String s : tokens) {
            if (s.matches("-?[\\d]+")) {
                operandStack.push(Integer.parseInt(s));
            } else {
                int operand2 = operandStack.pop();
                int operand1 = operandStack.pop();
                int result = 0;
                String operator = s;
                switch (operator){
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        result = operand1 / operand2;
                        break;
                }
                operandStack.push(result);
            }
        }
        return operandStack.pop();
    }
}

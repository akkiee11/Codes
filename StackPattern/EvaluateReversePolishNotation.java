import java.util.Stack;

/**
 * Problem Statement: Evaluate the value of an arithmetic expression in Reverse Polish Notation (RPN).
 * Approach: Use a stack to keep track of operands. When encountering an operator, pop the last two operands, apply the operator, and push the result back.
 * Intuition: RPN eliminates the need for parentheses by ensuring operators are applied in the correct order.
 * Time Complexity: O(n) - We process each token exactly once.
 * Space Complexity: O(n) - In the worst case, the stack can hold all the operands.
 */
public class EvaluateReversePolishNotation {
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (token.equals("-")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b);
            } else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    
    // Edge Cases:
    // 1. Single number input
    // 2. Empty input
    // 3. Invalid tokens
    // 4. Division by zero
    // 5. Overflow/Underflow
    public static void main(String[] args) {
        String[][] testCases = {
            {"2", "1", "+", "3", "*"},
            {"4", "13", "5", "/", "+"},
            {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}
        };
        for (String[] test : testCases) {
            System.out.println(String.join(" ", test) + " = " + evalRPN(test));
        }
    }
}
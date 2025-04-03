import java.util.Stack;

/**
 * Valid Parentheses
 * 
 * Problem Statement:
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 
 * Approach:
 * We use a stack data structure to keep track of opening brackets. As we iterate through the string:
 * 1. When we encounter an opening bracket, we push it onto the stack.
 * 2. When we encounter a closing bracket, we check if it matches the top of the stack.
 * 3. If all brackets are matched correctly, the stack should be empty at the end.
 * 
 * Intuition:
 * The stack helps us maintain the order of brackets and ensures that the most recent opening bracket
 * is matched with the current closing bracket.
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) in the worst case when all characters are opening brackets
 */
public class ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') || 
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // Test cases including edge cases
        String[] testCases = {
            "()",        // Valid
            "()[]{}",    // Valid
            "(]",        // Invalid - wrong closing bracket
            "([)]",      // Invalid - wrong order
            "{[]}",      // Valid
            "",          // Edge case - empty string
            "[",         // Edge case - single opening bracket
            "]"          // Edge case - single closing bracket
        };
        
        for (String test : testCases) {
            System.out.println(test + ": " + isValid(test));
        }
    }
}
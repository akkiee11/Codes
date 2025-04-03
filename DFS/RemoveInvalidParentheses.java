/**
 * Remove Invalid Parentheses
 *
 * Problem: Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses
 * to make the input string valid. Return all possible results.
 *
 * Example 1:
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 *
 * Example 2:
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 *
 * Example 3:
 * Input: s = ")("
 * Output: [""]
 *
 * Approach:
 * 1. First count minimum number of left and right parentheses to remove
 * 2. Use DFS with backtracking to try all possible combinations of removing parentheses
 * 3. Use pruning to avoid duplicate results and invalid paths
 * 4. Keep track of current string, position, count of removed left and right parentheses
 *
 * Time Complexity: O(2^N) where N is the length of the string
 * Space Complexity: O(N) for the recursion stack
 */

import java.util.*;

public class RemoveInvalidParentheses {
    private Set<String> validExpressions;
    
    public List<String> removeInvalidParentheses(String s) {
        validExpressions = new HashSet<>();
        
        // Count minimum removals needed
        int[] minRemoval = getMinRemoval(s);
        int minLeft = minRemoval[0];
        int minRight = minRemoval[1];
        
        // Start DFS
        dfs(s, 0, 0, 0, minLeft, minRight, new StringBuilder());
        
        return new ArrayList<>(validExpressions);
    }
    
    private int[] getMinRemoval(String s) {
        int left = 0, right = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        
        return new int[]{left, right};
    }
    
    private void dfs(String s, int index, int leftCount, int rightCount,
                     int leftRem, int rightRem, StringBuilder expression) {
        // Base case: reached end of string
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                validExpressions.add(expression.toString());
            }
            return;
        }
        
        char currentChar = s.charAt(index);
        int length = expression.length();
        
        // If current character is not a parenthesis, add it
        if (currentChar != '(' && currentChar != ')') {
            expression.append(currentChar);
            dfs(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression);
            expression.setLength(length);
        } else {
            // Skip current character if we still need to remove parentheses
            if ((currentChar == '(' && leftRem > 0) || (currentChar == ')' && rightRem > 0)) {
                dfs(s, index + 1, leftCount, rightCount,
                    leftRem - (currentChar == '(' ? 1 : 0),
                    rightRem - (currentChar == ')' ? 1 : 0),
                    expression);
            }
            
            // Add current character and continue
            expression.append(currentChar);
            
            // Only add if expression remains valid
            if (currentChar == '(') {
                dfs(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression);
            } else if (currentChar == ')' && leftCount > rightCount) {
                dfs(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression);
            }
            
            expression.setLength(length);
        }
    }
    
    public static void main(String[] args) {
        RemoveInvalidParentheses solution = new RemoveInvalidParentheses();
        
        // Test Case 1
        String s1 = "()())()";
        System.out.println("Test Case 1: " + solution.removeInvalidParentheses(s1));
        
        // Test Case 2
        String s2 = "(a)())()";
        System.out.println("Test Case 2: " + solution.removeInvalidParentheses(s2));
        
        // Test Case 3
        String s3 = ")(";
        System.out.println("Test Case 3: " + solution.removeInvalidParentheses(s3));
    }
}
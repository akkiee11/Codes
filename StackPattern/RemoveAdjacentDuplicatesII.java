import java.util.Stack;

/**
 * Remove All Adjacent Duplicates in String II
 * 
 * Problem Statement:
 * Given a string s and an integer k, remove all adjacent duplicates until no more can be removed.
 * A duplicate consists of k or more adjacent identical characters.
 * Return the final string after all such duplicate removals have been made.
 * 
 * Approach:
 * We use a stack to track characters and their counts. As we iterate through the string:
 * 1. When we encounter a character that matches the top of the stack, we increment its count
 * 2. When the count reaches k, we remove the character from the stack
 * 3. Finally, we reconstruct the string from the stack
 * 
 * Intuition:
 * The stack helps us maintain the order of characters and their counts, allowing efficient removal
 * when we find k adjacent duplicates.
 * 
 * Time Complexity: O(n) where n is the length of the string
 * Space Complexity: O(n) in the worst case when all characters are unique
 */
public class RemoveAdjacentDuplicatesII {
    public static String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().character == c) {
                stack.peek().count++;
            } else {
                stack.push(new Pair(c, 1));
            }
            
            if (stack.peek().count == k) {
                stack.pop();
            }
        }
        
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair p = stack.pop();
            for (int i = 0; i < p.count; i++) {
                result.append(p.character);
            }
        }
        
        return result.reverse().toString();
    }
    
    private static class Pair {
        char character;
        int count;
        
        Pair(char c, int count) {
            this.character = c;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        // Test cases including edge cases
        String[] testCases = {
            "abcd",         // No duplicates
            "deeedbbcccbdaa",  // Multiple duplicates
            "pbbcggttciiippooaais",  // Mixed cases
            "yfttttfbbbbnnnnffbgffffgbbbbgssssgthyyyy",  // Long string
            "",             // Edge case - empty string
            "a"             // Edge case - single character
        };
        
        int k = 3;
        for (String test : testCases) {
            System.out.println(test + " -> " + removeDuplicates(test, k));
        }
    }
}
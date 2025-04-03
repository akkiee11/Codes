/**
 * Reverse String
 * 
 * Problem Description:
 * Write a function that reverses a string in-place. The input string is given as
 * an array of characters. You must do this by modifying the input array in-place
 * with O(1) extra memory.
 * 
 * Example:
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * 
 * Approach:
 * Two Pointer Technique (O(n)):
 * - Use two pointers: left (starting from beginning) and right (starting from end)
 * - Swap characters at left and right pointers
 * - Move pointers towards center until they meet
 * - Time Complexity: O(n), Space Complexity: O(1)
 */

public class ReverseString {
    
    public static void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        
        int left = 0;
        int right = s.length - 1;
        
        while (left < right) {
            // Swap characters at left and right pointers
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            
            // Move pointers towards center
            left++;
            right--;
        }
    }
    
    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
            "hello",            // Basic case
            "Hannah",           // Palindrome
            "a",               // Single character
            "",                // Empty string
            "12345",           // Numbers
            "!@#$%",           // Special characters
            "Hello, World!"    // String with spaces and punctuation
        };
        
        for (String test : testCases) {
            char[] charArray = test.toCharArray();
            System.out.println("\nOriginal string: " + test);
            
            reverseString(charArray);
            
            System.out.println("Reversed string: " + new String(charArray));
        }
    }
}
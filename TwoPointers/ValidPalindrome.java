/**
 * Valid Palindrome
 * 
 * Problem Description:
 * Given a string s, determine if it is a palindrome, considering only alphanumeric
 * characters and ignoring cases.
 * 
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * 
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * 
 * Approach:
 * Two Pointer Technique (O(n)):
 * - Use two pointers: left (starting from beginning) and right (starting from end)
 * - Skip non-alphanumeric characters
 * - Compare characters case-insensitively
 * - Move pointers towards center until they meet
 * - Time Complexity: O(n), Space Complexity: O(1)
 */

public class ValidPalindrome {
    
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            
            // Skip non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare characters ignoring case
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
            "A man, a plan, a canal: Panama",  // True - Classic palindrome with spaces and punctuation
            "race a car",                     // False - Not a palindrome
            "Was it a car or a cat I saw?",  // True - Palindrome with spaces and punctuation
            "hello",                         // False - Regular word
            "12321",                        // True - Numeric palindrome
            "12345",                        // False - Numbers
            "a",                            // True - Single character
            "",                             // True - Empty string
            ".,",                           // True - Only non-alphanumeric
            "0P"                            // False - Case sensitive comparison
        };
        
        for (String test : testCases) {
            System.out.println("\nInput string: \"" + test + "\"");
            boolean isPalindrome = isPalindrome(test);
            System.out.println("Is palindrome: " + isPalindrome);
        }
    }
}
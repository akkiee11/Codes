/**
 * Problem: Longest Substring Without Repeating Characters
 * 
 * Intuition:
 * - To find substring without repeats, we need to track unique characters
 * - When we encounter a repeat, we can't include it until we remove its previous occurrence
 * - Sliding window helps maintain the valid substring as we move through string
 * 
 * Approach:
 * 1. Use HashSet to track characters in current window
 * 2. Expand window to right until we find a duplicate
 * 3. When duplicate found, contract window from left until duplicate is removed
 * 4. Track maximum length of valid window seen so far
 * 
 * Key Points:
 * - Window contracts from left when duplicate found
 * - Window expands to right one character at a time
 * - HashSet provides O(1) lookup for duplicates
 * 
 * Time Complexity: O(n)
 * - Each character is added and removed at most once
 * - Both pointers traverse the string once
 * 
 * Space Complexity: O(min(m,n)) where m is the size of character set
 * - HashSet stores at most min(string length, character set size) characters
 */
public class LongestSubstringNoRepeat {
    public static int findLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        
        // HashSet to store characters in current window
        java.util.HashSet<Character> set = new java.util.HashSet<>();
        int maxLength = 0;
        int left = 0;
        
        // Iterate through the string using right pointer
        for (int right = 0; right < s.length(); right++) {
            // If character is already in set, remove characters from left
            // until we remove the duplicate
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            
            // Add current character to set
            set.add(s.charAt(right));
            
            // Update maxLength if current window is larger
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    public static void main(String[] args) {
        // Test cases
        String[] tests = {
            "abcabcbb",  // Expected: 3 ("abc")
            "bbbbb",     // Expected: 1 ("b")
            "pwwkew",    // Expected: 3 ("wke")
            "",          // Expected: 0
            "dvdf"       // Expected: 3 ("vdf")
        };
        
        for (String test : tests) {
            System.out.println("Input: " + test);
            System.out.println("Length of longest substring: " + 
                             findLongestSubstring(test));
            System.out.println();
        }
    }
}
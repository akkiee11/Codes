/**
 * Problem: Find the minimum window substring that contains all characters of pattern
 * 
 * Intuition:
 * - We need to find the smallest substring that contains all characters from pattern string
 * - Using a sliding window allows us to efficiently track valid windows and minimize their size
 * - Character frequency tracking helps maintain window validity
 * 
 * Approach:
 * 1. Use frequency map to track required characters from pattern
 * 2. Expand window by adding characters (right pointer)
 * 3. When all characters are found, try to minimize window (left pointer)
 * 4. Track minimum valid window seen so far
 * 
 * Key Points:
 * - patternMap tracks both required characters and their frequencies
 * - matched variable ensures all characters are found with correct frequencies
 * - Negative values in patternMap indicate excess characters in window
 * 
 * Time Complexity: O(n), where n is length of input string
 * - Single pass through the string with two pointers
 * 
 * Space Complexity: O(k), where k is size of character set
 * - Fixed size array for character frequency tracking (128 ASCII)
 */
public class MinWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";
        
        // Create frequency maps for pattern characters
        int[] patternMap = new int[128];
        for (char c : t.toCharArray()) {
            patternMap[c]++;
        }
        
        int start = 0, minStart = 0;
        int minLength = Integer.MAX_VALUE;
        int matched = 0;
        
        // Sliding window
        for (int end = 0; end < s.length(); end++) {
            char rightChar = s.charAt(end);
            
            // Process current character
            patternMap[rightChar]--;
            if (patternMap[rightChar] >= 0) {
                matched++;
            }
            
            // Try to minimize window
            while (matched == t.length()) {
                // Update minimum window
                if (end - start + 1 < minLength) {
                    minStart = start;
                    minLength = end - start + 1;
                }
                
                // Remove leftmost character
                char leftChar = s.charAt(start);
                patternMap[leftChar]++;
                if (patternMap[leftChar] > 0) {
                    matched--;
                }
                start++;
            }
        }
        
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength);
    }
    
    public static void main(String[] args) {
        MinWindowSubstring solution = new MinWindowSubstring();
        
        // Test cases
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC")); // Expected: "BANC"
        System.out.println(solution.minWindow("a", "a")); // Expected: "a"
        System.out.println(solution.minWindow("a", "aa")); // Expected: ""
    }
}
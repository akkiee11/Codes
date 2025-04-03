/**
 * Minimum Window Substring
 * 
 * Problem Description:
 * Given two strings s and t, return the minimum window substring of s that contains
 * all characters in t. If there is no such substring, return the empty string "".
 * 
 * Example 1:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * 
 * Example 2:
 * Input: s = "a", t = "a"
 * Output: "a"
 * 
 * Example 3:
 * Input: s = "a", t = "aa"
 * Output: ""
 * 
 * Constraints:
 * - 1 <= s.length, t.length <= 10^5
 * - s and t consist of uppercase and lowercase English letters
 * 
 * Approach:
 * Two Pointers with Sliding Window and Frequency Map:
 * - Create frequency maps for pattern string and window
 * - Use two pointers (left and right) to maintain a sliding window
 * - Expand window to right until all characters are found
 * - Contract window from left to minimize while maintaining valid window
 * - Time Complexity: O(n), Space Complexity: O(k) where k is character set size
 */

import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstring {
    
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        
        // Create frequency map for pattern string t
        Map<Character, Integer> patternMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            patternMap.put(c, patternMap.getOrDefault(c, 0) + 1);
        }
        
        // Initialize variables for sliding window
        Map<Character, Integer> windowMap = new HashMap<>();
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;
        int required = patternMap.size();
        int formed = 0;
        
        // Process the string s using sliding window
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            // Add current character to window map
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
            
            // Check if current character forms a valid count
            if (patternMap.containsKey(c) && windowMap.get(c).equals(patternMap.get(c))) {
                formed++;
            }
            
            // Try to minimize window by moving left pointer
            while (left <= right && formed == required) {
                // Update minimum window size if current window is smaller
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }
                
                // Remove leftmost character from window
                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                
                // Check if removing character breaks the valid window
                if (patternMap.containsKey(leftChar) && 
                    windowMap.get(leftChar) < patternMap.get(leftChar)) {
                    formed--;
                }
                
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
    
    public static void main(String[] args) {
        // Test cases
        String[][] testCases = {
            {"ADOBECODEBANC", "ABC"},  // Standard case
            {"a", "a"},                // Single character
            {"a", "aa"},               // Impossible case
            {"ABCDEFGHIJK", "DEF"},    // Consecutive characters
            {"aaaaaaaaaa", "aa"},      // Repeated characters
            {"ABCDEFGHIJKLMN", "ZXY"}  // No valid window
        };
        
        // Run test cases
        for (int i = 0; i < testCases.length; i++) {
            String s = testCases[i][0];
            String t = testCases[i][1];
            System.out.println("\nTest Case " + (i + 1) + ":");
            System.out.println("String s: " + s);
            System.out.println("String t: " + t);
            String result = minWindow(s, t);
            System.out.println("Minimum Window: " + (result.isEmpty() ? "No valid window" : result));
        }
    }
}
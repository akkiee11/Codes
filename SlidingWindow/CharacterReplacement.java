/**
 * Problem: Longest Repeating Character Replacement
 * Given a string s and an integer k, find the length of the longest substring containing
 * the same letter after replacing at most k characters.
 *
 * Example:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa to get "BBBB" or "AAAA".
 *
 * Intuition:
 * - Use sliding window to track character frequencies in current window
 * - For each window, find the most frequent character
 * - If (window size - count of most frequent char) <= k, we can make all chars same
 * - Keep track of the maximum valid window size
 *
 * Approach: Sliding Window with Character Frequency
 * Time Complexity: O(n) where n is length of string
 * Space Complexity: O(1) since we only store 26 characters
 */
public class CharacterReplacement {
    public static int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        
        // Array to store frequency of characters in current window
        int[] freq = new int[26];
        int maxCount = 0;    // Count of most frequent character
        int maxLength = 0;
        int left = 0;
        
        // Iterate through the string using right pointer
        for (int right = 0; right < s.length(); right++) {
            // Update frequency of current character
            freq[s.charAt(right) - 'A']++;
            
            // Update maxCount if current character appears more frequently
            maxCount = Math.max(maxCount, freq[s.charAt(right) - 'A']);
            
            // If window size - count of most frequent char > k
            // we need to shrink window from left
            int windowSize = right - left + 1;
            if (windowSize - maxCount > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            
            // Update maxLength
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    public static void main(String[] args) {
        // Test cases
        String[] strings = {"ABAB", "AABABBA", "AAAA", "ABCDE"};
        int[] kValues = {2, 1, 2, 1};
        
        for (int i = 0; i < strings.length; i++) {
            System.out.println("Input: s = " + strings[i] + ", k = " + kValues[i]);
            System.out.println("Length of longest substring: " + 
                             characterReplacement(strings[i], kValues[i]));
            System.out.println();
        }
    }
}
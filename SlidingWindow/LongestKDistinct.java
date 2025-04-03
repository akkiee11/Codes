import java.util.*;

/**
 * Problem: Find the longest substring with at most K distinct characters
 * Approach: Variable size sliding window with HashMap for character frequency
 * Time Complexity: O(n), where n is length of input string
 * Space Complexity: O(k), where k is the number of distinct characters allowed
 */
public class LongestKDistinct {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) return 0;
        
        // Map to store character frequencies
        Map<Character, Integer> charFreq = new HashMap<>();
        int maxLength = 0;
        int start = 0;
        
        // Sliding window
        for (int end = 0; end < s.length(); end++) {
            char rightChar = s.charAt(end);
            charFreq.put(rightChar, charFreq.getOrDefault(rightChar, 0) + 1);
            
            // Shrink window while we have more than k distinct characters
            while (charFreq.size() > k) {
                char leftChar = s.charAt(start);
                charFreq.put(leftChar, charFreq.get(leftChar) - 1);
                if (charFreq.get(leftChar) == 0) {
                    charFreq.remove(leftChar);
                }
                start++;
            }
            
            // Update maximum length
            maxLength = Math.max(maxLength, end - start + 1);
        }
        
        return maxLength;
    }
    
    public static void main(String[] args) {
        LongestKDistinct solution = new LongestKDistinct();
        
        // Test cases
        System.out.println(solution.lengthOfLongestSubstringKDistinct("eceba", 2)); // Expected: 3
        System.out.println(solution.lengthOfLongestSubstringKDistinct("aa", 1)); // Expected: 2
        System.out.println(solution.lengthOfLongestSubstringKDistinct("abaccc", 2)); // Expected: 4
    }
}
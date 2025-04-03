import java.util.*;

/**
 * Reorganize String
 *
 * Problem Statement:
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 * Return any possible rearrangement of s or return "" if not possible.
 *
 * Constraints:
 * - 1 <= s.length <= 500
 * - s consists of lowercase English letters
 *
 * Approaches:
 * 1. Max Heap (Current Implementation)
 *    - Time Complexity: O(n log k) where n is string length, k is unique chars
 *    - Space Complexity: O(k) for storing the heap
 *    - Intuition: Use max heap to always pick most frequent remaining char
 *
 * 2. Greedy with Sorting (Alternative)
 *    - Time Complexity: O(n)
 *    - Space Complexity: O(k)
 *    - Intuition: Place most frequent char at even indices, then fill others
 *
 * 3. Count and Place (Alternative)
 *    - Time Complexity: O(n)
 *    - Space Complexity: O(k)
 *    - Intuition: Count frequencies, place chars alternately if possible
 */
public class ReorganizeString {
    public String reorganizeString(String s) {
        // Count character frequencies
        int[] frequencies = new int[26];
        for (char c : s.toCharArray()) {
            frequencies[c - 'a']++;
            // If any character appears more than half the string length, impossible to reorganize
            if (frequencies[c - 'a'] > (s.length() + 1) / 2) {
                return "";
            }
        }
        
        // Create max heap based on character frequencies
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < 26; i++) {
            if (frequencies[i] > 0) {
                maxHeap.offer(new int[]{i, frequencies[i]});
            }
        }
        
        StringBuilder result = new StringBuilder();
        while (maxHeap.size() >= 2) {
            // Take two most frequent characters
            int[] first = maxHeap.poll();
            int[] second = maxHeap.poll();
            
            // Add them to result
            result.append((char) (first[0] + 'a'));
            result.append((char) (second[0] + 'a'));
            
            // Decrease frequencies and add back to heap if needed
            if (--first[1] > 0) maxHeap.offer(first);
            if (--second[1] > 0) maxHeap.offer(second);
        }
        
        // Handle last character if exists
        if (!maxHeap.isEmpty()) {
            result.append((char) (maxHeap.poll()[0] + 'a'));
        }
        
        return result.toString();
    }
    
    // Test the implementation
    public static void main(String[] args) {
        ReorganizeString solution = new ReorganizeString();
        
        // Test case 1: Regular case
        String s1 = "aab";
        System.out.println("Test case 1: " + solution.reorganizeString(s1));
        
        // Test case 2: Impossible case
        String s2 = "aaab";
        System.out.println("Test case 2: " + solution.reorganizeString(s2));
        
        // Test case 3: Complex case
        String s3 = "vvvlo";
        System.out.println("Test case 3: " + solution.reorganizeString(s3));
    }
}
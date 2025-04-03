/**
 * Problem: Find All Anagrams in a String
 * Approach: Sliding Window with Character Frequency Matching
 * Time Complexity: O(n)
 * Space Complexity: O(1) since we only store 26 characters
 */
public class FindAllAnagrams {
    public static java.util.List<Integer> findAnagrams(String s, String p) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) return result;
        
        // Arrays to store character frequencies
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        
        // Build frequency map for pattern and initial window
        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }
        
        // Check if initial window is an anagram
        if (matches(pCount, sCount)) {
            result.add(0);
        }
        
        // Slide the window
        for (int i = p.length(); i < s.length(); i++) {
            // Add new character to window
            sCount[s.charAt(i) - 'a']++;
            // Remove character from start of window
            sCount[s.charAt(i - p.length()) - 'a']--;
            
            // Check if current window is an anagram
            if (matches(pCount, sCount)) {
                result.add(i - p.length() + 1);
            }
        }
        
        return result;
    }
    
    // Helper method to check if frequency maps match
    private static boolean matches(int[] pCount, int[] sCount) {
        for (int i = 0; i < 26; i++) {
            if (pCount[i] != sCount[i]) return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        // Test cases
        String[][] tests = {
            {"cbaebabacd", "abc"}, // Expected: [0, 6]
            {"abab", "ab"},        // Expected: [0, 1, 2]
            {"baa", "aa"},         // Expected: [1]
            {"aaaaaaaaaa", "aaaa"} // Expected: [0,1,2,3,4,5,6]
        };
        
        for (String[] test : tests) {
            System.out.println("String: " + test[0]);
            System.out.println("Pattern: " + test[1]);
            System.out.println("Anagram indices: " + 
                             findAnagrams(test[0], test[1]));
            System.out.println();
        }
    }
}
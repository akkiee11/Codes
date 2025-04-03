/**
 * Problem: Permutation in String
 * Approach: Sliding Window with Character Frequency Matching
 * Time Complexity: O(n)
 * Space Complexity: O(1) since we only store 26 characters
 */
public class PermutationInString {
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        
        // Arrays to store character frequencies
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        
        // Build frequency map for s1 and initial window of s2
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        
        // Check if initial window is a permutation
        if (matches(s1map, s2map)) return true;
        
        // Slide the window
        for (int i = s1.length(); i < s2.length(); i++) {
            // Add new character to window
            s2map[s2.charAt(i) - 'a']++;
            // Remove character from start of window
            s2map[s2.charAt(i - s1.length()) - 'a']--;
            
            // Check if current window is a permutation
            if (matches(s1map, s2map)) return true;
        }
        
        return false;
    }
    
    // Helper method to check if frequency maps match
    private static boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i]) return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        // Test cases
        String[][] tests = {
            {"ab", "eidbaooo"},    // Expected: true
            {"ab", "eidboaoo"},    // Expected: false
            {"abc", "bbbca"},      // Expected: true
            {"hello", "ooolleoooleh"} // Expected: false
        };
        
        for (String[] test : tests) {
            System.out.println("s1: " + test[0]);
            System.out.println("s2: " + test[1]);
            System.out.println("Contains permutation: " + 
                             checkInclusion(test[0], test[1]));
            System.out.println();
        }
    }
}
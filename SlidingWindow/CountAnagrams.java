/**
 * Count Occurrences of Anagrams
 * Problem: Given a text and a pattern, find the count of occurrences of anagrams of the pattern in the text.
 *
 * Example:
 * Input: text = "forxxorfxdofr", pattern = "for"
 * Output: 3
 * Explanation: Anagrams of pattern "for" appear in text as: "for", "orf", "ofr"
 *
 * Time Complexity: O(n) where n is the length of text
 * Space Complexity: O(1) as we use fixed size array for character count
 */
public class CountAnagrams {
    public static int findAnagramsCount(String text, String pattern) {
        if (text == null || pattern == null || pattern.length() > text.length()) {
            return 0;
        }

        // Create frequency arrays for pattern and current window
        int[] patternFreq = new int[26];
        int[] windowFreq = new int[26];

        // Fill pattern frequency array
        for (char c : pattern.toCharArray()) {
            patternFreq[c - 'a']++;
        }

        int windowSize = pattern.length();
        int count = 0;

        // Process first window
        for (int i = 0; i < windowSize; i++) {
            windowFreq[text.charAt(i) - 'a']++;
        }

        // Check first window
        if (matches(windowFreq, patternFreq)) {
            count++;
        }

        // Slide window and process rest of the text
        for (int i = windowSize; i < text.length(); i++) {
            // Remove first character of previous window
            windowFreq[text.charAt(i - windowSize) - 'a']--;
            // Add last character of current window
            windowFreq[text.charAt(i) - 'a']++;

            // Check if current window is anagram
            if (matches(windowFreq, patternFreq)) {
                count++;
            }
        }

        return count;
    }

    private static boolean matches(int[] arr1, int[] arr2) {
        for (int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test Case 1
        String text1 = "forxxorfxdofr";
        String pattern1 = "for";
        System.out.println("Test Case 1: " + findAnagramsCount(text1, pattern1)); // Expected: 3

        // Test Case 2
        String text2 = "aabaabaa";
        String pattern2 = "aaba";
        System.out.println("Test Case 2: " + findAnagramsCount(text2, pattern2)); // Expected: 4

        // Test Case 3
        String text3 = "abc";
        String pattern3 = "def";
        System.out.println("Test Case 3: " + findAnagramsCount(text3, pattern3)); // Expected: 0
    }
}
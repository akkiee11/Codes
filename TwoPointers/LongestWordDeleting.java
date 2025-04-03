/**
 * Longest Word in Dictionary through Deleting
 * 
 * Problem Description:
 * Given a string s and a dictionary of strings dictionary, find the longest string in the dictionary
 * that can be formed by deleting some characters of the given string s. If there are multiple possible
 * answers, return the longest word with the smallest lexicographical order. If there is no possible
 * answer, return the empty string.
 * 
 * Example 1:
 * Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * Output: "apple"
 * 
 * Example 2:
 * Input: s = "abpcplea", dictionary = ["a","b","c"]
 * Output: "a"
 * 
 * Constraints:
 * - 1 <= s.length <= 1000
 * - 1 <= dictionary.length <= 1000
 * - 1 <= dictionary[i].length <= 1000
 * - s and dictionary[i] consist of lowercase English letters
 * 
 * Approach:
 * Two Pointer Technique:
 * 1. For each word in dictionary, check if it's a subsequence of s
 * 2. Among all subsequences, find the longest one
 * 3. If multiple words have same length, return lexicographically smallest
 * - Time Complexity: O(n * x) where n is dictionary size and x is average word length
 * - Space Complexity: O(1)
 */

import java.util.*;

public class LongestWordDeleting {
    
    public static String findLongestWord(String s, List<String> dictionary) {
        String result = "";
        
        for (String word : dictionary) {
            // Check if word is a subsequence of s
            if (isSubsequence(word, s)) {
                // Update result if current word is longer or
                // same length but lexicographically smaller
                if (word.length() > result.length() ||
                    (word.length() == result.length() && word.compareTo(result) < 0)) {
                    result = word;
                }
            }
        }
        
        return result;
    }
    
    private static boolean isSubsequence(String word, String s) {
        if (word.length() > s.length()) return false;
        
        int i = 0; // pointer for word
        int j = 0; // pointer for s
        
        while (i < word.length() && j < s.length()) {
            if (word.charAt(i) == s.charAt(j)) {
                i++;
            }
            j++;
        }
        
        return i == word.length();
    }
    
    public static void main(String[] args) {
        // Test cases
        String[] strings = {
            "abpcplea",           // Standard case
            "abpcplea",           // Multiple possible answers
            "helloworld",         // No match
            "aaa",               // Multiple same characters
            "abcdefghijklmn"     // Long string
        };
        
        List<List<String>> dictionaries = Arrays.asList(
            Arrays.asList("ale", "apple", "monkey", "plea"),          // Should return "apple"
            Arrays.asList("a", "b", "c"),                            // Should return "a"
            Arrays.asList("xyz", "pqr"),                             // Should return ""
            Arrays.asList("a", "aa", "aaa"),                         // Should return "aaa"
            Arrays.asList("abc", "abcd", "abcde", "abcdef")         // Should return "abcdef"
        );
        
        // Run test cases
        for (int i = 0; i < strings.length; i++) {
            System.out.println("\nTest Case " + (i + 1) + ":");
            System.out.println("String: " + strings[i]);
            System.out.println("Dictionary: " + dictionaries.get(i));
            String result = findLongestWord(strings[i], dictionaries.get(i));
            System.out.println("Longest word: " + result);
        }
    }
}
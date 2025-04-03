import java.util.*;

/**
 * Substring with Concatenation of All Words
 * Problem: Find all starting indices of substring(s) that is a concatenation of all given words.
 * Each word has the same length, and words can be reused multiple times.
 *
 * Intuition:
 * - Since all words have the same length, any valid concatenation will have a fixed total length
 * - We can use a sliding window of this fixed size to check each possible starting position
 * - For each window, we need to:
 *   1. Split it into word-sized chunks
 *   2. Compare frequency of words with the required frequency
 * - Using a HashMap to track word frequencies makes the comparison efficient
 *
 * Approach:
 * 1. Create a frequency map of required words
 * 2. For each possible starting position i:
 *    - Create a new frequency map for current window
 *    - Split window into word-sized chunks
 *    - Check if each chunk is a valid word and has correct frequency
 *    - If all words match, add index i to result
 * 3. Key optimization: Break early if:
 *    - A word is not in the pattern
 *    - A word appears more times than required
 *
 * Example:
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 * Explanation:
 * - At index 0: "barfoo" = "bar" + "foo"
 * - At index 9: "foobar" = "foo" + "bar"
 *
 * Time Complexity: O(N * M * K) where:
 * - N is string length
 * - M is word length
 * - K is number of words
 * - For each starting position, we need to check K words of length M
 *
 * Space Complexity: O(K) where K is number of words
 * - We store word frequencies in HashMaps
 * - Size of maps is proportional to number of unique words
 */
public class SubstringConcatenation {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        
        // Initialize word frequency map
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }
        
        int wordLength = words[0].length();
        int totalWords = words.length;
        int totalLength = wordLength * totalWords;
        
        // Check each possible starting position
        for (int i = 0; i <= s.length() - totalLength; i++) {
            Map<String, Integer> seenWords = new HashMap<>();
            int j;
            
            // Check each word in the window
            for (j = 0; j < totalWords; j++) {
                int wordStart = i + j * wordLength;
                String currentWord = s.substring(wordStart, wordStart + wordLength);
                
                // If word is not in pattern
                if (!wordFreq.containsKey(currentWord)) {
                    break;
                }
                
                // Add to seen words
                seenWords.put(currentWord, seenWords.getOrDefault(currentWord, 0) + 1);
                
                // Check if word frequency exceeds pattern
                if (seenWords.get(currentWord) > wordFreq.get(currentWord)) {
                    break;
                }
            }
            
            // If all words matched
            if (j == totalWords) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        SubstringConcatenation solution = new SubstringConcatenation();
        
        // Test cases
        String s1 = "barfoothefoobarman";
        String[] words1 = {"foo","bar"};
        System.out.println(solution.findSubstring(s1, words1)); // Expected: [0, 9]
        
        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word","good","best","word"};
        System.out.println(solution.findSubstring(s2, words2)); // Expected: []
        
        String s3 = "barfoofoobarthefoobarman";
        String[] words3 = {"bar","foo","the"};
        System.out.println(solution.findSubstring(s3, words3)); // Expected: [6, 9, 12]
    }
}
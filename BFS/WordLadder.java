import java.util.*;

public class WordLadder {
    
    /*
     * Problem Statement:
     * Given two words, beginWord and endWord, and a dictionary wordList, return the length of the shortest
     * transformation sequence from beginWord to endWord such that:
     * - Only one letter can be changed at a time
     * - Each transformed word must exist in the word list
     * Return 0 if there is no such transformation sequence.
     * 
     * Example 1:
     * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     * Output: 5
     * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * return its length 5.
     * 
     * Example 2:
     * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
     * Output: 0
     * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
     * 
     * Constraints:
     * - 1 <= beginWord.length <= 10
     * - endWord.length == beginWord.length
     * - 1 <= wordList.length <= 5000
     * - wordList[i].length == beginWord.length
     * - beginWord, endWord, and wordList[i] consist of lowercase English letters
     * - beginWord != endWord
     * - All the words in wordList are unique
     * 
     * Intuition:
     * - Use BFS to find shortest transformation sequence
     * - Each word represents a node in the graph
     * - Two words are connected if they differ by one letter
     * - BFS ensures we find the shortest path first
     * 
     * Approach:
     * 1. Convert wordList to HashSet for O(1) lookups
     * 2. Use BFS starting from beginWord:
     *    - For each word, try changing each character
     *    - Add valid transformations to queue
     *    - Track visited words to avoid cycles
     * 3. Return level + 1 when endWord is found
     * 
     * Time Complexity: O(N * 26 * L^2), where N is dictionary size, L is word length
     * Space Complexity: O(N), for the queue and visited set
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert wordList to a HashSet for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordList);
        
        // If endWord is not in the dictionary, return 0
        if (!wordSet.contains(endWord)) return 0;
        
        // Queue for BFS traversal
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        // Set to keep track of visited words
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        
        // Length of transformation sequence
        int level = 1;
        
        // BFS traversal
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Process all words at current level
            for (int i = 0; i < levelSize; i++) {
                String currentWord = queue.poll();
                
                // Try all possible one-letter transformations
                char[] wordChars = currentWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];
                    
                    // Try replacing with each letter from 'a' to 'z'
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue; // Skip if same character
                        
                        wordChars[j] = c;
                        String transformedWord = new String(wordChars);
                        
                        // If we've reached the end word, return the level + 1
                        if (transformedWord.equals(endWord)) {
                            return level + 1;
                        }
                        
                        // If the transformed word is in the dictionary and not visited
                        if (wordSet.contains(transformedWord) && !visited.contains(transformedWord)) {
                            queue.offer(transformedWord);
                            visited.add(transformedWord);
                        }
                    }
                    
                    // Restore original character for next iteration
                    wordChars[j] = originalChar;
                }
            }
            
            // Increment level after processing all words at current level
            level++;
        }
        
        // If we can't reach the end word
        return 0;
    }
    
    public static void main(String[] args) {
        // Test case 1
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println("Test case 1 - Ladder length: " + 
                          ladderLength(beginWord1, endWord1, wordList1)); // Expected: 5
        
        // Test case 2
        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        System.out.println("Test case 2 - Ladder length: " + 
                          ladderLength(beginWord2, endWord2, wordList2)); // Expected: 0
    }
}
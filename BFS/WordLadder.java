import java.util.*;

public class WordLadder {
    
    // Problem: Find shortest transformation sequence
    // Explanation: This problem involves finding the shortest transformation sequence from a start word to an end word, using a dictionary of valid words. BFS is used to explore all possible transformations level by level, ensuring the shortest path is found.
    // Time Complexity: O(n * m^2), where n is the number of words in the dictionary and m is the length of each word.
    // Space Complexity: O(n * m), due to the space required for the queue and visited set.
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
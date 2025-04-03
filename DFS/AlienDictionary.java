/**
 * Alien Dictionary
 *
 * Problem: Given a sorted dictionary of an alien language, find the order of characters in the alphabet.
 * The dictionary is sorted lexicographically by the rules of this new language.
 *
 * Example 1:
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 *
 * Example 2:
 * Input: words = ["z","x"]
 * Output: "zx"
 *
 * Example 3:
 * Input: words = ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 *
 * Approach:
 * 1. Build a graph from character relationships in adjacent words
 * 2. Use DFS for topological sort
 * 3. Handle cycles (invalid order)
 * 4. Track visited nodes to detect cycles
 */

import java.util.*;

public class AlienDictionary {
    private Map<Character, Set<Character>> graph;
    private Map<Character, Integer> visited;
    private StringBuilder result;
    
    public String alienOrder(String[] words) {
        // Initialize data structures
        graph = new HashMap<>();
        visited = new HashMap<>();
        result = new StringBuilder();
        
        // Add all characters to the graph
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }
        
        // Build graph from adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            
            // Check if word2 is a prefix of word1
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";
            }
            
            // Find first different character
            int len = Math.min(word1.length(), word2.length());
            for (int j = 0; j < len; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    break;
                }
            }
        }
        
        // DFS for each character
        for (char c : graph.keySet()) {
            if (dfs(c)) return ""; // Cycle detected
        }
        
        return result.reverse().toString();
    }
    
    private boolean dfs(char c) {
        if (visited.containsKey(c)) {
            return visited.get(c) == 1; // 1 means being visited (cycle)
        }
        
        visited.put(c, 1); // Mark as being visited
        
        for (char next : graph.get(c)) {
            if (dfs(next)) return true;
        }
        
        visited.put(c, 2); // Mark as visited
        result.append(c);
        return false;
    }
    
    public static void main(String[] args) {
        AlienDictionary solution = new AlienDictionary();
        
        // Test Case 1
        String[] words1 = {"wrt","wrf","er","ett","rftt"};
        System.out.println("Test Case 1 Result: " + 
                          solution.alienOrder(words1)); // Expected: "wertf"
        
        // Test Case 2
        String[] words2 = {"z","x"};
        System.out.println("Test Case 2 Result: " + 
                          solution.alienOrder(words2)); // Expected: "zx"
        
        // Test Case 3
        String[] words3 = {"z","x","z"};
        System.out.println("Test Case 3 Result: " + 
                          solution.alienOrder(words3)); // Expected: ""
    }
}
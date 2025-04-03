import java.util.*;

public class MinimumGeneticMutation {
    
    /*
     * Problem Statement:
     * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
     * Given a start gene string, an end gene string, and a bank of valid gene mutations, return the minimum number
     * of mutations needed to mutate from start to end. If there is no such a mutation, return -1.
     * Note that the starting point is assumed to be valid, but the end point must exist in the bank.
     * 
     * Example 1:
     * Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
     * Output: 1
     * Explanation: The only mutation needed is to change the last character 'T' to 'A'.
     * 
     * Example 2:
     * Input: start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
     * Output: 2
     * Explanation: One optimal path is "AACCGGTT" -> "AACCGGTA" -> "AAACGGTA".
     * 
     * Example 3:
     * Input: start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
     * Output: 3
     * 
     * Constraints:
     * - start.length == 8
     * - end.length == 8
     * - 0 <= bank.length <= 10
     * - bank[i].length == 8
     * - start, end, and bank[i] consist of only the characters ['A', 'C', 'G', 'T']
     * 
     * Intuition:
     * - Use BFS to find the shortest path from start to end gene
     * - Each mutation represents an edge in the graph
     * - Valid mutations must exist in the bank
     * - BFS ensures we find the minimum number of mutations
     * 
     * Approach:
     * 1. Convert bank to HashSet for O(1) lookup
     * 2. Use BFS with a queue to try all possible mutations:
     *    - For each position in the gene string
     *    - Try replacing with each valid character (A, C, G, T)
     *    - If new mutation is valid and not visited:
     *      a. Add to queue for further exploration
     *      b. Mark as visited to avoid cycles
     * 3. Track mutation count at each level
     * 4. Return -1 if end gene cannot be reached
     * 
     * Time Complexity: O(N * 4^8), where N is the length of bank
     * Space Complexity: O(N), for the queue and visited set
     */
    public static int minMutation(String start, String end, String[] bank) {
        // If end string is not in the bank, mutation is impossible
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if (!bankSet.contains(end)) return -1;
        
        // Characters that can be used for mutation
        char[] validChars = {'A', 'C', 'G', 'T'};
        
        // Queue for BFS traversal
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(start);
        visited.add(start);
        int mutations = 0;
        
        // BFS traversal
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Process all genes at current mutation level
            for (int i = 0; i < levelSize; i++) {
                String current = queue.poll();
                
                // If we've reached the end gene, return the number of mutations
                if (current.equals(end)) {
                    return mutations;
                }
                
                // Try all possible one-character mutations
                char[] currentArray = current.toCharArray();
                for (int j = 0; j < currentArray.length; j++) {
                    char originalChar = currentArray[j];
                    
                    // Try replacing with each valid character
                    for (char c : validChars) {
                        if (c == originalChar) continue; // Skip if same character
                        
                        currentArray[j] = c;
                        String mutated = new String(currentArray);
                        
                        // If mutation is valid and not visited, add to queue
                        if (bankSet.contains(mutated) && !visited.contains(mutated)) {
                            queue.offer(mutated);
                            visited.add(mutated);
                        }
                    }
                    
                    // Restore original character for next iteration
                    currentArray[j] = originalChar;
                }
            }
            
            // Increment mutation count after processing all genes at current level
            mutations++;
        }
        
        // If we can't reach the end gene
        return -1;
    }
    
    public static void main(String[] args) {
        // Test case 1
        String start1 = "AACCGGTT";
        String end1 = "AACCGGTA";
        String[] bank1 = {"AACCGGTA"};
        System.out.println("Test case 1 - Minimum mutations: " + 
                          minMutation(start1, end1, bank1)); // Expected: 1
        
        // Test case 2
        String start2 = "AACCGGTT";
        String end2 = "AAACGGTA";
        String[] bank2 = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        System.out.println("Test case 2 - Minimum mutations: " + 
                          minMutation(start2, end2, bank2)); // Expected: 2
        
        // Test case 3
        String start3 = "AAAAACCC";
        String end3 = "AACCCCCC";
        String[] bank3 = {"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        System.out.println("Test case 3 - Minimum mutations: " + 
                          minMutation(start3, end3, bank3)); // Expected: 3
    }
}
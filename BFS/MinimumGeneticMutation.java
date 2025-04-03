import java.util.*;

public class MinimumGeneticMutation {
    
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
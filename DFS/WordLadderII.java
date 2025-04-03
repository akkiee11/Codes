/**
 * Word Ladder II
 * 
 * Problem: Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation
 * sequences from beginWord to endWord, such that:
 * 1. Only one letter can be changed at a time
 * 2. Each transformed word must exist in the word list
 *
 * Example:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"]]
 *
 * Approach:
 * 1. Use bidirectional BFS to find the shortest path length
 * 2. Build a graph of word transformations
 * 3. Use DFS to find all paths of the shortest length
 * 4. Optimize by pre-processing word patterns
 */

import java.util.*;

class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;
        
        // Remove beginWord from dictionary
        dict.remove(beginWord);
        
        // Distances of words from beginWord
        Map<String, Integer> distance = new HashMap<>();
        // Graph for word transformations
        Map<String, List<String>> neighbors = new HashMap<>();
        
        distance.put(beginWord, 0);
        
        // BFS to build graph and find distances
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        // Build the graph layer by layer
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                int currDistance = distance.get(word);
                
                // Try all possible transformations
                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String newWord = new String(chars);
                        
                        if (newWord.equals(endWord)) {
                            foundEnd = true;
                        }
                        
                        if (dict.contains(newWord)) {
                            // Add to neighbors graph
                            neighbors.computeIfAbsent(word, k -> new ArrayList<>()).add(newWord);
                            
                            if (!distance.containsKey(newWord)) {
                                distance.put(newWord, currDistance + 1);
                                queue.offer(newWord);
                            }
                        }
                    }
                    chars[j] = original;
                }
            }
            if (foundEnd) break;
        }
        
        // DFS to find all shortest paths
        if (distance.containsKey(endWord)) {
            List<String> path = new ArrayList<>();
            path.add(beginWord);
            dfs(beginWord, endWord, neighbors, distance, path, res);
        }
        
        return res;
    }
    
    private void dfs(String word, String endWord, Map<String, List<String>> neighbors,
                    Map<String, Integer> distance, List<String> path, List<List<String>> res) {
        if (word.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        if (!neighbors.containsKey(word)) return;
        
        for (String neighbor : neighbors.get(word)) {
            if (distance.get(neighbor) == distance.get(word) + 1) {
                path.add(neighbor);
                dfs(neighbor, endWord, neighbors, distance, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    // Time Complexity: O(N * 26 * L) for BFS where N is dictionary size and L is word length
    // Space Complexity: O(N) for the graph and distance map
    public static void main(String[] args) {
        WordLadderII solution = new WordLadderII();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        
        List<List<String>> result = solution.findLadders(beginWord, endWord, wordList);
        System.out.println("All shortest transformation sequences:");
        for (List<String> path : result) {
            System.out.println(path);
        }
    }
}
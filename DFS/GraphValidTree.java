/**
 * Graph Valid Tree
 *
 * Problem: Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 *
 * For a graph to be a valid tree, it must:
 * 1. Be fully connected (all nodes can be reached from any other node)
 * 2. Have no cycles
 * 3. Have exactly n-1 edges where n is the number of nodes
 *
 * Example 1:
 * Input: n = 5, edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 *
 * Example 2:
 * Input: n = 5, edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 * Explanation: The graph has a cycle: 1-2-3-1
 *
 * Approach:
 * 1. First check if number of edges is exactly n-1
 * 2. Build an adjacency list representation of the graph
 * 3. Use DFS to detect cycles and check if all nodes are connected
 * 4. Track parent during DFS to handle undirected edges
 * Time Complexity: O(V + E) where V is number of vertices and E is number of edges
 * Space Complexity: O(V) for the visited set and recursion stack
 */

import java.util.*;

public class GraphValidTree {
    private List<List<Integer>> graph;
    private Set<Integer> visited;
    
    public boolean validTree(int n, int[][] edges) {
        // Check if number of edges is exactly n-1
        if (edges.length != n - 1) return false;
        
        // Build the graph (adjacency list)
        buildGraph(n, edges);
        
        // DFS from node 0
        visited = new HashSet<>();
        if (!dfs(0, -1)) return false;
        
        // Check if all nodes are connected
        return visited.size() == n;
    }
    
    private void buildGraph(int n, int[][] edges) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Add undirected edges
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
    }
    
    private boolean dfs(int node, int parent) {
        visited.add(node);
        
        for (int neighbor : graph.get(node)) {
            // Skip the parent node (since graph is undirected)
            if (neighbor == parent) continue;
            
            // If we find a visited node that's not the parent, we have a cycle
            if (visited.contains(neighbor)) return false;
            
            // Recursively check neighbors
            if (!dfs(neighbor, node)) return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        GraphValidTree solution = new GraphValidTree();
        
        // Test Case 1: Valid Tree
        int[][] edges1 = {{0,1}, {0,2}, {0,3}, {1,4}};
        System.out.println("Test Case 1: " + solution.validTree(5, edges1));
        
        // Test Case 2: Invalid Tree (has cycle)
        int[][] edges2 = {{0,1}, {1,2}, {2,3}, {1,3}, {1,4}};
        System.out.println("Test Case 2: " + solution.validTree(5, edges2));
        
        // Test Case 3: Invalid Tree (disconnected)
        int[][] edges3 = {{0,1}, {2,3}};
        System.out.println("Test Case 3: " + solution.validTree(4, edges3));
    }
}
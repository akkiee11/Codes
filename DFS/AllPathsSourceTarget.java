/**
 * Problem: All Paths from Source to Target
 * 
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths
 * from node 0 to node n - 1 and return them in any order.
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i
 * (i.e., there is a directed edge from node i to node graph[i][j]).
 * 
 * Example:
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * 
 * Visual representation:
 *    1 -> 3
 *  /     ^
 * 0      |
 *  \     |
 *    2 ---
 * 
 * Time Complexity: O(2^N * N) where N is the number of nodes
 * Space Complexity: O(N) for the recursion stack
 */

import java.util.*;

public class AllPathsSourceTarget {
    private List<List<Integer>> result;
    private int target;
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        result = new ArrayList<>();
        target = graph.length - 1;
        
        // Start DFS from node 0
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(0, path, graph);
        
        return result;
    }
    
    private void dfs(int node, List<Integer> path, int[][] graph) {
        // If we reached the target, add the current path to result
        if (node == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        // Explore all neighbors
        for (int nextNode : graph[node]) {
            path.add(nextNode);
            dfs(nextNode, path, graph);
            path.remove(path.size() - 1); // backtrack
        }
    }
    
    public static void main(String[] args) {
        AllPathsSourceTarget solution = new AllPathsSourceTarget();
        
        // Test Case 1
        int[][] graph1 = {{1,2}, {3}, {3}, {}};
        List<List<Integer>> paths1 = solution.allPathsSourceTarget(graph1);
        System.out.println("Test Case 1: " + paths1);
        
        // Test Case 2: More complex graph
        int[][] graph2 = {{1,2,3}, {2,3}, {3}, {}};
        List<List<Integer>> paths2 = solution.allPathsSourceTarget(graph2);
        System.out.println("Test Case 2: " + paths2);
        
        // Test Case 3: Single node graph
        int[][] graph3 = {{}};
        List<List<Integer>> paths3 = solution.allPathsSourceTarget(graph3);
        System.out.println("Test Case 3: " + paths3);
    }
}
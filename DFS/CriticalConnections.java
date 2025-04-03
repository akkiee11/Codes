/**
 * Critical Connections in Network
 * 
 * Problem: There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where
 * connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly
 * through the network. A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 * Return all critical connections in the network in any order.
 *
 * Example:
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: The connection between servers 1 and 3 is critical because if it's removed,
 * server 3 will be unable to reach servers 0 and 2.
 *
 * Approach:
 * 1. Use Tarjan's algorithm to find bridges in the graph
 * 2. Keep track of discovery time and lowest reachable vertex time
 * 3. If a vertex can't reach back to earlier vertices through alternative paths, the edge is critical
 * 4. Use DFS to explore all paths and update lowest reachable times
 */

import java.util.*;

class CriticalConnections {
    private int time = 0;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // Build adjacency list
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> conn : connections) {
            graph[conn.get(0)].add(conn.get(1));
            graph[conn.get(1)].add(conn.get(0));
        }
        
        // Arrays to keep track of discovery time and lowest reachable time
        int[] disc = new int[n];  // Discovery times
        int[] low = new int[n];   // Lowest vertex reachable
        Arrays.fill(disc, -1);    // -1 indicates not visited
        
        List<List<Integer>> result = new ArrayList<>();
        
        // Start DFS from vertex 0
        dfs(0, -1, disc, low, graph, result);
        
        return result;
    }
    
    private void dfs(int curr, int parent, int[] disc, int[] low, List<Integer>[] graph, 
                    List<List<Integer>> result) {
        // Discover current vertex
        disc[curr] = low[curr] = ++time;
        
        // Explore all adjacent vertices
        for (int next : graph[curr]) {
            if (next == parent) continue;  // Skip parent vertex
            
            if (disc[next] == -1) {  // If next vertex not visited
                dfs(next, curr, disc, low, graph, result);
                
                // Update lowest reachable time
                low[curr] = Math.min(low[curr], low[next]);
                
                // If this is a bridge (critical connection)
                if (low[next] > disc[curr]) {
                    result.add(Arrays.asList(curr, next));
                }
            } else {
                // Update lowest reachable time if already visited
                low[curr] = Math.min(low[curr], disc[next]);
            }
        }
    }

    // Time Complexity: O(V + E) where V is number of vertices and E is number of edges
    // Space Complexity: O(V) for the disc and low arrays
    public static void main(String[] args) {
        CriticalConnections solution = new CriticalConnections();
        int n = 4;
        List<List<Integer>> connections = Arrays.asList(
            Arrays.asList(0, 1),
            Arrays.asList(1, 2),
            Arrays.asList(2, 0),
            Arrays.asList(1, 3)
        );
        
        List<List<Integer>> result = solution.criticalConnections(n, connections);
        System.out.println("Critical connections: " + result);
    }
}
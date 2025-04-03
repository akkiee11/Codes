/**
 * Problem: Clone Graph
 * 
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
 * Each node in the graph contains a value (int) and a list of its neighbors (List[Node]).
 * 
 * Example:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * 
 * Visual representation:
 *       1 -- 2
 *       |    |
 *       4 -- 3
 * 
 * Time Complexity: O(N + E) where N is the number of nodes and E is the number of edges
 * Space Complexity: O(N) for the HashMap and recursion stack
 */

import java.util.*;

public class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;
        
        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }
    }
    
    private HashMap<Node, Node> visited = new HashMap<>();
    
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        // If node was already cloned, return the clone
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        
        // Create a new node with the same value
        Node cloneNode = new Node(node.val);
        visited.put(node, cloneNode);
        
        // Recursively clone all neighbors
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        
        return cloneNode;
    }
    
    public static void main(String[] args) {
        CloneGraph solution = new CloneGraph();
        
        // Create test graph: 1 -- 2
        //                    |    |
        //                    4 -- 3
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        
        // Clone the graph
        Node clonedNode = solution.cloneGraph(node1);
        
        // Verify the clone (basic check)
        System.out.println("Original node value: " + node1.val);
        System.out.println("Cloned node value: " + clonedNode.val);
        System.out.println("Number of neighbors (original): " + node1.neighbors.size());
        System.out.println("Number of neighbors (cloned): " + clonedNode.neighbors.size());
        System.out.println("Is clone different object than original: " + (node1 != clonedNode));
    }
}
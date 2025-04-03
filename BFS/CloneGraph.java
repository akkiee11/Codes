import java.util.*;

public class CloneGraph {
    // Node class definition for an undirected graph
    static class Node {
        public int val;
        public List<Node> neighbors;
        
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    
    // Problem: Clone a graph
    // Explanation: This problem involves cloning an undirected graph using BFS traversal. By visiting each node and its neighbors, we can create a deep copy of the graph efficiently.
    // Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
    // Space Complexity: O(V), due to the space required for the queue and visited map.
    public static Node cloneGraph(Node node) {
        if (node == null) return null;
        
        // Map to keep track of cloned nodes
        Map<Node, Node> visited = new HashMap<>();
        
        // Queue for BFS traversal
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        
        // Clone the first node and add to visited map
        visited.put(node, new Node(node.val));
        
        // BFS traversal
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            // Process all neighbors of current node
            for (Node neighbor : current.neighbors) {
                // If neighbor not cloned yet, clone it and add to queue
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                
                // Connect current cloned node with its cloned neighbor
                visited.get(current).neighbors.add(visited.get(neighbor));
            }
        }
        
        return visited.get(node);
    }
    
    // Helper method to create a test graph
    private static Node createTestGraph() {
        // Create a graph: 1 -- 2
        //                |    |
        //                4 -- 3
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
        
        return node1;
    }
    
    // Helper method to print graph (BFS)
    private static void printGraph(Node node) {
        if (node == null) return;
        
        Set<Integer> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited.add(node.val);
        
        System.out.println("Graph structure:");
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.val + " -> ");
            
            List<Integer> neighborVals = new ArrayList<>();
            for (Node neighbor : current.neighbors) {
                neighborVals.add(neighbor.val);
                if (!visited.contains(neighbor.val)) {
                    visited.add(neighbor.val);
                    queue.offer(neighbor);
                }
            }
            System.out.println(neighborVals);
        }
    }
    
    public static void main(String[] args) {
        // Create a test graph
        Node originalGraph = createTestGraph();
        System.out.println("Original Graph:");
        printGraph(originalGraph);
        
        // Clone the graph
        Node clonedGraph = cloneGraph(originalGraph);
        System.out.println("\nCloned Graph:");
        printGraph(clonedGraph);
        
        // Verify they are different objects
        System.out.println("\nAre original and cloned graphs the same object? " + 
                          (originalGraph == clonedGraph));
    }
}
import java.util.*;

public class CourseScheduleDFS {
    
    /*
     * Problem Statement:
     * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
     * Some courses have prerequisites, for example, to take course 1 you have to first take course 0,
     * which is expressed as a pair: [1,0]. Given the total number of courses and a list of prerequisite
     * pairs, determine if it is possible to finish all courses.
     * 
     * Example 1:
     * Input: numCourses = 2, prerequisites = [[1,0]]
     * Output: true
     * Explanation: There are 2 courses to take. To take course 1 you should have finished course 0.
     * So it is possible.
     * 
     * Example 2:
     * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
     * Output: false
     * Explanation: There are 2 courses to take. To take course 1 you should have finished course 0,
     * and to take course 0 you should have finished course 1. So it is impossible.
     * 
     * Intuition:
     * - The problem can be modeled as a directed graph where courses are vertices and prerequisites are edges
     * - A cycle in this graph means some courses cannot be completed (they depend on each other)
     * - DFS with visited and recursion stack tracking can detect cycles
     * - If we find a back edge (an edge to a vertex in current recursion stack), we have a cycle
     * 
     * Approach using DFS:
     * 1. Build adjacency list representation of the graph
     * 2. For each unvisited vertex:
     *    - Perform DFS with visited and recursion stack tracking
     *    - If we find a vertex that's in recursion stack, we have a cycle
     * 3. If no cycles found, return true
     * 
     * Time Complexity: O(V + E), where V is number of courses and E is number of prerequisites
     * Space Complexity: O(V) for recursion stack and visited arrays
     * 
     * Key Differences from BFS Approach:
     * - DFS uses recursion stack instead of queue
     * - DFS detects cycles by tracking vertices in current path
     * - DFS might be more intuitive for cycle detection
     * - BFS approach uses in-degree counting which might be easier to understand
     */
    
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create adjacency list representation of the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Build the graph
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prereq = prerequisite[1];
            graph.get(prereq).add(course); // prereq -> course
        }
        
        // Arrays to track visited vertices and recursion stack
        boolean[] visited = new boolean[numCourses];
        boolean[] recursionStack = new boolean[numCourses];
        
        // Check for cycles starting from each unvisited vertex
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && hasCycle(i, graph, visited, recursionStack)) {
                return false; // Found a cycle, cannot finish all courses
            }
        }
        
        return true; // No cycles found, can finish all courses
    }
    
    private static boolean hasCycle(int current, List<List<Integer>> graph, 
                                   boolean[] visited, boolean[] recursionStack) {
        // Mark current vertex as visited and add to recursion stack
        visited[current] = true;
        recursionStack[current] = true;
        
        // Visit all adjacent vertices
        for (int neighbor : graph.get(current)) {
            if (!visited[neighbor]) {
                if (hasCycle(neighbor, graph, visited, recursionStack)) {
                    return true; // Found a cycle in DFS subtree
                }
            } else if (recursionStack[neighbor]) {
                return true; // Found a back edge (cycle)
            }
        }
        
        // Remove current vertex from recursion stack
        recursionStack[current] = false;
        return false;
    }
    
    public static void main(String[] args) {
        // Test case 1: Can finish all courses
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}}; // Course 1 requires Course 0
        System.out.println("Test case 1 - Can finish all courses: " + 
                          canFinish(numCourses1, prerequisites1));
        
        // Test case 2: Cannot finish all courses (cycle detected)
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}}; // Cycle: 0->1->0
        System.out.println("Test case 2 - Can finish all courses: " + 
                          canFinish(numCourses2, prerequisites2));
        
        // Test case 3: More complex example
        int numCourses3 = 4;
        int[][] prerequisites3 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println("Test case 3 - Can finish all courses: " + 
                          canFinish(numCourses3, prerequisites3));
    }
}
import java.util.*;

public class CourseSchedule {
    
    // Problem: Determine if you can finish all courses
     // Explanation: This problem uses BFS to detect cycles in a directed graph representing course prerequisites. By processing nodes with zero in-degree, we can determine if all courses can be completed.
     // Time Complexity: O(V + E), where V is the number of courses and E is the number of prerequisites.
    // Space Complexity: O(V), due to the space required for the queue and in-degree array.
      public static boolean canFinish(int numCourses, int[][] prerequisites) {
          // Create adjacency list representation of the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Create in-degree array for each vertex
        int[] inDegree = new int[numCourses];
        
        // Build the graph and calculate in-degrees
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prereq = prerequisite[1];
            graph.get(prereq).add(course); // prereq -> course
            inDegree[course]++;
        }
        
        // Queue for BFS (start with all vertices that have no prerequisites)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // Count of visited vertices
        int count = 0;
        
        // BFS traversal
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            
            // Reduce in-degree of all adjacent vertices
            for (int neighbor : graph.get(current)) {
                inDegree[neighbor]--;
                
                // If in-degree becomes 0, add to queue
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // If count equals numCourses, we can finish all courses
        return count == numCourses;
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
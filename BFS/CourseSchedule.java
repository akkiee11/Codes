import java.util.*;

public class CourseSchedule {
    
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
     * Constraints:
     * - 1 <= numCourses <= 2000
     * - 0 <= prerequisites.length <= 5000
     * - prerequisites[i].length == 2
     * - 0 <= ai, bi < numCourses
     * - All the pairs prerequisites[i] are unique
     * 
     * Intuition:
     * - The problem can be modeled as a directed graph where courses are vertices and prerequisites are edges
     * - A cycle in this graph means some courses cannot be completed (they depend on each other)
     * - BFS with in-degree tracking can detect if such cycles exist
     * - Courses with no prerequisites (in-degree = 0) can be taken first
     * 
     * Approach:
     * 1. Build adjacency list and calculate in-degree for each course
     * 2. Start BFS with courses having no prerequisites (in-degree = 0)
     * 3. For each course taken:
     *    - Reduce in-degree of dependent courses
     *    - Add courses with new in-degree of 0 to queue
     * 4. If all courses can be taken (count equals numCourses), return true
     * 
     * Time Complexity: O(V + E), where V is the number of courses and E is the number of prerequisites
     * Space Complexity: O(V), for the queue and in-degree array
     */
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
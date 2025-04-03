import java.util.*;

public class ShortestPathMatrix {
    /*
     * Problem Statement:
     * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix.
     * A clear path in a binary matrix is a path from the top-left cell (0, 0) to the bottom-right cell (n-1, n-1) such that:
     * - All the visited cells of the path are 0
     * - All the adjacent cells of the path are 8-directionally connected
     * The length of a clear path is the number of visited cells of this path.
     * Return -1 if there is no clear path.
     * 
     * Example 1:
     * Input: grid = [[0,1],[1,0]]
     * Output: 2
     * Explanation: The shortest clear path is (0,0) -> (1,1)
     * 
     * Example 2:
     * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
     * Output: 4
     * Explanation: The shortest clear path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2)
     * 
     * Example 3:
     * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
     * Output: -1
     * Explanation: No clear path exists as starting cell is blocked.
     * 
     * Constraints:
     * - n == grid.length == grid[i].length
     * - 1 <= n <= 100
     * - grid[i][j] is 0 or 1
     * 
     * Intuition:
     * - Use BFS to find shortest path as it explores all possible paths level by level
     * - Track visited cells to avoid cycles
     * - Consider all 8 directions for each move
     * - Distance increases by 1 for each step
     * 
     * Approach:
     * 1. Check if start and end cells are valid (0)
     * 2. Use BFS with queue storing [row, col, distance]
     * 3. For each cell:
     *    - Try all 8 directions
     *    - Add valid unvisited cells to queue
     *    - Mark visited cells to avoid revisiting
     * 4. Return distance when reaching end cell
     * 
     * Time Complexity: O(n²), where n is the side length of the grid
     * Space Complexity: O(n²) for the queue in worst case
     */
    // Possible directions: right, right-down, down, left-down, left, left-up, up, right-up
    private static final int[][] DIRECTIONS = {
        {0, 1}, {1, 1}, {1, 0}, {1, -1},
        {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}
    };
    
    public static int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) {
            return -1;  // Start or end cell is blocked
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});  // {row, col, distance}
        grid[0][0] = 1;  // Mark as visited
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int distance = current[2];
            
            if (row == n-1 && col == n-1) {
                return distance;  // Reached destination
            }
            
            // Try all 8 directions
            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                // Check if new position is valid and unvisited
                if (isValid(newRow, newCol, n) && grid[newRow][newCol] == 0) {
                    queue.offer(new int[]{newRow, newCol, distance + 1});
                    grid[newRow][newCol] = 1;  // Mark as visited
                }
            }
        }
        
        return -1;  // No path found
    }
    
    private static boolean isValid(int row, int col, int n) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] grid1 = {
            {0, 1, 1, 0},
            {0, 0, 0, 1},
            {1, 1, 0, 0},
            {1, 1, 1, 0}
        };
        
        int[][] grid2 = {
            {0, 0, 0},
            {1, 1, 0},
            {1, 1, 0}
        };
        
        System.out.println("Test Case 1:");
        printGrid(grid1);
        System.out.println("Shortest Path Length: " + shortestPathBinaryMatrix(grid1));
        
        System.out.println("\nTest Case 2:");
        printGrid(grid2);
        System.out.println("Shortest Path Length: " + shortestPathBinaryMatrix(grid2));
    }
    
    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }
}
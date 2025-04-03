import java.util.*;

public class NumberOfIslands {
    
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        
        return count;
    }
    
    /*
     * Problem Statement:
     * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water),
     * return the number of islands. An island is surrounded by water and is formed by connecting
     * adjacent lands horizontally or vertically. You may assume all four edges of the grid are
     * surrounded by water.
     * 
     * Example 1:
     * Input: grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * Output: 1
     * 
     * Example 2:
     * Input: grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * Output: 3
     * 
     * Constraints:
     * - m == grid.length
     * - n == grid[i].length
     * - 1 <= m, n <= 300
     * - grid[i][j] is '0' or '1'
     * 
     * Intuition:
     * - Use BFS to explore connected land cells (1's)
     * - Each unvisited land cell could be part of a new island
     * - Mark visited cells to avoid counting them again
     * - BFS ensures we find all connected land cells for each island
     * 
     * Approach:
     * 1. Iterate through each cell in the grid
     * 2. When we find an unvisited land cell (1):
     *    - Increment island count
     *    - Use BFS to explore all connected land cells
     *    - Mark visited cells by changing them to 0
     * 3. BFS explores in four directions (up, right, down, left)
     * 4. Return the total count of islands found
     * 
     * Time Complexity: O(m * n), where m is the number of rows and n is the number of columns
     * Space Complexity: O(min(m, n)), for the queue in worst case
     */
    private static void bfs(char[][] grid, int row, int col) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Define the four directions: up, right, down, left
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        // Mark the current cell as visited by changing '1' to '0'
        grid[row][col] = '0';
        
        // Queue for BFS traversal
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currRow = current[0];
            int currCol = current[1];
            
            // Explore all four directions
            for (int[] dir : directions) {
                int newRow = currRow + dir[0];
                int newCol = currCol + dir[1];
                
                // Check if the new position is valid and is land ('1')
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols 
                    && grid[newRow][newCol] == '1') {
                    // Mark as visited and add to queue
                    grid[newRow][newCol] = '0';
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // Test case 1
        char[][] grid1 = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        System.out.println("Test case 1 - Number of islands: " + numIslands(grid1)); // Expected: 1
        
        // Test case 2
        char[][] grid2 = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println("Test case 2 - Number of islands: " + numIslands(grid2)); // Expected: 3
    }
}
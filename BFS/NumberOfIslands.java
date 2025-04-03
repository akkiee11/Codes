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
    
    // Problem: Count number of islands in a grid
    // Explanation: This problem uses BFS to count the number of connected components (islands) in a grid. By exploring each land cell and marking visited cells, we can efficiently count islands.
    // Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the grid.
    // Space Complexity: O(min(m, n)), due to the space required for the queue.
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
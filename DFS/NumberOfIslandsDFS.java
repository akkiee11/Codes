import java.util.*;

public class NumberOfIslandsDFS {
    
    /*
     * Problem Statement:
     * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water),
     * return the number of islands. An island is surrounded by water and is formed by connecting
     * adjacent lands horizontally or vertically.
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
     * Intuition:
     * - Each cell with value '1' can be part of an island
     * - When we find a '1', we use DFS to mark all connected land cells as visited
     * - Each time we start a new DFS, we've found a new island
     * 
     * Approach using DFS:
     * 1. Iterate through each cell in the grid
     * 2. When we find an unvisited land cell ('1'):
     *    - Increment island count
     *    - Use DFS to mark all connected land cells as visited
     * 3. Return total number of islands found
     * 
     * Time Complexity: O(M × N) where M is number of rows and N is number of columns
     * Space Complexity: O(M × N) in worst case for recursion stack
     * 
     * Key DFS Concepts Demonstrated:
     * - Grid traversal using DFS
     * - Connected components counting
     * - In-place marking of visited cells
     * - Handling multiple directions in recursion
     */
    
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int numRows = grid.length;
        int numCols = grid[0].length;
        int islands = 0;
        
        // Iterate through each cell in the grid
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (grid[row][col] == '1') {
                    islands++; // Found a new island
                    dfs(grid, row, col); // Mark all connected land cells
                }
            }
        }
        
        return islands;
    }
    
    private static void dfs(char[][] grid, int row, int col) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        
        // Check boundary conditions and if current cell is land
        if (row < 0 || col < 0 || row >= numRows || col >= numCols || grid[row][col] != '1') {
            return;
        }
        
        // Mark current cell as visited by changing it to '0'
        grid[row][col] = '0';
        
        // Recursively visit all adjacent cells (up, right, down, left)
        dfs(grid, row - 1, col); // Up
        dfs(grid, row + 1, col); // Down
        dfs(grid, row, col - 1); // Left
        dfs(grid, row, col + 1); // Right
    }
    
    public static void main(String[] args) {
        // Test case 1: Single island
        char[][] grid1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println("Test case 1 - Number of islands: " + numIslands(grid1));
        
        // Test case 2: Multiple islands
        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println("Test case 2 - Number of islands: " + numIslands(grid2));
        
        // Test case 3: No islands
        char[][] grid3 = {
            {'0','0','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println("Test case 3 - Number of islands: " + numIslands(grid3));
        
        // Test case 4: All lands
        char[][] grid4 = {
            {'1','1','1'},
            {'1','1','1'},
            {'1','1','1'}
        };
        System.out.println("Test case 4 - Number of islands: " + numIslands(grid4));
    }
}
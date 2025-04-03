/**
 * Longest Increasing Path in a Matrix
 *
 * Problem: Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 * From each cell, you can either move to four directions: left, right, up, or down.
 * You may NOT move diagonally or move outside the boundary.
 *
 * Example 1:
 * Input: matrix = [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1,2,6,9].
 *
 * Example 2:
 * Input: matrix = [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3,4,5,6].
 *
 * Approach:
 * 1. Use DFS with memoization
 * 2. For each cell, explore all four directions
 * 3. Only move to adjacent cells with larger values
 * 4. Cache results to avoid redundant calculations
 * 5. Track the maximum path length
 */

public class LongestIncreasingPath {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
    private int rows, cols;
    private int[][] memo;
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        rows = matrix.length;
        cols = matrix[0].length;
        memo = new int[rows][cols];
        
        int maxLength = 1;
        
        // Try starting from each cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maxLength = Math.max(maxLength, dfs(matrix, i, j));
            }
        }
        
        return maxLength;
    }
    
    private int dfs(int[][] matrix, int row, int col) {
        // If we've already computed this cell, return cached result
        if (memo[row][col] != 0) return memo[row][col];
        
        int maxPath = 1; // Minimum path length is 1 (current cell)
        
        // Explore all four directions
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            // Check if new position is valid and has a larger value
            if (isValid(newRow, newCol) && matrix[newRow][newCol] > matrix[row][col]) {
                maxPath = Math.max(maxPath, 1 + dfs(matrix, newRow, newCol));
            }
        }
        
        // Cache the result
        memo[row][col] = maxPath;
        return maxPath;
    }
    
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
    
    public static void main(String[] args) {
        LongestIncreasingPath solution = new LongestIncreasingPath();
        
        // Test Case 1
        int[][] matrix1 = {
            {9,9,4},
            {6,6,8},
            {2,1,1}
        };
        System.out.println("Test Case 1 Result: " + 
                          solution.longestIncreasingPath(matrix1)); // Expected: 4
        
        // Test Case 2
        int[][] matrix2 = {
            {3,4,5},
            {3,2,6},
            {2,2,1}
        };
        System.out.println("Test Case 2 Result: " + 
                          solution.longestIncreasingPath(matrix2)); // Expected: 4
    }
}
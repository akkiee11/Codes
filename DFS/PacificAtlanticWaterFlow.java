/**
 * Pacific Atlantic Water Flow
 *
 * Problem: Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 *
 * Example:
 * Input: heights = [
 *   [1,2,2,3,5],
 *   [3,2,3,4,4],
 *   [2,4,5,3,1],
 *   [6,7,1,4,5],
 *   [5,1,1,2,4]
 * ]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 *
 * Approach:
 * 1. Use two visited sets to track cells reachable from Pacific and Atlantic
 * 2. Start DFS from the borders of both oceans
 * 3. For Pacific, start from top and left edges
 * 4. For Atlantic, start from bottom and right edges
 * 5. Find cells that are in both visited sets
 *
 * Time Complexity: O(M * N) where M and N are the dimensions of the matrix
 * Space Complexity: O(M * N) for the visited sets
 */

import java.util.*;

public class PacificAtlanticWaterFlow {
    private int m, n;
    private int[][] heights;
    private boolean[][] pacificReachable;
    private boolean[][] atlanticReachable;
    private int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0) return new ArrayList<>();
        
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        this.pacificReachable = new boolean[m][n];
        this.atlanticReachable = new boolean[m][n];
        
        // DFS from Pacific borders (top and left)
        for (int i = 0; i < m; i++) {
            dfs(i, 0, pacificReachable);
        }
        for (int j = 0; j < n; j++) {
            dfs(0, j, pacificReachable);
        }
        
        // DFS from Atlantic borders (bottom and right)
        for (int i = 0; i < m; i++) {
            dfs(i, n-1, atlanticReachable);
        }
        for (int j = 0; j < n; j++) {
            dfs(m-1, j, atlanticReachable);
        }
        
        // Find cells reachable from both oceans
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        
        return result;
    }
    
    private void dfs(int i, int j, boolean[][] reachable) {
        // Mark current cell as reachable
        reachable[i][j] = true;
        
        // Check all 4 directions
        for (int[] dir : directions) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            
            // Check bounds, if already visited, and if water can flow (height should be >= current)
            if (newI < 0 || newI >= m || newJ < 0 || newJ >= n || 
                reachable[newI][newJ] || 
                heights[newI][newJ] < heights[i][j]) {
                continue;
            }
            
            dfs(newI, newJ, reachable);
        }
    }
    
    public static void main(String[] args) {
        PacificAtlanticWaterFlow solution = new PacificAtlanticWaterFlow();
        
        // Test Case
        int[][] heights = {
            {1,2,2,3,5},
            {3,2,3,4,4},
            {2,4,5,3,1},
            {6,7,1,4,5},
            {5,1,1,2,4}
        };
        
        List<List<Integer>> result = solution.pacificAtlantic(heights);
        
        System.out.println("Cells where water can flow to both oceans:");
        for (List<Integer> cell : result) {
            System.out.println(cell);
        }
    }
}
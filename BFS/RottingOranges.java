import java.util.*;

public class RottingOranges {
      
      /*
       * Problem Statement:
       * You are given an m x n grid where each cell can have one of three values:
       * - 0: representing an empty cell
       * - 1: representing a fresh orange
       * - 2: representing a rotten orange
       * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
       * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
       * Return -1 if this is impossible.
       * 
       * Example 1:
       * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
       * Output: 4
       * 
       * Example 2:
       * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
       * Output: -1
       * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten.
       * 
       * Example 3:
       * Input: grid = [[0,2]]
       * Output: 0
       * 
       * Constraints:
       * - m == grid.length
       * - n == grid[i].length
       * - 1 <= m, n <= 10
       * - grid[i][j] is 0, 1, or 2
       * 
       * Intuition:
       * - Use multi-source BFS starting from all rotten oranges
       * - Track time taken for each fresh orange to become rotten
       * - Count fresh oranges to ensure all can be rotten
       * 
       * Approach:
       * 1. Find all rotten oranges and count fresh ones
       * 2. Add all rotten oranges to queue as starting points
       * 3. Process level by level (each level = 1 minute):
       *    - For each rotten orange, check 4 adjacent cells
       *    - If adjacent orange is fresh, make it rotten
       *    - Track remaining fresh oranges
       * 4. Return minutes taken or -1 if impossible
       * 
       * Time Complexity: O(m * n), where m and n are grid dimensions
       * Space Complexity: O(m * n) for the queue
       */
      public static int orangesRotting(int[][] grid) {
          if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;
        
        // Find all rotten oranges and count fresh ones
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        
        // If no fresh oranges, return 0
        if (freshOranges == 0) return 0;
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int minutes = 0;
        
        // Process rotten oranges level by level
        while (!queue.isEmpty() && freshOranges > 0) {
            minutes++;
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                
                // Try all 4 directions
                for (int[] dir : directions) {
                    int newRow = current[0] + dir[0];
                    int newCol = current[1] + dir[1];
                    
                    if (newRow >= 0 && newRow < rows && 
                        newCol >= 0 && newCol < cols && 
                        grid[newRow][newCol] == 1) {
                        
                        grid[newRow][newCol] = 2;
                        queue.offer(new int[]{newRow, newCol});
                        freshOranges--;
                    }
                }
            }
        }
        
        return freshOranges == 0 ? minutes : -1;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][][] testCases = {
            // Test Case 1: All oranges will rot
            {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
            },
            // Test Case 2: Can't rot all oranges
            {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
            },
            // Test Case 3: No fresh oranges
            {
                {0, 2, 2},
                {0, 2, 0},
                {0, 2, 2}
            }
        };
        
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            printGrid(testCases[i]);
            int result = orangesRotting(testCases[i]);
            System.out.println("Minutes to rot all oranges: " + result);
            System.out.println();
        }
    }
    
    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }
}
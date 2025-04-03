import java.util.*;

public class RottingOranges {
      
      // Problem: Minimum time to rot all oranges
      // Explanation: This problem uses BFS to calculate the minimum time required for all oranges to rot in a grid. By processing rotten oranges level by level, we can track the time taken for each fresh orange to rot.
      // Time Complexity: O(m * n), where m is the number of rows and n is the number of columns in the grid.
      // Space Complexity: O(m * n), due to the space required for the queue.
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
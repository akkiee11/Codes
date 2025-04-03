import java.util.*;

public class ShortestPathMatrix {
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
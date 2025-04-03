import java.util.*;

public class WallsAndGates {
    // Constants for cell types
    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private static final int WALL = -1;
    
    // Four directions: up, right, down, left
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    public static void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        
        int rows = rooms.length;
        int cols = rooms[0].length;
        
        // Queue for BFS traversal
        Queue<int[]> queue = new LinkedList<>();
        
        // Add all gates to the queue as starting points
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rooms[i][j] == GATE) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        
        // BFS traversal from all gates simultaneously
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            
            // Explore all four directions
            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                
                // Check if the new position is valid and is an empty room
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols 
                    && rooms[newRow][newCol] == EMPTY) {
                    // Update distance to this empty room
                    rooms[newRow][newCol] = rooms[row][col] + 1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
    }
    
    // Helper method to print the rooms grid
    private static void printRooms(int[][] rooms) {
        for (int[] row : rooms) {
            for (int cell : row) {
                if (cell == WALL) {
                    System.out.print("W\t");
                } else if (cell == GATE) {
                    System.out.print("G\t");
                } else if (cell == EMPTY) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(cell + "\t");
                }
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // Test case
        // INF = Empty room (Integer.MAX_VALUE)
        // 0 = Gate
        // -1 = Wall
        int[][] rooms = {
            {EMPTY, WALL, GATE, EMPTY},
            {EMPTY, EMPTY, EMPTY, WALL},
            {EMPTY, WALL, EMPTY, WALL},
            {GATE, WALL, EMPTY, EMPTY}
        };
        
        System.out.println("Original rooms:");
        printRooms(rooms);
        
        wallsAndGates(rooms);
        
        System.out.println("\nRooms after filling distances:");
        printRooms(rooms);
    }
}
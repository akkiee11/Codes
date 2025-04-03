import java.util.*;

public class WallsAndGates {
    /*
     * Problem Statement:
     * You are given an m x n grid rooms initialized with these three possible values:
     * - -1: A wall or an obstacle
     * - 0: A gate
     * - INF: An empty room (represented by Integer.MAX_VALUE)
     * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate,
     * it should remain INF.
     * 
     * Example 1:
     * Input: rooms = [
     *   [INF, -1,   0, INF],
     *   [INF, INF, INF, -1],
     *   [INF, -1, INF, -1],
     *   [0,   -1, INF, INF]
     * ]
     * Output: [
     *   [3, -1,  0,  1],
     *   [2,  2,  1, -1],
     *   [1, -1,  2, -1],
     *   [0, -1,  3,  4]
     * ]
     * Explanation: The 2D grid is:
     * - the gates are at positions (0,2) and (3,0)
     * - the walls are at positions (0,1), (1,3), (2,1), (2,3), (3,1)
     * - empty rooms are filled with their distance to nearest gate
     * 
     * Constraints:
     * - m == rooms.length
     * - n == rooms[i].length
     * - 1 <= m, n <= 250
     * - rooms[i][j] is -1, 0, or 2^31 - 1
     * 
     * Intuition:
     * - Use multi-source BFS starting from all gates simultaneously
     * - Each step represents distance from a gate
     * - Process cells level by level to ensure minimum distance
     * - Walls block paths between gates and rooms
     * 
     * Approach:
     * 1. Find all gates in the grid and add to queue
     * 2. Start BFS from all gates simultaneously:
     *    - For each gate, explore four directions
     *    - Update distance for each empty room found
     *    - Add newly discovered rooms to queue
     * 3. Process until no more rooms can be reached
     * 
     * Time Complexity: O(m * n), where m and n are the dimensions of the grid
     * Space Complexity: O(m * n) for the queue in worst case
     */
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
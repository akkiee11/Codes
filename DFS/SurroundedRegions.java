/**
 * Surrounded Regions
 *
 * Problem: Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally
 * surrounded by 'X'. A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example 1:
 * Input: board = [
 *   ["X","X","X","X"],
 *   ["X","O","O","X"],
 *   ["X","X","O","X"],
 *   ["X","O","X","X"]
 * ]
 * Output: [
 *   ["X","X","X","X"],
 *   ["X","X","X","X"],
 *   ["X","X","X","X"],
 *   ["X","O","X","X"]
 * ]
 *
 * Approach:
 * 1. First, mark all 'O' cells that are connected to the border with a temporary mark '#'
 * 2. These cells cannot be captured as they have a path to the border
 * 3. Then, convert all remaining 'O' cells to 'X' (these are surrounded)
 * 4. Finally, convert all '#' cells back to 'O'
 *
 * Time Complexity: O(M * N) where M and N are the dimensions of the board
 * Space Complexity: O(M * N) in worst case for recursion stack
 */

public class SurroundedRegions {
    private int m, n;
    private char[][] board;
    private int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        
        this.board = board;
        this.m = board.length;
        this.n = board[0].length;
        
        // Mark 'O' cells connected to border
        // Check first and last row
        for (int j = 0; j < n; j++) {
            dfs(0, j);
            dfs(m-1, j);
        }
        
        // Check first and last column
        for (int i = 0; i < m; i++) {
            dfs(i, 0);
            dfs(i, n-1);
        }
        
        // Convert remaining 'O' to 'X' and '#' back to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void dfs(int i, int j) {
        // Check bounds and if cell is 'O'
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
            return;
        }
        
        // Mark this cell
        board[i][j] = '#';
        
        // Check all 4 directions
        for (int[] dir : directions) {
            dfs(i + dir[0], j + dir[1]);
        }
    }
    
    public static void main(String[] args) {
        SurroundedRegions solution = new SurroundedRegions();
        
        // Test Case 1
        char[][] board1 = {
            {'X','X','X','X'},
            {'X','O','O','X'},
            {'X','X','O','X'},
            {'X','O','X','X'}
        };
        
        System.out.println("Original Board:");
        printBoard(board1);
        
        solution.solve(board1);
        
        System.out.println("\nAfter Capturing:");
        printBoard(board1);
    }
    
    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
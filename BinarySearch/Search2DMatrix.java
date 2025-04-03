/**
 * Search in 2D Matrix
 * Problem: Write an efficient algorithm that searches for a value target in an m x n matrix.
 * The matrix has the following properties:
 * 1. Integers in each row are sorted from left to right.
 * 2. The first integer of each row is greater than the last integer of the previous row.
 * 
 * Example:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 */
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        
        // Treat the 2D matrix as a sorted 1D array
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / n;
            int col = mid % n;
            int value = matrix[row][col];
            
            if (value == target) {
                return true;
            } else if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    // Test cases
    public static void main(String[] args) {
        Search2DMatrix solution = new Search2DMatrix();
        
        // Test case 1: Regular case
        int[][] matrix1 = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        System.out.println(solution.searchMatrix(matrix1, 3));  // Expected: true
        System.out.println(solution.searchMatrix(matrix1, 13)); // Expected: false
        
        // Test case 2: Single row matrix
        int[][] matrix2 = {{1, 3, 5}};
        System.out.println(solution.searchMatrix(matrix2, 3));  // Expected: true
        
        // Test case 3: Single column matrix
        int[][] matrix3 = {{1}, {3}, {5}};
        System.out.println(solution.searchMatrix(matrix3, 5));  // Expected: true
        
        // Test case 4: Empty matrix
        int[][] matrix4 = {};
        System.out.println(solution.searchMatrix(matrix4, 1));  // Expected: false
    }
}
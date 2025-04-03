import java.util.PriorityQueue;

/**
 * Kth Smallest Element in a Sorted Matrix
 *
 * Problem Statement:
 * Given an n x n matrix where each row and column is sorted in ascending order,
 * find the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * Constraints:
 * - n == matrix.length == matrix[i].length
 * - 1 <= n <= 300
 * - -10^9 <= matrix[i][j] <= 10^9
 * - All the rows and columns of matrix are guaranteed to be sorted in ascending order
 * - 1 <= k <= n^2
 *
 * Approaches:
 * 1. Min Heap (Current Implementation)
 *    - Time Complexity: O(k * log(n)) where n is the matrix dimension
 *    - Space Complexity: O(n) for storing the heap
 *    - Intuition: Use min heap to track smallest elements from each row
 *
 * 2. Binary Search (Alternative)
 *    - Time Complexity: O(n * log(max-min))
 *    - Space Complexity: O(1)
 *    - Intuition: Binary search on value range and count elements <= mid
 *
 * 3. Merge Sort (Alternative)
 *    - Time Complexity: O(n^2 * log(n))
 *    - Space Complexity: O(n^2)
 *    - Intuition: Merge all rows into sorted array and return kth element
 */
public class KthSmallestMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        
        // Create a min heap to store elements with their positions
        PriorityQueue<Cell> minHeap = new PriorityQueue<>((a, b) -> a.value - b.value);
        
        // Add first elements of each row
        for (int i = 0; i < n; i++) {
            minHeap.offer(new Cell(i, 0, matrix[i][0]));
        }
        
        // Process k-1 elements
        for (int i = 0; i < k - 1; i++) {
            Cell current = minHeap.poll();
            
            // If there are more elements in the current row, add the next element
            if (current.col + 1 < n) {
                minHeap.offer(new Cell(current.row, current.col + 1, 
                    matrix[current.row][current.col + 1]));
            }
        }
        
        // The kth element will be at the top of the heap
        return minHeap.poll().value;
    }
    
    // Helper class to store matrix cell information
    private static class Cell {
        int row;
        int col;
        int value;
        
        Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
    
    // Test the implementation
    public static void main(String[] args) {
        KthSmallestMatrix solution = new KthSmallestMatrix();
        
        // Test case 1: Regular case
        int[][] matrix1 = {
            {1,  5,  9},
            {10, 11, 13},
            {12, 13, 15}
        };
        int k1 = 8;
        System.out.println("Test case 1: " + solution.kthSmallest(matrix1, k1));
        
        // Test case 2: Single element matrix
        int[][] matrix2 = {{1}};
        int k2 = 1;
        System.out.println("Test case 2: " + solution.kthSmallest(matrix2, k2));
        
        // Test case 3: 2x2 matrix
        int[][] matrix3 = {
            {1, 2},
            {3, 4}
        };
        int k3 = 3;
        System.out.println("Test case 3: " + solution.kthSmallest(matrix3, k3));
    }
}
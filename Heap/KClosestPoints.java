import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Problem Statement:
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane
 * and an integer k, return the k closest points to the origin (0, 0).
 * 
 * The distance between two points is the Euclidean distance: sqrt((x1 - x2)² + (y1 - y2)²).
 * 
 * Constraints:
 * - 1 <= k <= points.length <= 10^4
 * - -10^4 <= xi, yi <= 10^4
 * 
 * Approaches:
 * 1. Min Heap (Current Implementation)
 *    - Time Complexity: O(n log k) where n is the number of points
 *    - Space Complexity: O(k) for storing the heap
 *    - Intuition: Use a min heap to maintain points sorted by distance from origin
 * 
 * 2. Quick Select (Alternative Approach)
 *    - Time Complexity: O(n) average case, O(n²) worst case
 *    - Space Complexity: O(1)
 *    - Intuition: Similar to QuickSort but only process one side of pivot
 * 
 * 3. Sorting (Simple Approach)
 *    - Time Complexity: O(n log n)
 *    - Space Complexity: O(1)
 *    - Intuition: Sort all points by distance and take first k points
 */
public class KClosestPoints {
    public int[][] kClosest(int[][] points, int k) {
        // Create a min heap based on distance from origin
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1])
        );
        
        // Add all points to the min heap
        for (int[] point : points) {
            minHeap.offer(point);
        }
        
        // Extract k closest points
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        
        return result;
    }
    
    // Test the implementation
    public static void main(String[] args) {
        KClosestPoints solution = new KClosestPoints();
        
        // Test case 1: Basic case
        // Expected output: [-2,2], [1,3] (or [1,3], [-2,2])
        // Explanation: The distances are: (1,3)->10, (-2,2)->8, (5,-1)->26
        int[][] points1 = {{1,3}, {-2,2}, {5,-1}};
        int k1 = 2;
        int[][] result1 = solution.kClosest(points1, k1);
        System.out.println("Test case 1: k=2 closest points from [(1,3), (-2,2), (5,-1)]:");
        printPoints(result1);
        
        // Test case 2: All points same distance
        // Expected output: Any two points (all have distance = 2)
        // Explanation: All points are at distance sqrt(2) from origin
        int[][] points2 = {{1,1}, {-1,-1}, {1,-1}, {-1,1}};
        int k2 = 2;
        int[][] result2 = solution.kClosest(points2, k2);
        System.out.println("\nTest case 2: k=2 closest points from [(1,1), (-1,-1), (1,-1), (-1,1)]:");
        printPoints(result2);
        
        // Test case 3: Edge case - k equals array length
        // Expected output: All points in any order
        int[][] points3 = {{1,0}, {0,1}};
        int k3 = 2;
        int[][] result3 = solution.kClosest(points3, k3);
        System.out.println("\nTest case 3: k=2 (equals array length) points from [(1,0), (0,1)]:");
        printPoints(result3);
        
        // Test case 4: Points with negative coordinates
        // Expected output: [0,0], [-1,0] (or [-1,0], [0,0])
        int[][] points4 = {{1,1}, {-1,0}, {0,0}, {2,2}};
        int k4 = 2;
        int[][] result4 = solution.kClosest(points4, k4);
        System.out.println("\nTest case 4: k=2 closest points from [(1,1), (-1,0), (0,0), (2,2)]:");
        printPoints(result4);
    }
    
    private static void printPoints(int[][] points) {
        for (int[] point : points) {
            System.out.println("[" + point[0] + "," + point[1] + "]");
        }
    }
}
/**
 * Squares of a Sorted Array
 * 
 * Problem Description:
 * Given an integer array nums sorted in non-decreasing order, return an array
 * of the squares of each number sorted in non-decreasing order.
 * 
 * Example 1:
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * 
 * Example 2:
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 * 
 * Approach:
 * Two Pointer Technique O(n):
 * - Use two pointers: left at start and right at end
 * - Compare squares of numbers at both ends
 * - Place larger square at the end of result array
 * - Move pointers accordingly
 * - Time Complexity: O(n), Space Complexity: O(n)
 */

public class SquaresSortedArray {
    
    public static int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n];
        
        // Two pointers: left at start, right at end
        int left = 0;
        int right = n - 1;
        int writeIndex = n - 1; // Write from end of result array
        
        // Compare squares from both ends and place larger one at the end
        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            
            if (leftSquare > rightSquare) {
                result[writeIndex] = leftSquare;
                left++;
            } else {
                result[writeIndex] = rightSquare;
                right--;
            }
            writeIndex--;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {-4, -1, 0, 3, 10},        // Mixed positive and negative
            {-7, -3, 2, 3, 11},         // Mixed positive and negative
            {-5, -3, -2, -1},           // All negative
            {1, 2, 3, 4, 5},            // All positive
            {0, 0, 0},                  // All zeros
            {-2, -1},                   // Two negative
            {1, 2},                     // Two positive
            {0},                        // Single zero
            {-1},                       // Single negative
            {1},                        // Single positive
            {}                          // Empty array
        };
        
        for (int[] nums : testCases) {
            System.out.println("\nOriginal array: " + arrayToString(nums));
            int[] result = sortedSquares(nums);
            System.out.println("Sorted squares: " + arrayToString(result));
        }
    }
    
    // Helper method to print array
    private static String arrayToString(int[] arr) {
        if (arr.length == 0) return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
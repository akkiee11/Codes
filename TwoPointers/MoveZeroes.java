/**
 * Move Zeroes
 * 
 * Problem Description:
 * Given an integer array nums, move all 0's to the end of it while maintaining
 * the relative order of the non-zero elements.
 * Note that you must do this in-place without making a copy of the array.
 * 
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * 
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 * 
 * Approach:
 * Two Pointer Technique O(n):
 * - Use two pointers: writePointer and readPointer
 * - writePointer keeps track of where to write the next non-zero element
 * - readPointer scans through the array
 * - When readPointer finds a non-zero element, write it to writePointer position
 * - Fill remaining positions with zeros
 * - Time Complexity: O(n), Space Complexity: O(1)
 */

public class MoveZeroes {
    
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int writePointer = 0; // Position to write next non-zero element
        
        // First pass: move all non-zero elements to the front
        for (int readPointer = 0; readPointer < nums.length; readPointer++) {
            if (nums[readPointer] != 0) {
                nums[writePointer] = nums[readPointer];
                writePointer++;
            }
        }
        
        // Second pass: fill remaining positions with zeros
        while (writePointer < nums.length) {
            nums[writePointer] = 0;
            writePointer++;
        }
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {0, 1, 0, 3, 12},           // Standard case
            {1, 3, 12, 0, 0},            // Zeros at end
            {0, 0, 0, 1, 2},             // Zeros at start
            {1, 2, 3, 4, 5},             // No zeros
            {0},                         // Single zero
            {1},                         // Single non-zero
            {},                          // Empty array
            {0, 0, 0, 0, 0},             // All zeros
            {1, 0, 1, 0, 1, 0}           // Alternating
        };
        
        for (int[] nums : testCases) {
            System.out.println("\nOriginal array: " + arrayToString(nums));
            moveZeroes(nums);
            System.out.println("After moving zeroes: " + arrayToString(nums));
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
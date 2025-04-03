/**
 * Remove Duplicates from Sorted Array
 * 
 * Problem Description:
 * Given a sorted array of integers, remove the duplicates in-place such that each element
 * appears only once and return the number of unique elements. The relative order of the
 * elements should be kept the same.
 * 
 * Example:
 * Input: nums = [1, 1, 2]
 * Output: 2, nums = [1, 2, _]
 * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2.
 * 
 * Approach:
 * Two Pointer Technique (O(n)):
 * - Use two pointers: slow (keeps track of position for next unique element)
 *   and fast (scans through the array)
 * - When finding a new unique element, place it at the slow pointer position
 * - Time Complexity: O(n), Space Complexity: O(1)
 */

public class RemoveDuplicatesSorted {
    
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Initialize the slow pointer
        int slow = 0;
        
        // Fast pointer starts from index 1
        for (int fast = 1; fast < nums.length; fast++) {
            // If we find a new unique element
            if (nums[fast] != nums[slow]) {
                slow++; // Move slow pointer
                nums[slow] = nums[fast]; // Place the new unique element
            }
        }
        
        // Return the number of unique elements (slow + 1 because slow is 0-based)
        return slow + 1;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {1, 1, 2},           // Basic case with one duplicate
            {0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, // Multiple duplicates
            {1, 2, 3},           // No duplicates
            {1},                 // Single element
            {}                   // Empty array
        };
        
        for (int[] nums : testCases) {
            System.out.println("\nOriginal array: " + arrayToString(nums));
            int k = removeDuplicates(nums);
            System.out.println("Number of unique elements: " + k);
            System.out.println("Array after removing duplicates: " + arrayToString(nums));
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
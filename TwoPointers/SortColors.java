/**
 * Sort Colors (Dutch National Flag Problem)
 * 
 * Problem Description:
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that
 * objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * 
 * Example 1:
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * 
 * Example 2:
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * 
 * Approach:
 * Dutch National Flag Algorithm (Three Pointers) O(n):
 * - Use three pointers: low (for 0s), mid (for 1s), and high (for 2s)
 * - low tracks the rightmost boundary of 0s
 * - high tracks the leftmost boundary of 2s
 * - mid scans through the array
 * - Time Complexity: O(n), Space Complexity: O(1)
 */

public class SortColors {
    
    public static void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int low = 0;        // pointer for 0s (red)
        int mid = 0;        // pointer for 1s (white)
        int high = nums.length - 1;  // pointer for 2s (blue)
        
        while (mid <= high) {
            if (nums[mid] == 0) {
                // Swap with low pointer and move both low and mid forward
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // Current element is 1, just move mid forward
                mid++;
            } else {
                // Current element is 2, swap with high pointer and move high backward
                swap(nums, mid, high);
                high--;
            }
        }
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {2, 0, 2, 1, 1, 0},  // Standard case with multiple occurrences
            {2, 0, 1},           // Minimal case with all colors
            {0},                 // Single element
            {1, 1, 1, 1},        // All same color
            {2, 2, 1, 1, 0, 0},  // Already sorted in reverse
            {0, 1, 2, 0, 1, 2}   // Repeated pattern
        };
        
        for (int[] test : testCases) {
            System.out.println("\nOriginal array: " + arrayToString(test));
            sortColors(test);
            System.out.println("Sorted array:   " + arrayToString(test));
        }
    }
    
    private static String arrayToString(int[] arr) {
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
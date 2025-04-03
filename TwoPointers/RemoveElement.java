/**
 * Remove Element
 * 
 * Problem Description:
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
 * The order of the elements may be changed. Then return the number of elements in nums which
 * are not equal to val.
 * 
 * Example 1:
 * Input: nums = [3,2,2,3], val = 3
 * Output: 2, nums = [2,2,_,_]
 * 
 * Example 2:
 * Input: nums = [0,1,2,2,3,0,4,2], val = 2
 * Output: 5, nums = [0,1,3,0,4,_,_,_]
 * 
 * Approach:
 * Two Pointer Technique O(n):
 * - Use two pointers: left (keeps track of position for next non-target element)
 *   and right (scans through the array)
 * - When finding a non-target element, place it at the left pointer position
 * - Time Complexity: O(n), Space Complexity: O(1)
 */

public class RemoveElement {
    
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0; // pointer for next position to place non-target element
        
        // Iterate through the array
        for (int right = 0; right < nums.length; right++) {
            // If current element is not the target value
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        
        return left; // left represents the number of elements not equal to val
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testArrays = {
            {3, 2, 2, 3},                  // Basic case with elements at start and end
            {0, 1, 2, 2, 3, 0, 4, 2},      // Multiple occurrences throughout array
            {2, 2, 2},                      // All elements are target value
            {1, 1, 1},                      // No elements are target value
            {1},                            // Single element equals target
            {4},                            // Single element not equals target
            {}                              // Empty array
        };
        
        int[] testValues = {3, 2, 2, 2, 1, 1, 0};
        
        for (int i = 0; i < testArrays.length; i++) {
            int[] nums = testArrays[i].clone(); // Create a copy of the test array
            int val = testValues[i];
            
            System.out.println("\nOriginal array: " + arrayToString(nums));
            System.out.println("Value to remove: " + val);
            
            int k = removeElement(nums, val);
            
            System.out.println("Number of elements after removal: " + k);
            System.out.println("Array after removal: " + arrayToString(nums));
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
/**
 * Three Sum
 * 
 * Problem Description:
 * Given an integer array nums, find all unique triplets in the array which gives the sum of zero.
 * The solution set must not contain duplicate triplets.
 * 
 * Example:
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * 
 * Approach:
 * Two Pointer Technique with Sorting (O(n²)):
 * - Sort the array first to handle duplicates easily
 * - Fix one element and use two pointers for the remaining sum
 * - Skip duplicates to ensure unique triplets
 * - For each element i:
 *   - Use left pointer at i+1 and right pointer at end
 *   - Move pointers based on sum comparison
 * - Time Complexity: O(n²), Space Complexity: O(1) excluding output space
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        
        // Sort array to handle duplicates and use two pointers
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    // Found a triplet
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates for left and right
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {-1, 0, 1, 2, -1, -4},       // Standard case
            {0, 0, 0, 0},                 // All zeros
            {1, -1, -1, 0},               // Single zero
            {-2, 0, 1, 1, 2},             // Multiple solutions
            {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}  // Large case with duplicates
        };
        
        for (int[] nums : testCases) {
            System.out.println("\nInput array: " + Arrays.toString(nums));
            List<List<Integer>> result = threeSum(nums);
            System.out.println("Triplets with sum zero: " + result);
        }
    }
}
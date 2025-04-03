/**
 * Four Sum
 * 
 * Problem Description:
 * Given an array nums of n integers and a target value, find all unique quadruplets
 * in the array which gives the sum of target.
 * The solution set must not contain duplicate quadruplets.
 * 
 * Example 1:
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 
 * Example 2:
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 * 
 * Approach:
 * Two Pointer Technique with Sorting (O(n³)):
 * - Sort the array first to handle duplicates easily
 * - Use two nested loops to fix first two numbers
 * - Use two pointers for the remaining two numbers
 * - Skip duplicates at all levels to ensure unique quadruplets
 * - Time Complexity: O(n³), Space Complexity: O(1) excluding output space
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }
        
        // Sort array to handle duplicates and use two pointers
        Arrays.sort(nums);
        int n = nums.length;
        
        // Fix first two numbers
        for (int i = 0; i < n - 3; i++) {
            // Skip duplicates for i
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicates for j
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                
                int left = j + 1;
                int right = n - 1;
                
                while (left < right) {
                    // Use long to handle potential integer overflow
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    
                    if (sum == target) {
                        // Found a quadruplet
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
                        // Skip duplicates for left and right
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testArrays = {
            {1, 0, -1, 0, -2, 2},           // Standard case
            {2, 2, 2, 2, 2},                 // All same numbers
            {-3, -2, -1, 0, 0, 1, 2, 3},     // Mixed positive and negative
            {-1, 0, 1, 2, -1, -4},           // Similar to ThreeSum test
            {0, 0, 0, 0},                    // All zeros
            {-2, -1, -1, 1, 1, 2, 2},        // Multiple duplicates
            {1, -2, -5, 0, 4}                // Small array
        };
        
        int[] targets = {0, 8, 0, -1, 0, 0, -2};
        
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("\nInput array: " + Arrays.toString(testArrays[i]));
            System.out.println("Target sum: " + targets[i]);
            List<List<Integer>> result = fourSum(testArrays[i], targets[i]);
            System.out.println("Quadruplets: " + result);
        }
    }
}
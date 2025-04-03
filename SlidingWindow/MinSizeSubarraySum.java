/**
 * Minimum Size Subarray Sum
 * Problem: Given an array of positive integers and a target sum, find the minimal length of a contiguous subarray
 * whose sum is greater than or equal to the target sum. Return 0 if no such subarray exists.
 *
 * Example:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Time Complexity: O(n) where n is the size of array
 * Space Complexity: O(1) as we only use variables
 */
public class MinSizeSubarraySum {
    public static int findMinSubarray(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int minLength = Integer.MAX_VALUE;
        int windowSum = 0;
        int windowStart = 0;

        // Use sliding window with variable size
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            windowSum += nums[windowEnd];

            // Shrink window as long as windowSum >= target
            while (windowSum >= target) {
                minLength = Math.min(minLength, windowEnd - windowStart + 1);
                windowSum -= nums[windowStart];
                windowStart++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        int target1 = 7;
        System.out.println("Test Case 1: " + findMinSubarray(nums1, target1)); // Expected output: 2

        // Test Case 2
        int[] nums2 = {1, 4, 4};
        int target2 = 4;
        System.out.println("Test Case 2: " + findMinSubarray(nums2, target2)); // Expected output: 1

        // Test Case 3
        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int target3 = 11;
        System.out.println("Test Case 3: " + findMinSubarray(nums3, target3)); // Expected output: 0

        // Test Case 4
        int[] nums4 = {1, 2, 3, 4, 5};
        int target4 = 15;
        System.out.println("Test Case 4: " + findMinSubarray(nums4, target4)); // Expected output: 5
    }
}
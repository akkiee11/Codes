/**
 * Binary Subarrays With Sum
 * Problem: Given a binary array nums and a target sum goal, return the number of non-empty subarrays with a sum equal to goal.
 *
 * Intuition:
 * - The key insight is that we can use the sliding window technique with a clever twist
 * - Instead of directly counting subarrays with sum = goal, we can use:
 *   count(sum = goal) = count(sum ≤ goal) - count(sum ≤ goal-1)
 * - This works because:
 *   1. First part counts all subarrays with sum ≤ goal
 *   2. Second part counts all subarrays with sum ≤ (goal-1)
 *   3. Their difference gives exactly the subarrays with sum = goal
 *
 * Approach:
 * 1. Use helper method atMostSum(nums, goal) to count subarrays with sum ≤ goal
 * 2. For each window:
 *    - Add numbers to window sum
 *    - If sum > goal, shrink window from start
 *    - Count all valid subarrays ending at current position
 * 3. Return difference of atMostSum(goal) and atMostSum(goal-1)
 *
 * Example:
 * Input: nums = [1,0,1,0,1], goal = 2
 * Output: 4
 * Explanation: 
 * - Subarrays with sum = 2: [1,0,1], [1,0,1,0], [0,1,0,1], [1,0,1]
 * - atMostSum(2) = 9 subarrays with sum ≤ 2
 * - atMostSum(1) = 5 subarrays with sum ≤ 1
 * - Therefore, exactly 4 subarrays with sum = 2
 *
 * Time Complexity: O(n) where n is the size of array
 * - We make two passes through the array using sliding window
 *
 * Space Complexity: O(1) as we only use variables
 * - Only need windowStart, windowSum, and count variables
 */
public class BinarySubarraySum {
    public static int numSubarraysWithSum(int[] nums, int goal) {
        if (nums == null || nums.length == 0 || goal < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        return atMostSum(nums, goal) - (goal > 0 ? atMostSum(nums, goal - 1) : 0);
    }

    // Helper method to count subarrays with sum at most goal
    private static int atMostSum(int[] nums, int goal) {
        if (goal < 0) return 0;

        int count = 0;
        int windowStart = 0;
        int windowSum = 0;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            windowSum += nums[windowEnd];

            // Shrink window while sum is greater than goal
            while (windowSum > goal) {
                windowSum -= nums[windowStart];
                windowStart++;
            }

            // Add count of all valid subarrays ending at windowEnd
            count += windowEnd - windowStart + 1;
        }

        return count;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {1, 0, 1, 0, 1};
        int goal1 = 2;
        System.out.println("Test Case 1: " + numSubarraysWithSum(nums1, goal1)); // Expected output: 4

        // Test Case 2
        int[] nums2 = {1, 0, 1, 0, 1};
        int goal2 = 3;
        System.out.println("Test Case 2: " + numSubarraysWithSum(nums2, goal2)); // Expected output: 1

        // Test Case 3
        int[] nums3 = {0, 0, 0, 0, 0};
        int goal3 = 0;
        System.out.println("Test Case 3: " + numSubarraysWithSum(nums3, goal3)); // Expected output: 15

        // Test Case 4
        int[] nums4 = {0, 1, 1, 1, 1};
        int goal4 = 3;
        System.out.println("Test Case 4: " + numSubarraysWithSum(nums4, goal4)); // Expected output: 3
    }
}
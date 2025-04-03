/**
 * Max Consecutive Ones III
 * Problem: Given a binary array nums and an integer k, return the maximum number of consecutive 1's
 * in the array if you can flip at most k 0's.
 *
 * Example:
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1] Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 * Time Complexity: O(n) where n is the size of array
 * Space Complexity: O(1) as we only use variables
 */
public class MaxConsecutiveOnes {
    public static int longestOnes(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int maxLength = 0;
        int windowStart = 0;
        int zeroCount = 0;

        // Use sliding window to track flippable zeros
        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            if (nums[windowEnd] == 0) {
                zeroCount++;
            }

            // Shrink window while we have more zeros than we can flip
            while (zeroCount > k) {
                if (nums[windowStart] == 0) {
                    zeroCount--;
                }
                windowStart++;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k1 = 2;
        System.out.println("Test Case 1: " + longestOnes(nums1, k1)); // Expected output: 6

        // Test Case 2
        int[] nums2 = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k2 = 3;
        System.out.println("Test Case 2: " + longestOnes(nums2, k2)); // Expected output: 10

        // Test Case 3
        int[] nums3 = {0, 0, 0, 0};
        int k3 = 0;
        System.out.println("Test Case 3: " + longestOnes(nums3, k3)); // Expected output: 0

        // Test Case 4
        int[] nums4 = {1, 1, 1, 1, 1};
        int k4 = 2;
        System.out.println("Test Case 4: " + longestOnes(nums4, k4)); // Expected output: 5
    }
}
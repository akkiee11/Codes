/**
 * Count Number of Nice Subarrays
 * Problem: Given an array of integers and an integer k, a continuous subarray is called nice if there are exactly k odd numbers in it.
 * Return the number of nice subarrays.
 *
 * Intuition:
 * - Similar to Binary Subarray Sum, we can transform this problem into a sliding window approach
 * - Instead of directly counting subarrays with exactly k odd numbers, we can use:
 *   count(odd = k) = count(odd ≤ k) - count(odd ≤ k-1)
 * - This works because:
 *   1. First part counts all subarrays with at most k odd numbers
 *   2. Second part counts all subarrays with at most (k-1) odd numbers
 *   3. Their difference gives exactly the subarrays with k odd numbers
 *
 * Approach:
 * 1. Use helper method atMostK(nums, k) to count subarrays with at most k odd numbers
 * 2. For each window:
 *    - Track odd numbers count in current window
 *    - If odd count > k, shrink window from start
 *    - Count all valid subarrays ending at current position
 * 3. Return difference of atMostK(k) and atMostK(k-1)
 *
 * Example:
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: 
 * - Nice subarrays with exactly 3 odd numbers: [1,1,2,1] and [1,2,1,1]
 * - atMostK(3) = 8 subarrays with ≤ 3 odd numbers
 * - atMostK(2) = 6 subarrays with ≤ 2 odd numbers
 * - Therefore, exactly 2 subarrays with 3 odd numbers
 *
 * Time Complexity: O(n) where n is the size of array
 * - We make two passes through the array using sliding window
 *
 * Space Complexity: O(1) as we only use variables
 * - Only need windowStart, oddCount, and count variables
 */
public class NiceSubarrays {
    public static int countNiceSubarrays(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Invalid input");
        }

        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    // Helper method to count subarrays with at most k odd numbers
    private static int atMostK(int[] nums, int k) {
        int count = 0;
        int windowStart = 0;
        int oddCount = 0;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            // If current number is odd, increment oddCount
            if (nums[windowEnd] % 2 == 1) {
                oddCount++;
            }

            // Shrink window while we have more than k odd numbers
            while (oddCount > k) {
                if (nums[windowStart] % 2 == 1) {
                    oddCount--;
                }
                windowStart++;
            }

            // Add count of all valid subarrays ending at windowEnd
            count += windowEnd - windowStart + 1;
        }

        return count;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {1, 1, 2, 1, 1};
        int k1 = 3;
        System.out.println("Test Case 1: " + countNiceSubarrays(nums1, k1)); // Expected output: 2

        // Test Case 2
        int[] nums2 = {2, 4, 6};
        int k2 = 1;
        System.out.println("Test Case 2: " + countNiceSubarrays(nums2, k2)); // Expected output: 0

        // Test Case 3
        int[] nums3 = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        int k3 = 2;
        System.out.println("Test Case 3: " + countNiceSubarrays(nums3, k3)); // Expected output: 16

        // Test Case 4
        int[] nums4 = {1, 1, 1, 1, 1};
        int k4 = 1;
        System.out.println("Test Case 4: " + countNiceSubarrays(nums4, k4)); // Expected output: 5
    }
}
/**
 * Maximum Sum Subarray of Size K
 * Problem: Given an array of integers and a number k, find the maximum sum of any contiguous subarray of size k.
 *
 * Intuition:
 * - Instead of calculating sum for each window separately (which would be O(n*k)), we can reuse the sum
 * - When sliding the window, we only need to:
 *   1. Subtract the element leaving the window
 *   2. Add the new element entering the window
 * - This way we maintain the window sum with minimal operations
 *
 * Approach:
 * 1. Calculate sum of first k elements as initial window
 * 2. Slide window by one position at a time:
 *    - Subtract leftmost element (leaving window)
 *    - Add rightmost element (entering window)
 * 3. Keep track of maximum sum seen so far
 *
 * Example:
 * Input: arr = [2, 1, 5, 1, 3, 2], k = 3
 * Output: 9
 * Explanation: Subarray with maximum sum is [5, 1, 3]
 *
 * Time Complexity: O(n) where n is the size of array
 * - Single pass through the array
 * - Each element is added and subtracted exactly once
 *
 * Space Complexity: O(1) as we only use variables
 * - Only need two variables: windowSum and maxSum
 */
public class MaxSumSubarray {
    public static int findMaxSumSubarray(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            throw new IllegalArgumentException("Invalid input");
        }

        int maxSum = 0;
        int windowSum = 0;

        // Calculate sum of first window
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;

        // Slide window and update maxSum
        for (int i = k; i < arr.length; i++) {
            windowSum = windowSum - arr[i - k] + arr[i]; // Remove first element of previous window and add last element of current window
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] arr1 = {2, 1, 5, 1, 3, 2};
        int k1 = 3;
        System.out.println("Test Case 1: " + findMaxSumSubarray(arr1, k1)); // Expected output: 9

        // Test Case 2
        int[] arr2 = {1, 4, 2, 10, 2, 3, 1, 0, 20};
        int k2 = 4;
        System.out.println("Test Case 2: " + findMaxSumSubarray(arr2, k2)); // Expected output: 24

        // Test Case 3
        int[] arr3 = {1, 1, 1, 1, 1};
        int k3 = 2;
        System.out.println("Test Case 3: " + findMaxSumSubarray(arr3, k3)); // Expected output: 2
    }
}
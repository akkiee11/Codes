/**
 * First Negative Number in Window
 * Problem: Given an array and a positive integer k, find the first negative integer in every window of size k.
 *
 * Example:
 * Input: arr = [12, -1, -7, 8, -15, 30, 16, 28], k = 3
 * Output: [-1, -1, -7, -15, -15, 0]
 * Explanation: First negative number in windows of size 3:
 * [12, -1, -7] => -1
 * [-1, -7, 8] => -1
 * [-7, 8, -15] => -7
 * [8, -15, 30] => -15
 * [-15, 30, 16] => -15
 * [30, 16, 28] => 0 (no negative number)
 *
 * Time Complexity: O(n) where n is the size of array
 * Space Complexity: O(k) where k is the window size
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

public class FirstNegative {
    public static long[] findFirstNegatives(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            throw new IllegalArgumentException("Invalid input");
        }

        long[] result = new long[arr.length - k + 1];
        Queue<Integer> negativeIndices = new LinkedList<>();

        // Process first k elements (first window)
        for (int i = 0; i < k; i++) {
            if (arr[i] < 0) {
                negativeIndices.offer(i);
            }
        }

        // Store result for first window
        result[0] = negativeIndices.isEmpty() ? 0 : arr[negativeIndices.peek()];

        // Process rest of the elements
        for (int i = k; i < arr.length; i++) {
            // Remove elements outside current window
            while (!negativeIndices.isEmpty() && negativeIndices.peek() <= i - k) {
                negativeIndices.poll();
            }

            // Add current negative element
            if (arr[i] < 0) {
                negativeIndices.offer(i);
            }

            // Store result for current window
            result[i - k + 1] = negativeIndices.isEmpty() ? 0 : arr[negativeIndices.peek()];
        }

        return result;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] arr1 = {12, -1, -7, 8, -15, 30, 16, 28};
        int k1 = 3;
        System.out.println("Test Case 1: " + Arrays.toString(findFirstNegatives(arr1, k1)));
        // Expected: [-1, -1, -7, -15, -15, 0]

        // Test Case 2
        int[] arr2 = {-8, 2, 3, -6, 10};
        int k2 = 2;
        System.out.println("Test Case 2: " + Arrays.toString(findFirstNegatives(arr2, k2)));
        // Expected: [-8, 0, -6, -6]

        // Test Case 3
        int[] arr3 = {1, 2, 3, 4, 5};
        int k3 = 2;
        System.out.println("Test Case 3: " + Arrays.toString(findFirstNegatives(arr3, k3)));
        // Expected: [0, 0, 0, 0]
    }
}
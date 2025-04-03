/**
 * Average of Subarrays
 * Problem: Given an array of integers and a number k, find the averages of all contiguous subarrays of size k.
 *
 * Example:
 * Input: arr = [1, 3, 2, 6, -1, 4, 1, 8, 2], k = 5
 * Output: [2.2, 2.8, 2.4, 3.6, 2.8]
 * Explanation:
 * First window [1, 3, 2, 6, -1] => (1 + 3 + 2 + 6 - 1) / 5 = 2.2
 * Second window [3, 2, 6, -1, 4] => (3 + 2 + 6 - 1 + 4) / 5 = 2.8
 * And so on...
 *
 * Time Complexity: O(n) where n is the size of array
 * Space Complexity: O(1) excluding the space needed for output array
 */
import java.util.Arrays;

public class AverageSubarrays {
    public static double[] findAverages(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            throw new IllegalArgumentException("Invalid input");
        }

        double[] result = new double[arr.length - k + 1];
        double windowSum = 0;

        // Calculate sum of first window
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        result[0] = windowSum / k;

        // Slide window and calculate averages for remaining windows
        for (int i = k; i < arr.length; i++) {
            windowSum = windowSum - arr[i - k] + arr[i]; // Update window sum
            result[i - k + 1] = windowSum / k;
        }

        return result;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] arr1 = {1, 3, 2, 6, -1, 4, 1, 8, 2};
        int k1 = 5;
        System.out.println("Test Case 1: " + Arrays.toString(findAverages(arr1, k1)));
        // Expected: [2.2, 2.8, 2.4, 3.6, 2.8]

        // Test Case 2
        int[] arr2 = {1, 2, 3, 4, 5};
        int k2 = 3;
        System.out.println("Test Case 2: " + Arrays.toString(findAverages(arr2, k2)));
        // Expected: [2.0, 3.0, 4.0]

        // Test Case 3
        int[] arr3 = {10, 20, 30, 40, 50};
        int k3 = 2;
        System.out.println("Test Case 3: " + Arrays.toString(findAverages(arr3, k3)));
        // Expected: [15.0, 25.0, 35.0, 45.0]
    }
}
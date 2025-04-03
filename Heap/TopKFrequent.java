/**
 * Top K Frequent Elements
 *
 * Problem: Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Approach:
 * 1. Use a HashMap to count frequency of each number
 * 2. Use a max heap (PriorityQueue) to store elements based on their frequency
 * 3. Poll k elements from the heap to get the k most frequent elements
 *
 * Time Complexity: O(N * log(k)) where N is the length of nums
 * Space Complexity: O(N) for the HashMap and heap
 */

import java.util.*;

public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        // Count frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Create max heap based on frequency
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> b[1] - a[1] // Sort by frequency in descending order
        );
        
        // Add elements to heap
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            maxHeap.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        
        // Get top k elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll()[0];
        }
        
        return result;
    }
    
    // Helper method to print array
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    public static void main(String[] args) {
        TopKFrequent solution = new TopKFrequent();
        
        // Test Case 1: Multiple elements with different frequencies
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = [1,1,1,2,2,3], k = 2");
        System.out.print("Output: ");
        printArray(solution.topKFrequent(nums1, k1));
        
        // Test Case 2: Single element
        int[] nums2 = {1};
        int k2 = 1;
        System.out.println("\nTest Case 2:");
        System.out.println("Input: nums = [1], k = 1");
        System.out.print("Output: ");
        printArray(solution.topKFrequent(nums2, k2));
        
        // Test Case 3: Elements with same frequency
        int[] nums3 = {1, 1, 2, 2, 3, 3};
        int k3 = 3;
        System.out.println("\nTest Case 3:");
        System.out.println("Input: nums = [1,1,2,2,3,3], k = 3");
        System.out.print("Output: ");
        printArray(solution.topKFrequent(nums3, k3));
    }
}
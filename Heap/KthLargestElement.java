/**
 * Kth Largest Element in an Array
 *
 * Problem: Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 *
 * Example 2:
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 * Approach:
 * 1. Use a min heap to maintain k largest elements
 * 2. For each element in array:
 *    - If heap size < k, add element
 *    - If current element > heap top, remove top and add current element
 * 3. The top of heap will be kth largest element
 *
 * Time Complexity: O(N * log(k)) where N is the length of nums
 * Space Complexity: O(k) for the heap
 */

import java.util.*;

public class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        // Create min heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // Process each element
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        
        // Return kth largest element
        return minHeap.peek();
    }
    
    public static void main(String[] args) {
        KthLargestElement solution = new KthLargestElement();
        
        // Test Case 1
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = [3,2,1,5,6,4], k = 2");
        System.out.println("Output: " + solution.findKthLargest(nums1, k1));
        
        // Test Case 2
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        System.out.println("\nTest Case 2:");
        System.out.println("Input: nums = [3,2,3,1,2,4,5,5,6], k = 4");
        System.out.println("Output: " + solution.findKthLargest(nums2, k2));
        
        // Test Case 3: Single element
        int[] nums3 = {1};
        int k3 = 1;
        System.out.println("\nTest Case 3:");
        System.out.println("Input: nums = [1], k = 1");
        System.out.println("Output: " + solution.findKthLargest(nums3, k3));
    }
}
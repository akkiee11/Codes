/**
 * Find K Pairs with Smallest Sums
 *
 * Problem: Given two sorted arrays nums1 and nums2 of size m and n respectively,
 * return k pairs (u,v) where u is from nums1 and v is from nums2,
 * such that the pairs have the k smallest sums (u + v).
 *
 * Example 1:
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence:
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *
 * Example 2:
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 *
 * Approach:
 * 1. Use a min heap to store pairs based on their sum
 * 2. Start with pairs formed with first element of nums1
 * 3. For each pair polled, add next pair with same nums1 index
 * 4. Continue until k pairs are found or all pairs are processed
 *
 * Time Complexity: O(k * log(k))
 * Space Complexity: O(k)
 */

import java.util.*;

public class KSmallestPairs {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return result;
        }
        
        // Min heap to store pairs based on sum
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]])
        );
        
        // Add initial pairs with first element of nums2
        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.offer(new int[]{i, 0});
        }
        
        // Process k pairs
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] pair = minHeap.poll();
            int i = pair[0];
            int j = pair[1];
            
            // Add pair to result
            result.add(Arrays.asList(nums1[i], nums2[j]));
            
            // If we can form next pair with same nums1 element
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{i, j + 1});
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        KSmallestPairs solution = new KSmallestPairs();
        
        // Test Case 1
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k1 = 3;
        System.out.println("Test Case 1:");
        System.out.println("nums1 = [1,7,11], nums2 = [2,4,6], k = 3");
        System.out.println("Output: " + solution.kSmallestPairs(nums1, nums2, k1));
        
        // Test Case 2
        int[] nums3 = {1, 1, 2};
        int[] nums4 = {1, 2, 3};
        int k2 = 2;
        System.out.println("\nTest Case 2:");
        System.out.println("nums1 = [1,1,2], nums2 = [1,2,3], k = 2");
        System.out.println("Output: " + solution.kSmallestPairs(nums3, nums4, k2));
        
        // Test Case 3: Empty array
        int[] nums5 = {};
        int[] nums6 = {1};
        int k3 = 1;
        System.out.println("\nTest Case 3:");
        System.out.println("nums1 = [], nums2 = [1], k = 1");
        System.out.println("Output: " + solution.kSmallestPairs(nums5, nums6, k3));
    }
}
/**
 * Merge Sorted Arrays
 * 
 * Problem Description:
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * Assume that nums1 has a size equal to m + n such that it has enough space to hold additional elements from nums2.
 * 
 * Example:
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * 
 * Approach:
 * Two Pointer Technique from End (O(m+n)):
 * - Use two pointers starting from the end of both arrays
 * - Compare elements and place larger one at the end of nums1
 * - Continue until all elements are placed
 * - Time Complexity: O(m+n), Space Complexity: O(1)
 */

import java.util.Arrays;

public class MergeSortedArrays {
    
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Initialize pointers for nums1, nums2, and merged array
        int p1 = m - 1;    // Last element in nums1
        int p2 = n - 1;    // Last element in nums2
        int p = m + n - 1; // Last position in merged array
        
        // Compare and place elements from the end
        while (p2 >= 0) {
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {1, 2, 3, 0, 0, 0},          // nums1 with space
            {2, 5, 6},                     // nums2
            {1},                           // Single element in nums1
            {},                            // Empty nums2
            {0},                           // nums1 with space
            {1},                           // Single element in nums2
            {4, 5, 6, 0, 0, 0},           // Larger elements in nums1
            {1, 2, 3},                     // Smaller elements in nums2
            {1, 2, 3, 0, 0, 0, 0, 0},     // Multiple zeros at end
            {4, 5, 6, 7, 8}               // Multiple elements in nums2
        };
        
        // Run test cases in pairs
        for (int i = 0; i < testCases.length; i += 2) {
            int[] nums1 = Arrays.copyOf(testCases[i], testCases[i].length);
            int[] nums2 = testCases[i + 1];
            int m = nums1.length - nums2.length;
            int n = nums2.length;
            
            System.out.println("\nBefore merge:");
            System.out.println("nums1: " + Arrays.toString(nums1));
            System.out.println("nums2: " + Arrays.toString(nums2));
            
            merge(nums1, m, nums2, n);
            
            System.out.println("After merge:");
            System.out.println("Result: " + Arrays.toString(nums1));
        }
    }
}
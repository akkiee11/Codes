/**
 * Intersection of Two Arrays
 * 
 * Problem Description:
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must be unique and the result can be in any order.
 * 
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * 
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * 
 * Constraints:
 * - 1 <= nums1.length, nums2.length <= 1000
 * - 0 <= nums1[i], nums2[i] <= 1000
 * 
 * Approach:
 * Two Pointers with Sorting:
 * 1. Sort both arrays
 * 2. Use two pointers to traverse both arrays
 * 3. Use a Set to store unique intersections
 * - Time Complexity: O(nlogn) due to sorting
 * - Space Complexity: O(n) for the result set
 */

import java.util.*;

public class IntersectionArrays {
    
    public static int[] intersection(int[] nums1, int[] nums2) {
        // Sort both arrays
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        // Use Set to store unique intersections
        Set<Integer> intersectionSet = new HashSet<>();
        
        // Two pointers
        int i = 0, j = 0;
        
        // Traverse both arrays
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                // Found an intersection
                intersectionSet.add(nums1[i]);
                i++;
                j++;
            }
        }
        
        // Convert Set to array
        int[] result = new int[intersectionSet.size()];
        int index = 0;
        for (int num : intersectionSet) {
            result[index++] = num;
        }
        
        return result;
    }
    
    // Helper method to print array
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {1, 2, 2, 1}, {2, 2},           // Basic case with duplicates
            {4, 9, 5}, {9, 4, 9, 8, 4},      // Multiple intersections
            {1, 2, 3, 4, 5}, {6, 7, 8, 9},   // No intersection
            {1}, {1},                        // Single element intersection
            {1, 2, 3}, {2, 2},              // One duplicate in second array
            {}, {1, 2, 3}                    // Empty array case
        };
        
        // Run test cases
        for (int i = 0; i < testCases.length; i += 2) {
            System.out.println("\nTest Case " + (i/2 + 1) + ":");
            System.out.println("nums1 = " + arrayToString(testCases[i]));
            System.out.println("nums2 = " + arrayToString(testCases[i+1]));
            int[] result = intersection(testCases[i], testCases[i+1]);
            System.out.println("Intersection = " + arrayToString(result));
        }
    }
}
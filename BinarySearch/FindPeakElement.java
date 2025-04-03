/**
 * Find Peak Element
 * Problem: A peak element is an element that is strictly greater than its neighbors.
 * Given an integer array nums, find a peak element, and return its index.
 * If the array contains multiple peaks, return the index to any of the peaks.
 * 
 * Example:
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // Compare with the next element
            if (nums[mid] > nums[mid + 1]) {
                // Peak must be in the left half (including mid)
                right = mid;
            } else {
                // Peak must be in the right half
                left = mid + 1;
            }
        }
        
        // At this point, left == right and this is the peak element
        return left;
    }
    
    // Test cases
    public static void main(String[] args) {
        FindPeakElement solution = new FindPeakElement();
        
        // Test case 1: Regular case
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(solution.findPeakElement(nums1)); // Expected: 2
        
        // Test case 2: Multiple peaks
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        int peak2 = solution.findPeakElement(nums2);
        System.out.println(peak2); // Expected: 5 (index of 6)
        
        // Test case 3: Array with two elements
        int[] nums3 = {1, 2};
        System.out.println(solution.findPeakElement(nums3)); // Expected: 1
        
        // Test case 4: Single element
        int[] nums4 = {1};
        System.out.println(solution.findPeakElement(nums4)); // Expected: 0
    }
}
/**
 * Find First and Last Position of Element in Sorted Array
 * Problem: Given a sorted array of integers nums and a target value,
 * find the starting and ending position of target in nums.
 * Return [-1, -1] if target is not found.
 * 
 * Example:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 */
public class FirstLastPosition {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) return result;
        
        // Find first position
        result[0] = findBoundary(nums, target, true);
        if (result[0] == -1) return result;
        
        // Find last position
        result[1] = findBoundary(nums, target, false);
        
        return result;
    }
    
    // Helper method to find boundary
    // isFirst: true to find first position, false to find last position
    private int findBoundary(int[] nums, int target, boolean isFirst) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                if (isFirst) {
                    // For first position, continue searching in left half
                    if (mid == 0 || nums[mid - 1] != target) return mid;
                    right = mid - 1;
                } else {
                    // For last position, continue searching in right half
                    if (mid == nums.length - 1 || nums[mid + 1] != target) return mid;
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    // Test cases
    public static void main(String[] args) {
        FirstLastPosition solution = new FirstLastPosition();
        
        // Test case 1: Regular case
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int[] result1 = solution.searchRange(nums1, 8);
        System.out.println("[" + result1[0] + ", " + result1[1] + "]"); // Expected: [3, 4]
        
        // Test case 2: Target not found
        int[] result2 = solution.searchRange(nums1, 6);
        System.out.println("[" + result2[0] + ", " + result2[1] + "]"); // Expected: [-1, -1]
        
        // Test case 3: Single element array
        int[] nums2 = {1};
        int[] result3 = solution.searchRange(nums2, 1);
        System.out.println("[" + result3[0] + ", " + result3[1] + "]"); // Expected: [0, 0]
        
        // Test case 4: Empty array
        int[] nums3 = {};
        int[] result4 = solution.searchRange(nums3, 5);
        System.out.println("[" + result4[0] + ", " + result4[1] + "]"); // Expected: [-1, -1]
    }
}
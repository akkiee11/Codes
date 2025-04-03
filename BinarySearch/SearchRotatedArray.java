/**
 * Search in Rotated Sorted Array
 * Problem: Given a sorted array nums that is rotated at an unknown pivot index,
 * and a target value, return the index of target if it exists in the array, or -1 if it does not exist.
 * 
 * Example:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 */
public class SearchRotatedArray {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // Check if the left half is sorted
            if (nums[left] <= nums[mid]) {
                // Check if target is in the left half
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                // Check if target is in the right half
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
    
    // Test cases
    public static void main(String[] args) {
        SearchRotatedArray solution = new SearchRotatedArray();
        
        // Test case 1: Regular case
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(solution.search(nums1, 0)); // Expected output: 4
        
        // Test case 2: Target not found
        System.out.println(solution.search(nums1, 3)); // Expected output: -1
        
        // Test case 3: Array with one element
        int[] nums2 = {1};
        System.out.println(solution.search(nums2, 1)); // Expected output: 0
        
        // Test case 4: Empty array
        int[] nums3 = {};
        System.out.println(solution.search(nums3, 5)); // Expected output: -1
    }
}
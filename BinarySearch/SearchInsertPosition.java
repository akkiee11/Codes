/**
 * Search Insert Position
 * Problem: Given a sorted array of distinct integers and a target value,
 * return the index if the target is found. If not, return the index where it would be
 * if it were inserted in order.
 *
 * Example 1:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // If we reach here, the target was not found
        // 'left' will be the position where target should be inserted
        return left;
    }
    
    // Test cases
    public static void main(String[] args) {
        SearchInsertPosition solution = new SearchInsertPosition();
        
        // Test case 1: Target exists in array
        int[] nums1 = {1, 3, 5, 6};
        System.out.println(solution.searchInsert(nums1, 5)); // Expected: 2
        
        // Test case 2: Target doesn't exist - should be inserted in middle
        System.out.println(solution.searchInsert(nums1, 2)); // Expected: 1
        
        // Test case 3: Target should be inserted at end
        System.out.println(solution.searchInsert(nums1, 7)); // Expected: 4
        
        // Test case 4: Target should be inserted at beginning
        System.out.println(solution.searchInsert(nums1, 0)); // Expected: 0
        
        // Test case 5: Empty array
        int[] nums2 = {};
        System.out.println(solution.searchInsert(nums2, 1)); // Expected: 0
    }
}
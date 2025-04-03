/**
 * Find Minimum in Rotated Sorted Array
 * Problem: Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 *
 * Example:
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 */
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    // Test cases
    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray solution = new FindMinimumInRotatedSortedArray();
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println(solution.findMin(nums1)); // Expected: 1

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(solution.findMin(nums2)); // Expected: 0

        int[] nums3 = {11, 13, 15, 17};
        System.out.println(solution.findMin(nums3)); // Expected: 11
    }
}
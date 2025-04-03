/**
 * Search for a Range
 * Problem: Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * If the target is not found in the array, return [-1, -1].
 *
 * Example:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 */
public class SearchForARange {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) return result;
        result[0] = findBound(nums, target, true);
        result[1] = findBound(nums, target, false);
        return result;
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                if (isFirst) {
                    if (mid == left || nums[mid - 1] != target) {
                        return mid;
                    }
                    right = mid - 1;
                } else {
                    if (mid == right || nums[mid + 1] != target) {
                        return mid;
                    }
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
        SearchForARange solution = new SearchForARange();
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int[] result1 = solution.searchRange(nums1, 8);
        System.out.println("[" + result1[0] + ", " + result1[1] + "]"); // Expected: [3, 4]

        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int[] result2 = solution.searchRange(nums2, 6);
        System.out.println("[" + result2[0] + ", " + result2[1] + "]"); // Expected: [-1, -1]

        int[] nums3 = {};
        int[] result3 = solution.searchRange(nums3, 0);
        System.out.println("[" + result3[0] + ", " + result3[1] + "]"); // Expected: [-1, -1]
    }
}
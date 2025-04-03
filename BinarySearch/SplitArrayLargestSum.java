/**
 * Split Array Largest Sum
 * Problem: Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
 * Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * Example:
 * Input: nums = [7,2,5,10,8], m = 2
 * Output: 18
 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        int low = 0;
        int high = 0;
        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canSplit(nums, m, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean canSplit(int[] nums, int m, int maxSum) {
        int count = 1;
        int currentSum = 0;
        for (int num : nums) {
            if (currentSum + num > maxSum) {
                count++;
                currentSum = num;
                if (count > m) return false;
            } else {
                currentSum += num;
            }
        }
        return true;
    }

    // Test cases
    public static void main(String[] args) {
        SplitArrayLargestSum solution = new SplitArrayLargestSum();
        int[] nums1 = {7, 2, 5, 10, 8};
        System.out.println(solution.splitArray(nums1, 2)); // Expected: 18

        int[] nums2 = {1, 2, 3, 4, 5};
        System.out.println(solution.splitArray(nums2, 2)); // Expected: 9
    }
}
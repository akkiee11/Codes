// Range Sum Query

/**
 * Problem Statement:
 * Given an integer array nums, handle multiple queries of the following type:
 * - Calculate the sum of the elements of nums between indices left and right inclusive.
 *
 * Approach:
 * - Precompute the prefix sum array where each element at index i is the sum of all elements from index 0 to i.
 * - For each query, the sum can be calculated as prefix[right] - prefix[left - 1].
 *
 * Time Complexity:
 * - O(n) for preprocessing, O(1) per query.
 *
 * Space Complexity:
 * - O(n) for the prefix sum array.
 */

public class RangeSumQuery {
    private int[] prefixSum;

    public RangeSumQuery(int[] nums) {
        prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefixSum[right + 1] - prefixSum[left];
    }

    public static void main(String[] args) {
        // Test cases
        int[] nums = {1, 2, 3, 4, 5};
        RangeSumQuery obj = new RangeSumQuery(nums);
        System.out.println(obj.sumRange(1, 3)); // Output: 9
        System.out.println(obj.sumRange(0, 4)); // Output: 15
    }
}
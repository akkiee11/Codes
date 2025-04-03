// Subarray Sum Equals K

/**
 * Problem Statement:
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 *
 * Approach:
 * - Use a hash map to store the frequency of prefix sums.
 * - Iterate through the array, computing the prefix sum and checking if (prefix_sum - k) exists in the hash map.
 *
 * Time Complexity:
 * - O(n)
 *
 * Space Complexity:
 * - O(n)
 */

import java.util.HashMap;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0, sum = 0;
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        // Test cases
        SubarraySumEqualsK obj = new SubarraySumEqualsK();
        System.out.println(obj.subarraySum(new int[]{1, 1, 1}, 2)); // Output: 2
        System.out.println(obj.subarraySum(new int[]{1, 2, 3}, 3)); // Output: 2
    }
}
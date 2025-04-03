package PrefixSum;
/*
Problem Statement:
Given an array of integers nums and two indices i and j, calculate the sum of elements from index i to j (inclusive).

Approach Intuition:
We use the prefix sum technique to efficiently calculate subarray sums. The prefix sum array stores cumulative sums,
allowing us to compute any subarray sum in constant time by subtracting prefix sums.

Time Complexity: O(n) for prefix sum calculation, O(1) for each query
Space Complexity: O(n) for prefix sum array
*/

public class BasicSubarraySum {
    public int subarraySum(int[] nums, int i, int j) {
        int[] prefixSum = new int[nums.length + 1];
        for (int k = 0; k < nums.length; k++) {
            prefixSum[k + 1] = prefixSum[k] + nums[k];
        }
        return prefixSum[j + 1] - prefixSum[i];
    }

    public static void main(String[] args) {
        BasicSubarraySum solution = new BasicSubarraySum();
        int[] nums = {1, 2, 3, 4};
        // Test Cases
System.out.println(solution.subarraySum(nums, 1, 3)); // Expected: 9
System.out.println(solution.subarraySum(new int[]{1, 1, 1}, 0, 2)); // Expected: 3
System.out.println(solution.subarraySum(new int[]{-1, -1, -1}, 0, 2)); // Expected: -3
System.out.println(solution.subarraySum(new int[]{5}, 0, 0)); // Expected: 5
System.out.println(solution.subarraySum(new int[]{1, 2, 3, 4, 5}, 0, 4)); // Expected: 15
    }
}
package PrefixSum;
/**
 * Problem: Split Array With Same Average
 * Given an array of integers, determine if it's possible to split the array into two non-empty subsets
 * where the average of elements in both subsets is the same.
 *
 * Approach:
 * 1. Calculate the total sum of the array
 * 2. Use dynamic programming to check all possible subset sums
 * 3. For each possible subset size, check if a subset exists with sum equal to (total_sum * subset_size) / total_elements
 * 4. If such a subset exists, return true
 *
 * Intuition:
 * The problem reduces to finding a subset whose average equals the overall array average.
 * This can be transformed into a subset sum problem with specific constraints.
 */
public class SplitArrayWithSameAverage {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) sum += num;
        
        boolean[][] dp = new boolean[sum + 1][n / 2 + 1];
        dp[0][0] = true;
        
        for (int num : nums) {
            for (int i = sum; i >= num; i--) {
                for (int j = 1; j <= n / 2; j++) {
                    dp[i][j] = dp[i][j] || dp[i - num][j - 1];
                }
            }
        }
        
        for (int i = 1; i <= n / 2; i++) {
            if (sum * i % n == 0 && dp[sum * i / n][i]) {
                return true;
            }
        }
        return false;
    }

        // Test Cases
    public static void main(String[] args) {
        SplitArrayWithSameAverage solution = new SplitArrayWithSameAverage();
        
        // Test case 1: Can be split into [1,2,3,4] and [5,6,7,8]
        int[] nums1 = {1,2,3,4,5,6,7,8};
        System.out.println(solution.splitArraySameAverage(nums1)); // Expected: true
        
        // Test case 2: Cannot be split
        int[] nums2 = {1,3};
        System.out.println(solution.splitArraySameAverage(nums2)); // Expected: false
        
        // Test case 3: Edge case - single element
        int[] nums3 = {1};
        System.out.println(solution.splitArraySameAverage(nums3)); // Expected: false
        
        // Test case 4: Edge case - all elements same
        int[] nums4 = {2,2,2,2};
        System.out.println(solution.splitArraySameAverage(nums4)); // Expected: true
        
        // Test case 5: Large numbers
        int[] nums5 = {1000,2000,3000,4000};
        System.out.println(solution.splitArraySameAverage(nums5)); // Expected: true
    }
        SplitArrayWithSameAverage solution = new SplitArrayWithSameAverage();
        int[] nums = {1,2,3,4,5,6,7,8};
        System.out.println(solution.splitArraySameAverage(nums)); // Output: true
    }
}
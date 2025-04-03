/**
 * Problem Statement:
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * 
 * Approach Intuition:
 * We use Kadane's Algorithm which maintains two variables: currentSum and maxSum. currentSum tracks the maximum sum of the subarray ending at the current index,
 * while maxSum stores the maximum sum encountered so far. For each element, we decide whether to start a new subarray or continue the existing one based on which
 * gives a larger sum. This approach ensures we find the maximum subarray sum in linear time.
 *
 * Test Cases:
 * 1. Input: [-2,1,-3,4,-1,2,1,-5,4] => Output: 6 (Subarray [4,-1,2,1])
 * 2. Input: [1] => Output: 1 (Single element)
 * 3. Input: [5,4,-1,7,8] => Output: 23 (Entire array)
 * 4. Input: [-1,-2,-3] => Output: -1 (Single element -1)
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }

    public static void main(String[] args) {
        MaximumSubarray solution = new MaximumSubarray();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solution.maxSubArray(nums)); // Output: 6
    }
}
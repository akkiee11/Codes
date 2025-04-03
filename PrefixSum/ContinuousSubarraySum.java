/*
Problem Statement:
Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.

Approach Intuition:
1. We use prefix sum technique to calculate cumulative sums
2. We store the remainders of the prefix sums when divided by k in a HashMap
3. If we encounter the same remainder again, it means the subarray between these two positions sums to a multiple of k
4. We also ensure the subarray length is at least 2

Time Complexity: O(n)
Space Complexity: O(n)
*/
public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int remainder = sum % k;
            if (map.containsKey(remainder)) {
                if (i - map.get(remainder) > 1) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        ContinuousSubarraySum solution = new ContinuousSubarraySum();
        
        // Test Case 1: Valid subarray exists
        int[] nums1 = {23, 2, 4, 6, 7};
        System.out.println(solution.checkSubarraySum(nums1, 6)); // Output: true
        
        // Test Case 2: No valid subarray
        int[] nums2 = {23, 2, 6, 4, 7};
        System.out.println(solution.checkSubarraySum(nums2, 13)); // Output: false
        
        // Test Case 3: Edge case with zero
        int[] nums3 = {0, 0};
        System.out.println(solution.checkSubarraySum(nums3, 1)); // Output: true
        
        // Test Case 4: Single element array
        int[] nums4 = {5};
        System.out.println(solution.checkSubarraySum(nums4, 5)); // Output: false
    }
}